# API Gateway for Subscriber Management System (SMS)

## Overview

This repository contains the code for the API Gateway of the Subscriber Management System (SMS). The API Gateway serves as the single entry point for all client requests and routes them to the appropriate microservices. It provides a way to encapsulate the internal architecture and to ensure secure and efficient communication between clients and backend services.

## Architecture

- **Spring Boot**: The core framework used to build the API Gateway.
- **Eureka Client**: Registers with the Eureka Server to discover other services.

## Features

- **Routing**: Routes incoming requests to the appropriate microservices.
- **Load Balancing**: Distributes requests evenly across instances of microservices.
- **Security**: Secures endpoints and manages authentication tokens.



## Configuration

The configuration for the API Gateway is primarily done through `application.yml` or `application.properties`. Below is an example configuration in `application.yml`:

```yaml
server:
  port: 8080

spring:
  application:
    name: api-gateway

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
    fetchRegistry: true
    registerWithEureka: true

