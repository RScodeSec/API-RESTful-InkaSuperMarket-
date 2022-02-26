package com.rs.market.domain.service;


import com.rs.market.domain.Item;
import com.rs.market.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAll(){
        return itemRepository.getAll();
    }

    public Optional<Item> getItem(int itemId){
        return itemRepository.getItem(itemId);
    }

    public Optional<List<Item>> getByCategory(int categoryId){
        return itemRepository.getByCtegory(categoryId);
    }

    public Item save(Item item){
        return itemRepository.save(item);
    }

    public boolean delete(int itemId){
        return getItem(itemId).map(prod->{
            itemRepository.delete(itemId);
            return true;
        }).orElse(false);
    }


}
