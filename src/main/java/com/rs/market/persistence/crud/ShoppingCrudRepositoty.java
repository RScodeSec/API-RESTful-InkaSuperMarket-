package com.rs.market.persistence.crud;

import com.rs.market.persistence.entity.Purchase;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingCrudRepositoty extends CrudRepository<Purchase,Integer> {
    Optional<List<Purchase>> findByIdClient(String clientId);
}
