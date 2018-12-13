package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import com.example.demo.model.Customer;

public interface CustomerRepositoryCustom {
	
	List<Customer> getCustomers(Map<String, Object> filters);

}
