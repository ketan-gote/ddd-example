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
 * Shipping address for order
 * @author ketangote
 *
 */
@PersistenceCapable(table = "shippingaddress", detachable = "true")
public class ShippingAddress {
	
	@PrimaryKey
	@Persistent(column = "shippingaddressid", customValueStrategy = "uuid")
	private String shippingAddressId;

	@Persistent(column = "label")
	private String label;

	@Persistent(column = "address")
	private String address;
	
	@Persistent(column = "country")
	private String country;
	
	@Persistent(column = "province")
	private String province;
	
	@Persistent(column = "city")
	private String city;
	
	@Persistent(column = "postalcode")
	private String postalcode;
	
	@Persistent(column="orderId")
	private Order order;
	

	/**
	 * Create shipping address for order with required attributes
	 * @param label {@link String}
	 * @param address {@link String}
	 * @param country {@link String}
	 * @param province {@link String}
	 * @param postalcode {@link String}
	 * @param city {@link String}
	 * @param order {@link Order}
	 * @throws InvalidDataException
	 */
	public ShippingAddress(String label, String address, String country, String province, String postalcode, String city, Order order) throws InvalidDataException{
		this.setLabel(label);
		this.setAddress(address);
		this.setCountry(country);
		this.setProvince(province);
		this.setPostalCode(postalcode);
		this.setCity(city);
		this.setOrder(order);
	}
	
	/**
	 * 
	 * @param label
	 * @param address
	 * @param country
	 * @param province
	 * @param postalcode
	 * @param city
	 * @throws InvalidDataException
	 */
	public void updateShippingAddress(String label, String address, String country, String province, String postalcode, String city) throws InvalidDataException{
		this.setLabel(label);
		this.setAddress(address);
		this.setCountry(country);
		this.setProvince(province);
		this.setPostalCode(postalcode);
		this.setCity(city);
	}
	
	/**
	 * sets shipping label if it is not null
	 * @param label  {@link String}
	 * @throws InvalidDataException
	 */
	private void setLabel(String label) throws InvalidDataException{
		if(label == null || label.length() == 0){
			throw new InvalidDataException("Invalid Shippinglabel");
		}
		this.label = label;
	}

	/**
	 * sets address if it is not null
	 * @param address  {@link String}
	 * @throws InvalidDataException
	 */
	private void setAddress(String address) throws InvalidDataException{
		if(address == null || address.length() == 0){
			throw new InvalidDataException("Invalid address");
		}
		this.address = address;
	}

	/**
	 * sets country if its not null
	 * @param country  {@link String}
	 * @throws InvalidDataException
	 */
	private void setCountry(String country) throws InvalidDataException{
		if(country == null || country.length() == 0){
			throw new InvalidDataException("Invalid country");
		}
		this.country = country;
	}

	/**
	 * sets province if it is not null
	 * @param province  {@link String}
	 * @throws InvalidDataException
	 */
	private void setProvince(String province) throws InvalidDataException{
		if(province == null || province.length() == 0){
			throw new InvalidDataException("Invalid province");
		}
		this.province = province;
	}

	/**
	 * sets postal code if it is not null
	 * @param postalcode
	 * @throws InvalidDataException
	 */
	private void setPostalCode(String postalcode) throws InvalidDataException{
		if(postalcode == null || postalcode.length() == 0){
			throw new InvalidDataException("Invalid postalcode");
		}
		this.postalcode = postalcode;
	}

	/**
	 * sets city if it is not null
	 * @param city
	 */
	private void setCity(String city){
		this.city = city;
	}

	/**
	 * sets order if it is not null
	 * @param order
	 * @throws InvalidDataException
	 */
	private void setOrder(Order order) throws InvalidDataException{
		if(order == null){
			throw new InvalidDataException("Invalid city");
		}
		this.order = order;
	}
	
	
	/**
	 * @return shipping address id  {@link String}
	 */
	public String getShippingAddressId() {
		return shippingAddressId;
	}

	/**
	 * @return shipping label {@link String}
	 */
	public String getlabel() {
		return label;
	}

	/**
	 * @return shipping address  {@link String}
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return country  {@link String}
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return province  {@link String}
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @return city  {@link String}
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return postalcode  {@link String}
	 */
	public String getPostalcode() {
		return postalcode;
	}

	@Override
	public boolean equals(Object shippingAddress) {
		if(shippingAddress instanceof ShippingAddress){
			return (hashCode() == ((ShippingAddress)shippingAddress).hashCode());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return (label+address+country+province+city+postalcode).hashCode();
	}

	@Override
	public String toString() {
		return "ShippingAddress [shippingAddressId=" + shippingAddressId + ", label=" + label + ", address=" + address
				+ ", country=" + country + ", province=" + province + ", city=" + city + ", postalcode=" + postalcode+ "]";
	}
	
	
}
