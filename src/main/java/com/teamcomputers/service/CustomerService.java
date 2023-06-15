package com.teamcomputers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.CustomerDto;
import com.teamcomputers.dto.CustomerFilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.model.Customer;
import com.teamcomputers.repository.CustomerRepository;
import com.teamcomputers.repository.UserRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private UserRepository userRepository;

	public Customer add(CustomerDto customerDto) {

		Customer customer = new Customer();

		customer.setCustomerId(customerDto.getCustomerId());
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setEmail(customerDto.getEmail());
		customer.setContact(customerDto.getContact());
		customer.setAddress(customerDto.getAddress());
		customer.setState(customerDto.getState());
		customer.setCity(customerDto.getCity());
		customer.setPostcode(customerDto.getPostcode());
		customer.setCreatedBy(userRepository.findById((int)(customerDto.getCreatedBy())).orElse(null));
		customer.setCreatedDate(customerDto.getCreatedDate());
		customer.setUpdatedBy(userRepository.findById((int)(customerDto.getUpdatedBy())).orElse(null));
		customer.setUpdatedDate(customerDto.getUpdatedDate());
		customer.setStatus(customerDto.isStatus());

		return customerRepository.save(customer);
	}

	public Customer getById(int customerId) {
		return customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Id :" + customerId + "Unavailable"));
	}

	public Customer update(CustomerDto customerDto) {

		Customer customer = new Customer();

		customer.setCustomerId(customerDto.getCustomerId());
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setEmail(customerDto.getEmail());
		customer.setContact(customerDto.getContact());
		customer.setAddress(customerDto.getAddress());
		customer.setState(customerDto.getState());
		customer.setCity(customerDto.getCity());
		customer.setPostcode(customerDto.getPostcode());
		customer.setCreatedBy(userRepository.findById((int)(customerDto.getCreatedBy())).orElse(null));
		customer.setCreatedDate(customerDto.getCreatedDate());
		customer.setUpdatedBy(userRepository.findById((int)(customerDto.getUpdatedBy())).orElse(null));
		customer.setUpdatedDate(customerDto.getUpdatedDate());
		customer.setStatus(customerDto.isStatus());

		return customerRepository.save(customer);
	}

	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

//check active user with ascending order	
	public List<CustomerFilterDto> getActiveUsers() {
		List<Customer> user = customerRepository.findByStatusOrderByFirstNameAsc(true);
		List<CustomerFilterDto> filteredCategories = new ArrayList<>();

		for (Customer customer : user) {
			CustomerFilterDto filtered = new CustomerFilterDto();

			filtered.setCustomerId(customer.getCustomerId());
			filtered.setFirstName(customer.getFirstName());
			filteredCategories.add(filtered);
		}

		return filteredCategories;
	}

	public boolean deleteById(int customerId) throws NotFoundException {
		Customer customer = customerRepository.findById(customerId).orElseThrow(
				() -> new ResourceNotFoundException("customer Id : " + customerId + " is Not Present in DataBase"));
		customer.setStatus(false); // Update status to false
		customerRepository.save(customer);
		return true;
	}

//	public boolean deleteById(int id) {
//		customerRepository.deleteById(id);
//		return true;
//	}

}
