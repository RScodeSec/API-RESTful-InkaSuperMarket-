package com.rs.market.persistence;

import com.rs.market.domain.Item;
import com.rs.market.domain.repository.ItemRepository;
import com.rs.market.persistence.crud.ProductCrudRepository;
import com.rs.market.persistence.entity.Product;
import com.rs.market.persistence.mapper.ItemMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements ItemRepository {

    private ProductCrudRepository productCrudRepository;

    private ItemMapper mapper;

    public ProductRepository(ProductCrudRepository productCrudRepository, ItemMapper mapper) {
        this.productCrudRepository = productCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Item> getAll(){
        List<Product> products = (List<Product>) productCrudRepository.findAll();
        return mapper.toItems(products);
    }

    @Override
    public Optional<List<Item>> getByCtegory(int categoriId) {
        List<Product> products = productCrudRepository.findByIdCategoryOrderByNameAsc(categoriId);
        return Optional.of(mapper.toItems(products));
    }

    @Override
    public Optional<List<Item>> getScarseItem(int quantity) {
        Optional<List<Product>> products = productCrudRepository.findByStockQuantityLessThanAndState(quantity, true);
        return products.map(prods-> mapper.toItems(prods));
    }

    @Override
    public Optional<Item> getItem(int productId) {

        return productCrudRepository.findById(productId).map(prod->mapper.toItem(prod));
    }

    @Override
    public Item save(Item item) {
        Product product = mapper.toProduct(item);
        return mapper.toItem(productCrudRepository.save(product));
    }

    @Override
    public void delete(int idProducto){
        productCrudRepository.deleteById(idProducto);
    }

}
