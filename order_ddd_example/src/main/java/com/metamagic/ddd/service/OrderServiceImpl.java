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

package com.metamagic.ddd.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.ddd.dto.PaymentDTO;
import com.metamagic.ddd.dto.ShippingAddressDTO;
import com.metamagic.ddd.entity.Order;
import com.metamagic.ddd.entity.Order.Status;
import com.metamagic.ddd.exception.InvalidDataException;
import com.metamagic.ddd.repo.OrderRepository;
import com.metamagic.ddd.specification.OrderAmountSpecification;
import com.metamagic.ddd.specification.OrderStatusSpecification;
import com.metamagic.ddd.specification.core.Specification;

/**
 * Domain Service which specifically focus on domain logic of order, implements {@link OrderService}
 * @author ketan gote
 *
 */

@Service
public class OrderServiceImpl implements OrderService
{
	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * creates the order
	 * @param order
	 */

	@Override
	public void createOrder(Order order) {
		orderRepository.saveOrder(order);
	}
	
	/**
	 * adds shipping address to order
	 * @param dto
	 * @throws InvalidDataException
	 * @throws Exception
	 */
	@Override
	public void addShippingAddressDetails(ShippingAddressDTO dto) throws Exception {
		Order order = orderRepository.findByOrderId(dto.getOrderId());
		order.addShippingAddress(dto.getLabel(), dto.getAddress(), dto.getCountry(), dto.getProvince(), dto.getPostalcode(), dto.getCity());
		orderRepository.saveOrder(order);		
	}
	
	/**
	 * adds payment details to order
	 * @param dto
	 * @throws InvalidDataException
	 * @throws Exception
	 */
	@Override
	public void addPaymentDetails(PaymentDTO dto) throws InvalidDataException, Exception {
		Order order = orderRepository.findByOrderId(dto.getOrderId());
		order.addPaymentDetails(dto.getPaymentmode());
		orderRepository.saveOrder(order);	
	}
	
	/**
	 * Apply the discount based on specification specified
	 */
	@Override
	public void applyDiscount() throws Exception 
	{
		
		Collection<Order> orders = orderRepository.findAllOrders();

		Specification spe = this.discountSpecification();
		
		orders.stream()
				.filter(order -> spe.isValid(order))
				.forEach(order -> {
					System.err.println(" Apply discount to order - "+order.getOrderId() 
																	  +" "+order.getStatus() 
																	  +" "+order.getMoneytoryValue().getTotal());
				});
	}

	/**
	 * Return discount specification
	 * @return {@link Specification} 
	 */
	private Specification discountSpecification(){
		return new OrderStatusSpecification(Status.PREPARING).
				or(new OrderStatusSpecification(Status.PAYMENT_EXPECTED))
				.and(new OrderAmountSpecification(5000.00));
	}
}
