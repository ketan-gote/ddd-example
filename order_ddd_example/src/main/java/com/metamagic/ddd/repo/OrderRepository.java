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
package com.metamagic.ddd.repo;

import java.util.Collection;

import com.metamagic.ddd.entity.Order;

/**
 * Repository for handling order
 * @author ketangote
 *
 */
public interface OrderRepository {

	/**
	 * Saves order
	 * @param order
	 */
	public void saveOrder(Order order);
	
	/**
	 * Fetch order based on order id
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public Order findByOrderId(String orderId) throws Exception;
	
	/**
	 * Fetch all orders
	 * @return {@link Collection<Order>}
	 * @throws Exception
	 */
	public Collection<Order> findAllOrders() throws Exception;
	
}
