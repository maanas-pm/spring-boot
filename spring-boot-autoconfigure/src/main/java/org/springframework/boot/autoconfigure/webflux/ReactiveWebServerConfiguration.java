/*
 * Copyright 2012-2017 the original author or authors.
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

package org.springframework.boot.autoconfigure.webflux;

import io.undertow.Undertow;
import reactor.ipc.netty.http.server.HttpServer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.embedded.ReactiveWebServerFactory;
import org.springframework.boot.context.embedded.jetty.JettyReactiveWebServerFactory;
import org.springframework.boot.context.embedded.reactor.ReactorNettyReactiveWebServerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatReactiveWebServerFactory;
import org.springframework.boot.context.embedded.undertow.UndertowReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * Configuration classes for reactive web servers
 * <p>Those should be {@code @Import} in a regular auto-configuration class
 * to guarantee their order of execution.
 *
 * @author Brian Clozel
 */
abstract class ReactiveWebServerConfiguration {

	@ConditionalOnMissingBean(ReactiveWebServerFactory.class)
	@ConditionalOnClass({HttpServer.class})
	static class ReactorNettyAutoConfiguration {
		@Bean
		public ReactorNettyReactiveWebServerFactory reactorNettyReactiveWebServerFactory() {
			return new ReactorNettyReactiveWebServerFactory();
		}
	}

	@ConditionalOnMissingBean(ReactiveWebServerFactory.class)
	@ConditionalOnClass({org.apache.catalina.startup.Tomcat.class})
	static class TomcatAutoConfiguration {
		@Bean
		public TomcatReactiveWebServerFactory tomcatReactiveWebServerFactory() {
			return new TomcatReactiveWebServerFactory();
		}
	}

	@ConditionalOnMissingBean(ReactiveWebServerFactory.class)
	@ConditionalOnClass({org.eclipse.jetty.server.Server.class})
	static class JettyAutoConfiguration {
		@Bean
		public JettyReactiveWebServerFactory jettyReactiveWebServerFactory() {
			return new JettyReactiveWebServerFactory();
		}
	}

	@ConditionalOnMissingBean(ReactiveWebServerFactory.class)
	@ConditionalOnClass({Undertow.class})
	static class UndertowAutoConfiguration {
		@Bean
		public UndertowReactiveWebServerFactory undertowReactiveWebServerFactory() {
			return new UndertowReactiveWebServerFactory();
		}

	}

}
