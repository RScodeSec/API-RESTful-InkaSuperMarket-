package com.rs.market.persistence.mapper;

import com.rs.market.domain.PurchaseItem;
import com.rs.market.persistence.entity.ShoppingProduct;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "id.idProduct", target = "itemId"),
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(source = "total", target = "total"),
            @Mapping(source = "state", target = "active")
    })
    PurchaseItem toPurchaseItem(ShoppingProduct shoppingProduct);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "purchase", ignore = true),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "id.idShop", ignore = true),
    })
    ShoppingProduct toChoppingProduct(PurchaseItem purchaseItem);
}
