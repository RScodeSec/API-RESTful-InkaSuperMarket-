package com.rs.market.web.controller;

import com.rs.market.domain.Item;
import com.rs.market.domain.service.ItemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/item")
public class ItemController {
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/all")
    @ApiOperation("Get all supermarket items")
    public ResponseEntity<List<Item>> getAll(){
        return new ResponseEntity<>(itemService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a item by ID")
    public ResponseEntity<Item> getItem(@PathVariable("id") int itemId){
        return itemService.getItem(itemId)
                .map(prods->new ResponseEntity<>(prods, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{id}")
    @ApiOperation("Get all item by category ID")
    public ResponseEntity<List<Item>> getByCategory(@PathVariable("id") int categoryId){
        return itemService.getByCategory(categoryId)
                .map(prods->new ResponseEntity<>(prods, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @ApiOperation("Add an item")
    public ResponseEntity<Item> save(@RequestBody Item item){
        return new ResponseEntity<>(itemService.save(item), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete an item by ID")
    public ResponseEntity  delete(@PathVariable("id") int itemid){
        if(itemService.delete(itemid)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

     }
}
