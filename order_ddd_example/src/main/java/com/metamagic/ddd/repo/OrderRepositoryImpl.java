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
import java.util.Iterator;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.metamagic.ddd.entity.Order;
import com.metamagic.ddd.entity.Order.Status;
import com.metamagic.ddd.specification.OrderStatusSpecification;
import com.metamagic.ddd.specification.core.Specification;

/**
 * Repository for handling order implements {@link OrderRepository}
 * @author ketangote
 *
 */

@Repository
public class OrderRepositoryImpl implements OrderRepository{

	@Autowired
	PersistenceManagerFactory pmf;

	private PersistenceManager pm() {
		return pmf.getPersistenceManager();
	}

	/**
	 * Saves order
	 */
	@Override
	public void saveOrder(Order order) {
		PersistenceManager pm = pm();
		pm.setDetachAllOnCommit(true);
		pm.makePersistent(order);
		pm.close();
	}
	
	/**
	 * Fetch order based on order id
	 */
	@Override
	public Order findByOrderId(String orderId) throws Exception {
		PersistenceManager pm = pm();
		Query query = (pm.newQuery(Order.class));
		query.setFilter("this.orderId==:orderId");
		query.setUnique(true);
		Order order = (Order)query.execute(orderId, true);
		if(order != null){
			return order;	
		}else{
			throw new Exception("Unable to retrive data");
		}
	}
	
	/**
	 * Fetch all orders
	 * @return {@link Collection<Order>}
	 * @throws Exception
	 */
	@Override
	public Collection<Order> findAllOrders() throws Exception {
		PersistenceManager pm = pm();
		Query query = (pm.newQuery(Order.class));
		Collection<Order> orders = (Collection<Order>)query.execute(true);
		return orders;
	}
	
	
}
