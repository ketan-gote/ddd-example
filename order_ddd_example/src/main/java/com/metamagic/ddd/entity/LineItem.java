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

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.metamagic.ddd.exception.InvalidDataException;

/**
 * Line items for the order.
 * @author ketan gote
 *
 */
@PersistenceCapable(table = "lineitems",detachable = "true")
public class LineItem {

	
	@PrimaryKey
	@Persistent(column = "lineitemid", customValueStrategy="uuid")
	private String lineitemid;
	
	@Persistent(column="orderId")
	private Order order;
	
	@Persistent(column = "itemid")
	private String itemId;
	
	@Persistent(column = "itemname")
	private String itemName;
	
	@Persistent(column = "price")
	private Double price;
	
	@Persistent(column = "quantity")
	private Integer quantity;

	@Persistent(column = "subtotal")
	private Double subTotal;
	
	/**
	 *
	 * @param itemId {@link String}
	 * @param itemName {@link String}
	 * @param price {@link Double}
	 * @param quantity {@link Integer}
	 * @param order {@link Order}
	 * @throws InvalidDataException
	 */
	public LineItem(String itemId, String itemName, Double price, Integer quantity, Order order) throws InvalidDataException{
		this.setItemId(itemId);
		this.setItemName(itemName);
		this.setPrice(price);
		this.setQuantity(quantity);
		this.order = order;
		this.subTotal = price * quantity;
	}

	/**
	 * Sets itemid if its is not null
	 * @param itemId
	 * @throws InvalidDataException
	 */
	private void setItemId(String itemId) throws InvalidDataException{
		if(itemId == null)
			throw new InvalidDataException("Invalid itemId");
		
		this.itemId = itemId;
	}
	
	/**
	 * Sets itemname if its is not null
	 * @param itemName
	 * @throws InvalidDataException
	 */
	private void setItemName(String itemName) throws InvalidDataException{
		if(itemName == null)
			throw new InvalidDataException("Invalid itemName");
		
		this.itemName = itemName;
	}
	
	/**
	 * Sets price if its is not null
	 * @param price
	 * @throws InvalidDataException
	 */
	private void setPrice(Double price) throws InvalidDataException{
		if(price == null)
			throw new InvalidDataException("Invalid price");
		
		this.price = price;
	}
	
	/**
	 * Sets quantity if its is not null
	 * @param quantity
	 * @throws InvalidDataException
	 */
	private void setQuantity(Integer quantity) throws InvalidDataException{
		if(quantity == null)
			throw new InvalidDataException("Invalid quantity");
		
		this.quantity = quantity;
	}
	
	
	/**
	 * @return lineitemid {@link String}
	 */
	public String getLineitemid() {
		return lineitemid;
	}

	/**
	 * @return itemid {@link String}
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @return item name {@link String}
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @return price {@link Double}
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * 
	 * @return quantity {@link Integer}
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * 
	 * @return subtotal {@link Double}
	 */
	public Double getSubTotal(){
		return subTotal;
	}
	
	@Override
	public boolean equals(Object lineItem) {
		if(lineItem instanceof LineItem){
			return itemId.equals(((LineItem) lineItem).getItemId());
		}else{
			return false;
		}
		
	}
	
	@Override
	public int hashCode() {
		return itemId.hashCode();
	}

	@Override
	public String toString() {
		return "{ itemId=" + itemId + ", itemName=" + itemName + ", price=" + price + ", quantity=" + quantity
				+ ", subTotal=" + subTotal+" }";
	}
	
	
	
}
