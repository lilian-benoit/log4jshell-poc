package fr.lbenoit.securite.log4shell;

/*-
 * #%L
 * log4shell-poc
 * %%
 * Copyright (C) 2021 Lilian BENOIT
 * %%
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
 * #L%
 */

import java.io.IOException;
import java.io.OutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class VulnerableLog4jExampleHandler implements HttpHandler {

	static Logger log = LogManager.getLogger(VulnerableLog4jExampleHandler.class.getName());

	public void handle(HttpExchange he) throws IOException {
		String userAgent = he.getRequestHeaders().getFirst("user-agent");

		log.info("Request user-agent: {}", userAgent);

		String response = "<h1>Voici le user-agent, " + userAgent + "!</h1>";
		he.sendResponseHeaders(200, response.length());
		OutputStream os = he.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
}
