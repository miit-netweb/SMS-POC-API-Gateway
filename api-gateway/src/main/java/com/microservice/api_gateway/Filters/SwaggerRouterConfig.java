package com.microservice.api_gateway.Filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class SwaggerRouterConfig {

	Logger logger = LoggerFactory.getLogger(SwaggerRouterConfig.class);

	@Autowired
    private DiscoveryClient discoveryClient;
	
//	@Autowired
//	private LoadBalancerClient loadBalancerClient;

	@Value("${swagger.routes.enrollment-service}")
	private String enrollmentServiceRoute;

	@Value("${swagger.routes.partner-service}")
	private String partnerServiceRoute;

	@Value("${swagger.routes.token-service}")
	private String tokenServiceRoute;

	@Value("${swagger.routes.get-subscriber-details}")
	private String subscriberDetailsServiceRoute;

	@Value("${swagger.routes.thirdparty-entity}")
	private String thirdPartyServiceRoute;

	private String enrollmentUrl = "http://localhost:8300";
	private String partnerUrl = "http://localhost:8500";
	private String tokenUrl = "http://localhost:8400";
	private String subscriberUrl = "http://localhost:8800";
	private String thirdPartyUrl = "http://localhost:8700";

	@Bean
	public RouterFunction<ServerResponse> serviceSwaggerRoutes() {
		logger.info("Base Url of Enrollment Service from Eureka ", enrollmentUrl);
		return RouterFunctions
				.route(RequestPredicates.path(enrollmentServiceRoute), HandlerFunctions.http(enrollmentUrl))
				.andRoute(RequestPredicates.path(partnerServiceRoute), HandlerFunctions.http(partnerUrl))
				.andRoute(RequestPredicates.path(tokenServiceRoute), HandlerFunctions.http(tokenUrl))
				.andRoute(RequestPredicates.path(subscriberDetailsServiceRoute), HandlerFunctions.http(subscriberUrl))
				.andRoute(RequestPredicates.path(thirdPartyServiceRoute), HandlerFunctions.http(thirdPartyUrl));
	}

	public String getServiceBaseUrl(String serviceName) {
        return discoveryClient.getInstances(serviceName)
                .stream()
                .findFirst()
                .map(serviceInstance -> serviceInstance.getUri().toString())
                .orElseThrow(() -> new RuntimeException("Service not available: " + serviceName));
    }
}