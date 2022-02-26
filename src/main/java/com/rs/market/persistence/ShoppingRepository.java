package com.rs.market.persistence;

import com.rs.market.domain.PurchaseDomain;
import com.rs.market.domain.repository.PurchaseRepository;
import com.rs.market.persistence.crud.ShoppingCrudRepositoty;
import com.rs.market.persistence.entity.Purchase;
import com.rs.market.persistence.mapper.PurchaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ShoppingRepository implements PurchaseRepository {

    private ShoppingCrudRepositoty shoppingCrudRepositoty;
    private PurchaseMapper mapper;

    public ShoppingRepository(ShoppingCrudRepositoty shoppingCrudRepositoty, PurchaseMapper mapper) {
        this.shoppingCrudRepositoty = shoppingCrudRepositoty;
        this.mapper = mapper;
    }

    @Override
    public List<PurchaseDomain> getAll() {
        List<Purchase> purchases = (List<Purchase>) shoppingCrudRepositoty.findAll();
        return  mapper.toPurchaseDomain(purchases);

    }

    @Override
    public Optional<List<PurchaseDomain>> getByClient(String clientId) {
        return shoppingCrudRepositoty.findByIdClient(clientId)
                .map(purchases -> mapper.toPurchaseDomain(purchases));
    }

    @Override
    public PurchaseDomain save(PurchaseDomain purchaseDomain) {

        Purchase purchase = mapper.toPurchase(purchaseDomain);
        purchase.getProducts().forEach(prods->prods.setPurchase(purchase));

        return mapper.toPurchaseDomain(shoppingCrudRepositoty.save(purchase));
    }
}
