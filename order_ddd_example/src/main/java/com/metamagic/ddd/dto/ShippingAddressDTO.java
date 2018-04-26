package com.metamagic.ddd.dto;

import org.springframework.web.bind.annotation.RequestBody;

import com.metamagic.ddd.api.OrderAPI;

/**
 * Shipping Address dto for adding shipping addresss, used as {@link RequestBody} for /order/addshippingaddress endpoint in {@link OrderAPI}
 * @author ketangote
 *
 */
public class ShippingAddressDTO {

	private String orderId;
	
	private String label;

	private String address;
	
	private String country;
	
	private String province;
	
	private String city;
	
	private String postalcode;

	/**
	 * 
	 * @param orderId
	 * @param label
	 * @param address
	 * @param country
	 * @param province
	 * @param city
	 * @param postalcode
	 */
	public ShippingAddressDTO(String orderId, String label, String address, String country, String province, String city, String postalcode){
			this.orderId = orderId;
			this.label = label;
			this.address = address;
			this.country = country;
			this.province = province;
			this.city = city;
			this.postalcode = postalcode;
	}

	public String getOrderId(){
		return orderId;
	}
	
	public String getLabel() {
		return label;
	}

	public String getAddress() {
		return address;
	}

	public String getCountry() {
		return country;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getPostalcode() {
		return postalcode;
	}
	
	@Override
	public boolean equals(Object obj) {
		return hashCode() == ((ShippingAddressDTO)obj).hashCode();
	}
	
	@Override
	public int hashCode() {
		return (label+address+country+province+city+postalcode).hashCode();
	}
}
