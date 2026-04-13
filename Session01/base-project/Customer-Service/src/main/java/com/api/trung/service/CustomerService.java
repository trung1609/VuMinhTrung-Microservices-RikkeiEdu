package com.api.trung.service;

import com.api.trung.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAllCustomers();

    Customer findCustomerById(Long id) throws Exception;

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Long id, Customer customer) throws Exception;

    void deleteCustomer(Long id) throws Exception;
}
