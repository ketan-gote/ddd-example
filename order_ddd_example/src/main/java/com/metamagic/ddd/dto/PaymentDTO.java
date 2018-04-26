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
package com.metamagic.ddd.dto;

import org.springframework.web.bind.annotation.RequestBody;

import com.metamagic.ddd.api.OrderAPI;

/**
 * Payment dto for adding payment, used as {@link RequestBody} for /order/addpaymentdetails endpoint in {@link OrderAPI}
 * @author ketangote
 *
 */
public class PaymentDTO {

	private String orderId;

	private String paymentmode;
	
	/**
	 * 
	 * @param orderId
	 * @param paymentmode
	 */
	public PaymentDTO(String orderId, String paymentmode){
		this.orderId = orderId;
		this.paymentmode = paymentmode;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getPaymentmode() {
		return paymentmode;
	}
	
	
}
