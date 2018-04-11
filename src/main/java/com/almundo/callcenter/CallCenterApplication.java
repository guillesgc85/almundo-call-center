package com.almundo.callcenter;


import com.almundo.callcenter.util.ConstantsUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

/**
 * The class CallCenterApplication.
 * This class is used to run Rest service application
 * @author <a href="guillesgc@gmail.com">Guillermo Garcia</a>
 * @version 1.0
 *
 */
@SpringBootApplication
public class CallCenterApplication {

	/**
	 * Main method to deploy microservice
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CallCenterApplication.class, args);
	}


	/**
	 * Only required due to defining myFactory in the receiver
	 *
	 * @param connectionFactory
	 * @param configurer
	 * @return factory
	 */
	@Bean
	public JmsListenerContainerFactory<?> myFactory(
			ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		return factory;
	}

	/**
	 * Serialize message content to json using TextMessage
	 * @return converter
	 */
	@Bean
	public MappingJackson2MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName(ConstantsUtil._TYPE);
		return converter;
	}

}
