package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Customer;
import com.example.demo.model.Film;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.FilmService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoAppApplicationTests {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	FilmService filmService;

	@Test
	public void findAllCustomerTest() {
		
		List<Customer> customers = customerRepository.findAll();
		assertEquals("Customer size not match",599, customers.size());
	}
	
	@Test
	public void findAllFilmsTest() {
		
		Page<Film> films = filmService.findFilms(1,5,"ASC","length");
		for(Film film : films.getContent()) {
			System.out.println(film.getFilmId());
		}
	}
	
	@Test
	public void customRepoTest() {
		Map<String, Object> filters = new HashMap<>();
		filters.put("customerId", 2);
		
		List<Customer> customerList = customerRepository.getCustomers(filters);
		assertEquals("Customer Name not match","PATRICIA", customerList.get(0).getFirstName());
	}

}
