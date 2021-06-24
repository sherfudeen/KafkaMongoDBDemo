package com.sherfu.kafka.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sherfu.kafka.demo.model.UserInfo;

public interface UserInfoRepository extends MongoRepository<UserInfo, Integer> {
	
}
