package com.cos.ideasOrOld;

import java.util.Optional;

import com.cos.daos.CustomerDao;
import com.cos.daos.Dao;
import com.cos.models.Customer;

public class CustomerApplication {

    private static Dao<Customer> customerDao;

    public static void main(String[] args) {
        customerDao = new CustomerDao();

        Customer customer1 = getCustomer(0);
        System.out.println(customer1);
        customerDao.update(customer1, new String[]{"Jake", "test", "jake@domain.com"});

        Customer customer2 = getCustomer(1);
        customerDao.delete(customer2);
        customerDao.save(new Customer("Julie", "test","julie@domain.com"));

        customerDao.getAll().forEach(customer -> System.out.println(customer.getFname()));
    }

    private static Customer getCustomer(long id) {
        Optional<Customer> customer = customerDao.get(id);

        return customer.orElseGet(
                () -> new Customer("non-existing fname", "non-existing lname", "no-email"));
    }
}