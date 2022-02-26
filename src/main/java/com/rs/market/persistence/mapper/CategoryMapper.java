package com.rs.market.persistence.mapper;

import com.rs.market.domain.ItemCategory;
import com.rs.market.persistence.entity.Category;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "idCategory", target = "categoryId"),
            @Mapping(source = "description", target = "category"),
            @Mapping(source = "state", target = "active")
    })
    ItemCategory toItemCategory(Category category);

    @InheritInverseConfiguration
    @Mapping(target = "productList", ignore = true)
    Category toCategory(ItemCategory itemCategory);

}
