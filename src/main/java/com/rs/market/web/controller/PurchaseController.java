package com.rs.market.web.controller;

import com.rs.market.domain.PurchaseDomain;
import com.rs.market.domain.service.PurchaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }


    @GetMapping("/all")
    @ApiOperation("Get all supermarket purchases")
    public ResponseEntity<List<PurchaseDomain>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/client/{id}")
    @ApiOperation("Get all purchases by client ID  \uD83D\uDC41️ operation only authenticated user")
    public ResponseEntity<List<PurchaseDomain>> PurchaseByCliente(@PathVariable("id") String clientId){

        return purchaseService.getByClient(clientId)
                .map(prods->new ResponseEntity<>(prods, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/save")
    @ApiOperation("Save a purchase of client \uD83D\uDC41️ operation only authenticated user")
    public ResponseEntity<PurchaseDomain> save(@RequestBody PurchaseDomain purchaseDomain){
        return new ResponseEntity<>(purchaseService.save(purchaseDomain), HttpStatus.CREATED);
    }
}
