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

package com.metamagic.ddd.specification.core;

/**
 * Define different criteria specification pattern
 * @author ketangote
 *
 */
public abstract class AbstractSpecification implements Specification {

	public abstract boolean isValid(Object od);

	public Specification and(final Specification specification) {
		return (Specification) new AndSpecification(this, specification);
	}

	public Specification or(final Specification specification) {
		return new OrSpecification(this, specification);
	}

}
