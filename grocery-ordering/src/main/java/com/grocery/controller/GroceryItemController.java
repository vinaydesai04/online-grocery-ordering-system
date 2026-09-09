package com.grocery.controller;

import com.grocery.model.GroceryItem;
import com.grocery.service.GroceryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grocery-items")
public class GroceryItemController {

    @Autowired
    private GroceryItemService groceryItemService;

    @PostMapping
    public ResponseEntity<GroceryItem> createGroceryItem(@RequestBody GroceryItem item) {
        return new ResponseEntity<>(groceryItemService.createGroceryItem(item), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GroceryItem>> getAllGroceryItems() {
        return ResponseEntity.ok(groceryItemService.getAllGroceryItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroceryItem> getGroceryItemById(@PathVariable Long id) {
        return ResponseEntity.ok(groceryItemService.getGroceryItemById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroceryItem> updateGroceryItem(@PathVariable Long id, @RequestBody GroceryItem itemDetails) {
        return ResponseEntity.ok(groceryItemService.updateGroceryItem(id, itemDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroceryItem(@PathVariable Long id) {
        groceryItemService.deleteGroceryItem(id);
        return ResponseEntity.noContent().build();
    }
}