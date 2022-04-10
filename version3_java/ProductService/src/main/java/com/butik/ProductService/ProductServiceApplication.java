package com.butik.ProductService;

import com.butik.ProductService.command.interceptor.CreateProductCommandInterceptor;
import com.butik.ProductService.core.handler.error.ProductServiceEventsErrorHandler;
import com.butik.ProductService.core.handler.error.ProductServiceEventsErrorHandlerImpl;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductServiceApplication {

	@Autowired
	private ProductServiceEventsErrorHandler productServiceEventsErrorHandler;

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Autowired
	public void registerCreateProductCommandInterceptor(ApplicationContext context, CommandBus commandBus){
		commandBus.registerDispatchInterceptor(context.getBean(CreateProductCommandInterceptor.class));
	}

	@Autowired
	public void configure(EventProcessingConfigurer eventProcessingConfigurer){
		eventProcessingConfigurer.registerListenerInvocationErrorHandler("product-group", configuration -> {
			return productServiceEventsErrorHandler;
		});
	}
}
