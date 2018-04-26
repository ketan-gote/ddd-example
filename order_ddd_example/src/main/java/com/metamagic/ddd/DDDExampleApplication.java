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


package com.metamagic.ddd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Central application class containing both general application and web configuration as well as a main-method to
 * bootstrap the application using Spring Boot.
 * 
 * @see #main(String[])
 * @see SpringApplication
 * @author Ketan Gote
 */

@SpringBootApplication
public class DDDExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DDDExampleApplication.class, args);
	}
}
