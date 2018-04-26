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

/**
 * 
 * @author ketangote
 * Bean used to send custom format message for rest endpoint.
 */
public class ResponseBean {

	private boolean success;
		
    private String message;

    private Object data;
    
    /**
     * 
     * @param success {@link Boolean}
     * @param message {@link String}
     */
    public ResponseBean(boolean success, String message){
    	this.success = success;
    	this.message = message;
    }
    
    /**
     * 
     * @param success {@link Boolean}
     * @param message {@link String}
     * @param data {@link Object}
     * 
     */
    public ResponseBean(boolean success, String message, Object data){
    	this(success, message);
    	this.data = data;
    }

    /**
     * 
     * @return {@link Boolean}
     */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * 
	 * @return {@link String}
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 
	 * @return {@link Object}
	 */
	public Object getData() {
		return data;
	}

    
}
