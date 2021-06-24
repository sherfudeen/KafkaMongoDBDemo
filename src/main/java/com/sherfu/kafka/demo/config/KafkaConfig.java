package com.sherfu.kafka.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.errors.TimeoutException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import com.sherfu.kafka.demo.model.EmailPassword;
import com.sherfu.kafka.demo.model.UserInfo;


@EnableKafka
@Configuration
public class KafkaConfig {

	/*
	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> config = new HashMap<>();
		
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id_1");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<String, String>(config);
		//return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new StringDeserializer());
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
		factory.setConsumerFactory(consumerFactory());
		factory.setRetryTemplate(retryTemplate());
		
        factory.setRecoveryCallback((context -> {
            if(context.getLastThrowable().getCause() instanceof RecoverableDataAccessException)
            {
                //Recovery -> Put back on to the topic using a Kafka producer
            } else{

                // Error Handler
                throw new RuntimeException(context.getLastThrowable().getMessage());
            }
            return null;
        }));

		factory.setErrorHandler(
				(
					(exception, data) -> {
					System.out.println("Error in process with Exception {} and the record is {} "+exception+data);}
				)
		);
		
		
		return factory;
	}*/
	
	@Bean
	public ConsumerFactory<String, UserInfo> userConsumerFactory(){
		
		Map<String, Object> config = new HashMap<>();
		
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json_1");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(UserInfo.class));
		
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserInfo> userKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, UserInfo> factory = new ConcurrentKafkaListenerContainerFactory();
		factory.setConsumerFactory(userConsumerFactory());
		factory.setRetryTemplate(retryTemplate());

        factory.setRecoveryCallback((context -> {
            if(context.getLastThrowable().getCause() instanceof RecoverableDataAccessException)
            {
                //Recovery -> Put back on to the topic using a Kafka producer
            } else{

                // Error Handler
                throw new RuntimeException(context.getLastThrowable().getMessage());
            }
            return null;
        }));

		factory.setErrorHandler(
				(
					(exception, data) -> {
					System.out.println("Error in process with Exception {} and the record is {} "+exception+data);}
				)
		);
		
		return factory;
	}
	

	@Bean
	public ConsumerFactory<String, EmailPassword> emailConsumerFactory(){
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json_2");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(EmailPassword.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, EmailPassword> emailKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, EmailPassword> factory = new ConcurrentKafkaListenerContainerFactory();
		factory.setConsumerFactory(emailConsumerFactory());
		factory.setRetryTemplate(retryTemplate());

        factory.setRecoveryCallback((context -> {
            if(context.getLastThrowable().getCause() instanceof RecoverableDataAccessException)
            {
                //Recovery -> Put back on to the topic using a Kafka producer
            } else{

                // Error Handler
                throw new RuntimeException(context.getLastThrowable().getMessage());
            }
            return null;
        }));

		factory.setErrorHandler(
				(
					(exception, data) -> {
					System.out.println("Error in process with Exception {} and the record is {} "+exception+data);}
				)
		);
		
		return factory;
	}
	
	
    private RetryTemplate retryTemplate() {

		RetryTemplate retryTemplate = new RetryTemplate();
		/* here retry policy is used to set the number of attempts to retry and what exceptions you wanted to try and what you don't want to retry.*/
		retryTemplate.setRetryPolicy(getSimpleRetryPolicy());
		return retryTemplate;
    }

    private SimpleRetryPolicy getSimpleRetryPolicy() {
        Map<Class<? extends Throwable>, Boolean> exceptionMap = new HashMap<>();
        // the boolean value in the map determines whether exception should be retried
        exceptionMap.put(IllegalArgumentException.class, false);
        exceptionMap.put(TimeoutException.class, true);
        return new SimpleRetryPolicy(3,exceptionMap,true);
    }
	
}
