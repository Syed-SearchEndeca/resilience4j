package com.searchendeca.message;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "employee-exchange-service")
public interface MessageExchangeService {

	@GetMapping("/employee/details/{id}")
	public String getEmployeeMessage(@PathVariable String id);

}
