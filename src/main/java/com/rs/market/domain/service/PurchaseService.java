package com.rs.market.domain.service;

import com.rs.market.domain.PurchaseDomain;
import com.rs.market.domain.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    private PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }
    public List<PurchaseDomain> getAll(){
        return purchaseRepository.getAll();
    }

    public Optional<List<PurchaseDomain>> getByClient(String clientId){
        return purchaseRepository.getByClient(clientId);
    }


    public PurchaseDomain save(PurchaseDomain purchaseDomain){
        return purchaseRepository.save(purchaseDomain);
    }
}
