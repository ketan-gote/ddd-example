/**

 * Copyright (c) 2018 Ketan Gote
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.

*/
package com.metamagic.ddd.entity;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.metamagic.ddd.deserializer.OrderDesiralizer;
import com.metamagic.ddd.exception.InvalidDataException;

@JsonDeserialize(using = OrderDesiralizer.class)
@PersistenceCapable(table = "order", detachable = "true")
public class Order {
	
	@PrimaryKey
	@Persistent(column = "orderid", customValueStrategy="uuid")
	private String orderId;
	
	@Persistent(column = "userid")
	private String userId;
	
	@Persistent(column = "orderno")
	private String orderNo;
	
	@Persistent(column = "orderdate")
	private Date orderDate;
	
	@Persistent(column = "status")
	private Status status;
	
	@Persistent(mappedBy = "order", defaultFetchGroup="true")
	private Set<LineItem> lineItems;

	@Persistent(mappedBy = "order", defaultFetchGroup = "true")
	private ShippingAddress shippingAddress;
	
	@Persistent(mappedBy = "order", defaultFetchGroup = "true")
	private Payment payment;
	
	@Persistent(defaultFetchGroup = "true")
	private MoneytoryValue moneytoryValue;
	/**
	 *
	 * @param userId {@link String}
	 * @throws InvalidDataException
	 */
	public Order(String userId) throws InvalidDataException{
		this.setUserId(userId);
		this.generateOrderNo();
		this.initCart();
		this.markPaymentExepected();
		this.moneytoryValue();
	}
	
	/**
	 * Set order date an order no
	 */
	private void generateOrderNo(){
		this.orderDate = new Date(Calendar.getInstance().getTimeInMillis());
		this.orderNo = "OD"+orderDate.getTime()+"";	
	}
	
	/**
	 * Initialize the empty cart
	 */
	private void initCart(){
		this.lineItems = new HashSet<LineItem>();
	}
	
	/**
	 * Maps cart status as open
	 */
	public void markPaymentExepected(){
		this.status = Status.PAYMENT_EXPECTED;
	}
	
	/**
	 * Maps cart status as close
	 */	
	public void markPaid() throws InvalidDataException{
		if(this.shippingAddress == null){
			throw new InvalidDataException("Invalid state exception");
		}
		this.status = Status.PAID;
	}
	
	/**
	 * 
	 * @return {@link MoneytoryValue}
	 */
	public MoneytoryValue moneytoryValue(){
		moneytoryValue = new MoneytoryValue(getTotal(), "USD");
		return moneytoryValue;
	}
	
	/**
	 * Added line item to user cart
	 * @param itemId
	 * @param itemName
	 * @param price
	 * @param quantity
	 * @throws InvalidDataException
	 */
	public void addLineItem(String itemId, String itemName, Double price, Integer quantity) throws InvalidDataException{
		LineItem lineItem = new LineItem(itemId, itemName, price, quantity, this);
		this.lineItems.add(lineItem);
		this.moneytoryValue();
	}
	
	/**
	 * Removes line item from user cart
	 * @param itemId
	 */
	public void removeItem(String itemId){
		for (LineItem lineItem : lineItems) {
			if(lineItem.getItemId().equals(itemId)){
				lineItems.remove(lineItem);
			}
		}
		
	}
	
	/**
	 * Add the shipping address to order
	 * @param shippinglabel
	 * @param address
	 * @param country
	 * @param province
	 * @param postalcode
	 * @param city
	 * @throws InvalidDataException
	 */
	public void addShippingAddress(String shippinglabel, String address, String country, String province, String postalcode, String city) throws InvalidDataException{
		if(this.shippingAddress == null){
			ShippingAddress shippingAddress = new ShippingAddress(shippinglabel, address, country, province, postalcode, city, this);
			this.shippingAddress = shippingAddress;
		}else{
			this.shippingAddress.updateShippingAddress(shippinglabel, address, country, province, postalcode, city);
		}
	}
	
	/**
	 * Add the payment details
	 * @param paymentmode
	 * @throws InvalidDataException
	 */
	public void addPaymentDetails(String paymentmode) throws InvalidDataException{
		Payment payment = new Payment(paymentmode, getTotal(), this);
		this.payment = payment;
		this.markPaid();
	}

	/**
	 * 
	 * @return total {@link Double}
	 */
	public Double getTotal(){
		double total = 0.0;
		for (Iterator iterator = lineItems.iterator(); iterator.hasNext();) {
			LineItem lineItem = (LineItem) iterator.next();
			System.out.println("---"+lineItem.getItemId()+"--"+lineItem.getItemName() +"--"+lineItem.getPrice() +"--"+lineItem.getQuantity());
			total = total + lineItem.getSubTotal();
		}
		return total;
	}
	
	
	
	/**
	 * 
	 * @param userId {@link String}
	 * @throws InvalidDataException
	 */
	private void setUserId(String userId) throws InvalidDataException{
		if(userId == null || userId.length() == 0)
			throw new InvalidDataException("Invalid UserId");
		else
			this.userId = userId;
	}

	/**
	 * 
	 * @return orderid {@link String}
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * 
	 * @return userid {@link String}
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 
	 * @return orderno {@link String}
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 
	 * @return orderdate {@link Date}
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * 
	 * @return order status {@link String}
	 */
	public boolean isPaid() {
		return this.status.equals(Status.PAID);
	}

	/**
	 * 
	 * @return Set<LineItem>
	 */
	public Set<LineItem> getLineItems() {
		return lineItems;
	}

	public Status getStatus(){
		return this.status;
	}
	
	public MoneytoryValue getMoneytoryValue(){
		return this.moneytoryValue;
	}

	@Override
	public boolean equals(Object order) {
		if(order instanceof Order){
			return orderId.equals(((Order)order).getOrderId());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return orderId.hashCode();
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", orderNo=" + orderNo + ", orderDate=" + orderDate
				+ ", status=" + status + ", lineItems=" + lineItems + ", shippingAddress=" + shippingAddress
				+ ", payment=" + payment + ", moneytoryValue=" + moneytoryValue + "]";
	}

	
	public static enum Status {

		/**
		 * Placed, but not payed yet. Still changeable.
		 */
		PAYMENT_EXPECTED,

		/**
		 * {@link Order} was payed. No changes allowed to it anymore.
		 */
		PAID,

		/**
		 * The {@link Order} is currently processed.
		 */
		PREPARING,

		/**
		 * The {@link Order} is ready to be picked up by the customer.
		 */
		READY,

		/**
		 * The {@link Order} was completed.
		 */
		TAKEN;
	}
	
}
