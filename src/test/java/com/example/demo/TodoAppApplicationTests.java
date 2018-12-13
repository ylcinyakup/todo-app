package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoAppApplicationTests {
	
	@Autowired
	CustomerRepository customerRepository;

	@Test
	public void findAllCustomerTest() {
		
		List<Customer> customers = customerRepository.findAll();
		System.out.println(customers.size());
		assertEquals("Customer size not match",599, customers.size());
	}
	
	@Test
	public void customRepoTest() {
		Map<String, Object> filters = new HashMap<>();
		filters.put("customerId", 2);
		
		List<Customer> customerList = customerRepository.getCustomers(filters);
		System.out.println(customerList.get(0).getFirstName());
		assertEquals("Customer size not match","PATRICIA", customerList.get(0).getFirstName());
	}

}
