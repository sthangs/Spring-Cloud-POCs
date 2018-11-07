package com.westuc.icrm.gateway.filters;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class MyRedirectionGatewayFilterFactory extends AbstractGatewayFilterFactory {

	@Override
	public GatewayFilter apply(Object config) {
		// grab configuration from Config object
		return (exchange, chain) -> {
			System.out.println(">>>>>>>>>>>>>Before going to IOL");
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				ServerHttpResponse response = exchange.getResponse();
				//Manipulate the response in some way
				// Manipulate the response in some way
				System.out.println(">>>>>>>>>>>>>in response filter");
				if (response.getStatusCode().is3xxRedirection()) {
					URL locationURL;
					try {
						locationURL = response.getHeaders().getLocation().toURL();
						System.out.println(">>>>>>>>>>>>>locationURL" + locationURL);
						String host = locationURL.getHost();
						System.out.println(">>>>>>>>>>>>>Host" + host);
						String gatewayLocation = locationURL.toExternalForm().replaceFirst(host,
								exchange.getRequest().getHeaders().getHost().getHostName());
						System.out.println(">>>>>>>>>>>>>gatewayLocation" + gatewayLocation);
						response.getHeaders().setLocation(new URL(gatewayLocation).toURI());

					} catch (MalformedURLException | URISyntaxException e) {
						e.printStackTrace();
					}

				}
			}));
		};
	}

	public static class Config {
	       //Put the configuration properties for your filter here
	}

}
