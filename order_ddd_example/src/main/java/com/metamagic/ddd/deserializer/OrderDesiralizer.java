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

package com.metamagic.ddd.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.metamagic.ddd.entity.Order;
import com.metamagic.ddd.exception.InvalidDataException;

/**
 * 
 * @author ketan gote
 * Custom deserializer for {@link Order}
 * 
 */

public class OrderDesiralizer extends JsonDeserializer<Order>{
	
	@Override
	public Order deserialize(JsonParser jsonParser, DeserializationContext des) throws IOException, JsonProcessingException {
		
		ObjectCodec oc = jsonParser.getCodec();
	    JsonNode ordernode = oc.readTree(jsonParser);
     
		try {
			Order order = new Order(ordernode.get("userId").asText());
			JsonNode shippingAddressNode = ordernode.get("shippingAddress");
			if(shippingAddressNode !=null){
				
				order.addShippingAddress(shippingAddressNode.get("shippinglabel").asText(), 
						shippingAddressNode.get("address").asText(), shippingAddressNode.get("country").asText(), 
						shippingAddressNode.get("province").asText(), shippingAddressNode.get("postalcode").asText(), 
						shippingAddressNode.get("city").asText());
			}
			
			JsonNode lineItemsNodes = ordernode.withArray("lineitems");
			
			for (JsonNode lineItemsNode : lineItemsNodes) {
				order.addLineItem(lineItemsNode.get("itemid").asText(), lineItemsNode.get("itemname").asText(), 
						lineItemsNode.get("price").asDouble(), lineItemsNode.get("quantity").asInt());
			}
			
			JsonNode paymentNode = ordernode.get("payment");
			
			if(paymentNode !=null){
				order.addPaymentDetails(paymentNode.get("paymentmode").asText());
			}
			
		    return order;
		} catch (InvalidDataException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
