package com.delivery.data.db.jpa.repositories;

import com.delivery.core.domain.Customer;
import com.delivery.core.usecases.customer.CustomerRepository;
import com.delivery.data.db.jpa.entities.CustomerData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {
    private final JpaCustomerRepository repository;

    @Override
    public Customer persist(Customer customer) {
        final CustomerData customerData = CustomerData.from(customer);
        return repository.save(customerData).fromThis();
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Optional<CustomerData> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<CustomerData> findById(Long id) {
        return repository.findById(id);
    }
}
