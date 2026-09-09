package com.grocery.service;

import com.grocery.exception.ResourceNotFoundException;
import com.grocery.model.GroceryItem;
import com.grocery.repository.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryItemService {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    public GroceryItem createGroceryItem(GroceryItem item) {
        return groceryItemRepository.save(item);
    }

    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemRepository.findAll();
    }

    public GroceryItem getGroceryItemById(Long id) {
        return groceryItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grocery Item not found with id: " + id));
    }

    public GroceryItem updateGroceryItem(Long id, GroceryItem itemDetails) {
        GroceryItem item = getGroceryItemById(id);
        item.setName(itemDetails.getName());
        item.setCategory(itemDetails.getCategory());
        item.setPrice(itemDetails.getPrice());
        item.setQuantity(itemDetails.getQuantity());
        return groceryItemRepository.save(item);
    }

    public void deleteGroceryItem(Long id) {
        GroceryItem item = getGroceryItemById(id);
        groceryItemRepository.delete(item);
    }
}