//package com.microservice.api_gateway.Filters;
//
//import com.microservice.api_gateway.utils.JwtUtil;
//import io.jsonwebtoken.Claims;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
//
//    @Autowired
//    private RouteValidator validator;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    public AuthenticationFilter() {
//        super(Config.class);
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return ((exchange, chain) -> {
//            ServerHttpRequest.Builder request = null;
//            if (validator.isSecured.test(exchange.getRequest())) {
//                //header contains token or not
//
//                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//                    throw new RuntimeException("missing authorization header");
//                }
//
//                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
//                if (authHeader != null && authHeader.startsWith("Bearer ")) {
//                    authHeader = authHeader.substring(7);
//                }
//
//                try {
//                    Claims claims = jwtUtil.validateAndGetClaim(authHeader);
//                    request = exchange.getRequest().mutate().header("loggedInUser", claims.getSubject());
//
//                } catch (Exception e) {
//                    System.out.println(e.getMessage());
//                    System.out.println("invalid access...!");
//                }
//
//            }
//            return chain.filter(exchange.mutate().request(request.build()).build());
//        });
//    }
//
//    public static class Config {
//
//    }
//}