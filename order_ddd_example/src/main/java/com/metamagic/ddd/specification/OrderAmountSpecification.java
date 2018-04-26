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

package com.metamagic.ddd.specification;

import com.metamagic.ddd.entity.Order;
import com.metamagic.ddd.specification.core.AbstractSpecification;

/**
 * Specification for order amount
 * @author ketangote
 *
 */
public class OrderAmountSpecification  extends AbstractSpecification{

	private Double amount;
	
	public OrderAmountSpecification(Double amount){
		this.amount = amount;
	}

	@Override
	public boolean isValid(Object obj) {
		return ((Order)obj).getMoneytoryValue().getTotal() > amount;
	}
}