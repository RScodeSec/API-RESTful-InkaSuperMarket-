package com.rs.market.persistence.mapper;

import com.rs.market.domain.Item;
import com.rs.market.persistence.entity.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = CategoryMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ItemMapper {

    @Mappings({
            @Mapping(source = "id", target = "productId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "idCategory", target = "categoriId"),
            @Mapping(source = "salePrice", target = "price"),
            @Mapping(source = "stockQuantity", target = "stock"),
            @Mapping(source = "state", target = "active"),
            @Mapping(source = "category", target = "itemCategory")
    })
    Item toItem(Product product);
    List<Item> toItems(List<Product> products);

    @InheritInverseConfiguration
    @Mapping(target = "barCode", ignore = true)
    Product toProduct(Item item);

}
