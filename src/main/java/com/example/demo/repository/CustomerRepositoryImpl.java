package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Repository
@Transactional
@Slf4j
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

	@PersistenceUnit
	EntityManagerFactory emf;

	@Override
	public List<Customer> getCustomers(Map<String, Object> filters) {
		log.info("getCustomers starts");
		List<Customer> customerList = new ArrayList<Customer>();
		String sql = "SELECT * from CUSTOMER where 1=1 ";

		sql = buildSql(filters, sql);
		EntityManager em = emf.createEntityManager();;
		Query query = em.createNativeQuery(sql,Customer.class);
		customerList = query.getResultList();
		log.info("getCustomers - customers size = '{}' ", customerList.size());
		return customerList;
	}

	private String buildSql(Map<String, Object> filters, String sql) {
		for (Entry<String, Object> filter : filters.entrySet()) {
			String key = filter.getKey();
			Object value = filter.getValue();

			switch (key) {
			case "customerId":
				sql += " AND customer_id = " + value + " ";
				break;
			case "storeId":
				sql += " AND store_id = " + value + " ";
				break;
			case "firstName":
				sql += " AND first_name = '" + value + "' ";
				break;
			case "lastName":
				sql += " AND last_name = '" + value + "' ";
				break;
			case "email":
				sql += " AND email = '" + value + "' ";
				break;
			default:
				break;
			}
		}

		return sql;
	}

}
