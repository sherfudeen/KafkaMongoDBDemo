package com.sherfu.kafka.demo.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sherfu.kafka.demo.model.EmailPassword;
import com.sherfu.kafka.demo.model.UserInfo;
import com.sherfu.kafka.demo.repository.EmailPasswordRepository;

@RestController
@RequestMapping("/rest/email")
public class EmailResource {

	EmailPasswordRepository emailPasswordRepository;
	
	public EmailResource(EmailPasswordRepository emailPasswordRepository) {
		this.emailPasswordRepository = emailPasswordRepository;
	}

	@GetMapping("/all")
	public List<EmailPassword> getAll(){
		return emailPasswordRepository.findAll();
	}

	@GetMapping("/{email}")
	public Optional<EmailPassword> getEmail(@PathVariable("email") final String email){
		return emailPasswordRepository.findById(email);
	}
}
