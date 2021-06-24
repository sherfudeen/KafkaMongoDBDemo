package com.sherfu.kafka.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.sherfu.kafka.demo.model.EmailPassword;
import com.sherfu.kafka.demo.model.UserInfo;
import com.sherfu.kafka.demo.repository.EmailPasswordRepository;
import com.sherfu.kafka.demo.repository.UserInfoRepository;

@EnableMongoRepositories(basePackageClasses = UserInfoRepository.class)
@Configuration
public class MongoDBConfig {
	UserInfoRepository userRepository;
	EmailPasswordRepository emailPasswordRepository;
	
	public MongoDBConfig(UserInfoRepository userRepository, EmailPasswordRepository emailPasswordRepository) {
		this.userRepository = userRepository;
		this.emailPasswordRepository = emailPasswordRepository;
	}

	public void saveUser(UserInfo user)
	{
		userRepository.save(user);
	}
	
	public void saveEmail(EmailPassword emailPassword)
	{
		emailPasswordRepository.save(emailPassword);
	}

}
