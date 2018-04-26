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

package com.metamagic.ddd.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ddd.dto.PaymentDTO;
import com.metamagic.ddd.dto.ShippingAddressDTO;
import com.metamagic.ddd.entity.Order;
import com.metamagic.ddd.exception.InvalidDataException;
import com.metamagic.ddd.service.OrderService;

/**
 * 
 * @author Ketan
 * Rest API for accessing order.
 * @see createOrder(..)
 * @see addShippingAddressDetails(..)
 * @see addPaymentDetails(..)
 */

@RestController
@RequestMapping(value = "/order")
public class OrderAPI {
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * Creates the order for user
	 * @param order {@link Order}
	 */
	@RequestMapping (value = "/createorder", method = RequestMethod.POST)
	public void createOrder(@RequestBody Order order){
		orderService.createOrder(order);
	}

	/**
	 * Adds shipping address to order
	 * @param dto {@link ShippingAddressDTO}
	 * @throws InvalidDataException
	 * @throws Exception
	 */
	@RequestMapping (value = "/addshippingaddress", method = RequestMethod.POST)
	public void addShippingAddressDetails(@RequestBody ShippingAddressDTO dto) throws InvalidDataException, Exception{
		orderService.addShippingAddressDetails(dto);
	}
	
	
	/**
	 * Adds payment details to order
	 * @param dto {@link PaymentDTO}
	 * @throws InvalidDataException
	 * @throws Exception
	 */
	@RequestMapping (value = "/addpaymentdetails", method = RequestMethod.POST)
	public void addPaymentDetails(@RequestBody PaymentDTO dto) throws InvalidDataException, Exception{
		orderService.addPaymentDetails(dto);
	}

	/**
	 * Check for discount based on specification given
	 * @throws Exception
	 */
	@RequestMapping (value = "/applyDiscount", method = RequestMethod.GET)
	public void applyDiscount() throws Exception{
		orderService.applyDiscount();
	}
	
}
