package com.sherfu.kafka.demo.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sherfu.kafka.demo.config.MongoDBConfig;
import com.sherfu.kafka.demo.model.EmailPassword;
import com.sherfu.kafka.demo.model.UserInfo;

@Service
public class KafkaConsumer {
	
	MongoDBConfig mongoDBConfig;
	
	
	public KafkaConsumer(MongoDBConfig mongoDBConfig) {
		this.mongoDBConfig = mongoDBConfig;
	}

	/*
	@KafkaListener(topics = "TestTopic", groupId  = "group_id_1")
	public void consume(String message) {
		System.out.println("Consumed Message :" +message);
		
	}*/
	
	
	@KafkaListener(topics = "UserInfo_Json", groupId  = "group_json_1",
			containerFactory = "userKafkaListenerContainerFactory")
	public void consumeUserJSon(UserInfo userInfo) {
		System.out.println("Consumed User Json Message :" + userInfo.toString());
		mongoDBConfig.saveUser(userInfo);
	}	
	
	
	@KafkaListener(topics = "EmailPassword_Json", groupId  = "group_json_2",
			containerFactory = "emailKafkaListenerContainerFactory")
	public void consumeEmailJSon(EmailPassword emailPassword) {
		System.out.println("Consumed Email Json Message :" + emailPassword.toString());
		mongoDBConfig.saveEmail(emailPassword);
	}	
	

}
