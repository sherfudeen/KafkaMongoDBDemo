package com.sherfu.kafka.demo.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sherfu.kafka.demo.repository.UserInfoRepository;
import com.sherfu.kafka.demo.model.UserInfo;

@RestController
@RequestMapping("/rest/users")
public class UserResource {
	
	private UserInfoRepository userInfoRepository;
	
	public UserResource(UserInfoRepository userInfoRepository) 
	{
		this.userInfoRepository = userInfoRepository;
	}
	
	@GetMapping("/all")
	public List<UserInfo> getAll(){
		return userInfoRepository.findAll();
	}
	
	@GetMapping("/{userId}")
	public Optional<UserInfo> getUser(@PathVariable("userId") final Integer userId){
		return userInfoRepository.findById(userId);
	}
	
	
}
