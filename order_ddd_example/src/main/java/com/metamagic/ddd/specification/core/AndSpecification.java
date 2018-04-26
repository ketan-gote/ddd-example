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
 * 
 * @author ketangote
 *
 */
public class AndSpecification extends AbstractSpecification {

	private Specification spec1;
	private Specification spec2;

	public AndSpecification(final Specification spec1, final Specification spec2) {
		this.spec1 = spec1;
		this.spec2 = spec2;
	}

	@Override
	public boolean isValid(Object od) {
		return spec1.isValid(od) && spec2.isValid(od);
	}

}
