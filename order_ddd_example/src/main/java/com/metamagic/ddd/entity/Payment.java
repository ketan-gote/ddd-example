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

import java.util.Calendar;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.metamagic.ddd.exception.InvalidDataException;

/**
 * Payment details for order
 * @author ketan gote
 *
 */
@PersistenceCapable(table = "payment", detachable = "true")
public class Payment {
	
	@PrimaryKey
	@Persistent(column = "paymentid", customValueStrategy = "uuid")
	private String paymentid;
	
	@Persistent(column = "mode")
	private String mode;
	
	@Persistent(column = "transactionid")
	private String transactionId;
	
	@Persistent(column = "amount")
	private Double amount;
	
	@Persistent(column="orderId")
	private Order order;

	/**
	 * Creates payment object with requried attributes.
	 * @param mode
	 * @param amount
	 * @param order
	 * @throws InvalidDataException
	 */
	public Payment(String mode, Double amount, Order order) throws InvalidDataException{
		this.setMode(mode);
		this.setAmount(amount);
		this.generateTransactionId();
		this.order = order;
	}
	
	/**
	 * generates transaction id.xxww
	 */
	private void generateTransactionId(){
		this.transactionId = "TR"+this.mode.trim()+""+Calendar.getInstance().getTimeInMillis();
	}
	
	/**
	 * Set payment mode if it is not null
	 * @param mode
	 * @throws InvalidDataException
	 */
	private void setMode(String mode) throws InvalidDataException
	{
		if(mode == null || mode.length() == 0){
			throw new InvalidDataException("Invalid payment mode");
		}
		
		this.mode = mode;
	}
	
	
	/**
	 * sets amount if it is not null
	 * @param amount
	 * @throws InvalidDataException
	 */
	private void setAmount(Double amount) throws InvalidDataException
	{
		if(amount == null){
			throw new InvalidDataException("Invalid amount");
		}
		
		this.amount = amount;
	}
	
	
	/**
	 * 
	 * @return payment id {@link String}
	 */
	public String getPaymentid() {
		return paymentid;
	}

	/**
	 * 
	 * @return payment mode {@link String}
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * 
	 * @return transaction id {@link String}
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * 
	 * @return amount {@link Double}
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * 
	 * @return order {@link Order}
	 */
	public Order getOrder() {
		return order;
	}

	@Override
	public boolean equals(Object payment) {
		if(payment instanceof Payment){
			return (hashCode() == ((Payment)payment).hashCode());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return (mode+transactionId).hashCode();
	}

	@Override
	public String toString() {
		return "Payment [paymentid=" + paymentid + ", mode=" + mode + ", transactionId=" + transactionId + ", amount="
				+ amount + "]";
	}	
	

}
