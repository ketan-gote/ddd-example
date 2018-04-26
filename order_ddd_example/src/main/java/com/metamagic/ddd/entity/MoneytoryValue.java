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

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * Embedded value object for {@link Order}
 * @author ketangote
 *
 */
@PersistenceCapable(detachable="true", embeddedOnly="true")
public class MoneytoryValue implements Serializable {

	@Persistent( column = "grandtotal")
	private Double total;
	
	@Persistent( column = "unit")
	private String unit;
	
	/**
	 * 
	 * @param total
	 * @param unit
	 */
	public MoneytoryValue(Double total, String unit){
		this.total = total;
		this.unit = unit;
	}
	
	/**
	 * 
	 * @return total {@link Double}
	 */
	public Double getTotal(){
		return this.total;
	}
	
	/**
	 * 
	 * @return unit {@link String}
	 */
	public String getUnit(){
		return unit;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof MoneytoryValue){
			MoneytoryValue moneytoryValue = (MoneytoryValue)obj;
			return (moneytoryValue.getTotal().equals(total) && moneytoryValue.getUnit().equals(unit));
		}
		return super.equals(obj);
	}
	
	
	@Override
	public String toString() {
		return "MoneytoryValue [total=" + total + ", unit=" + unit + "]";
	}
	
	
}
