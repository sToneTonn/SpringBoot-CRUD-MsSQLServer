package com.example.crudTest.Custommer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

//    private CustomerRepository customerRepository;

//    @Autowired
//    public CustomerService(CustomerRepository repository) {
//        this.customerRepository = repository;
//    }

    @Autowired  private  CustomerRepository customerRepository;

    public List<Customer> retrieveCustomer() {

        return (List<Customer>) customerRepository.findAll();
    }

    public Optional<Customer> retrieveCustomer(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> retrieveCustomer(String name) {
        return customerRepository.findByFirstName(name);
    }

    @Transactional
    public  Customer createCustomer(Customer customer) {
        System.out.println("body customer"+customer);
        customer.setId(null);
        return customerRepository.save(customer);
    }

    @Transactional
    public Optional<Customer> updateCustomer(Long id, Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(!customerOptional.isPresent()) {
            return customerOptional;
        }
        customer.setId(id);
        return Optional.of(customerRepository.save(customer));
    }

    @Transactional
    public boolean deleteCustomer(Long id) {
        try {
            customerRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
