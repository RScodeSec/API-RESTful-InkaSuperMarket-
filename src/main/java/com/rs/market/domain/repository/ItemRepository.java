package com.rs.market.domain.repository;

import com.rs.market.domain.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    List<Item> getAll();
    Optional<List<Item>> getByCtegory(int categoriId);
    Optional<List<Item>> getScarseItem(int quantity);
    Optional<Item> getItem(int productId);
    Item save(Item item);
    void delete(int productId);
}
