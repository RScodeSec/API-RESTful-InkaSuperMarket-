package com.rs.market.persistence.mapper;

import com.rs.market.domain.PurchaseDomain;
import com.rs.market.persistence.entity.Purchase;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {
    @Mappings({
            @Mapping(source = "id", target = "purchaseId"),
            @Mapping(source = "idClient", target = "clientId"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "paymentMethod", target = "paymentMethod"),
            @Mapping(source = "coment", target = "comment"),
            @Mapping(source = "state", target = "state"),
            @Mapping(source = "products", target = "item")
    })
    PurchaseDomain toPurchaseDomain(Purchase purchase);
    List<PurchaseDomain> toPurchaseDomain(List<Purchase> purchases);

    @InheritInverseConfiguration
    @Mapping(target = "client", ignore = true)
    Purchase toPurchase(PurchaseDomain purchaseDomain);


}
