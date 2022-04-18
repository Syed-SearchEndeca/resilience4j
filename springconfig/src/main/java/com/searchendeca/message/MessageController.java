package com.searchendeca.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RefreshScope
public class MessageController {

	@Autowired
	private MessageExchangeService proxy;

	@Autowired
	private GlobalProperties global;

	@GetMapping("/message")
	public String getMessage() {
		return global.getGreetmsg();
	}

	@GetMapping("/employee/message/{id}")
	public String getEmployeeMessage(@PathVariable String id) {
		return id + ":" + global.getGreetmsg();
	}

	@GetMapping("/employee-feign/message/{id}")
	public String getEmployeeFeignMessage(@PathVariable String id) {
		String response = proxy.getEmployeeMessage(id);
		return response;
	}

	private Logger logger = LoggerFactory.getLogger(MessageController.class);

	@GetMapping("/message-api")
//@Retry(name = "message-api", fallbackMethod = "fallbackResponse")
	@CircuitBreaker(name = "default", fallbackMethod = "fallbackResponse")
	@RateLimiter(name = "default")
//@Bulkhead(name="message-api")
	public String sampleApi() {
		logger.info("1.Message api call received");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url",
				String.class);
		return forEntity.getBody();
		// return "message-api";
	}

	public String fallbackResponse(Exception ex) {
		logger.info("2.Message fall back call received");

		return "fallback-response";
	}

}
