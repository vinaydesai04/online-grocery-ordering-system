package com.grocery.service;

import com.grocery.dto.OrderRequest;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.model.Customer;
import com.grocery.model.GroceryItem;
import com.grocery.model.Order;
import com.grocery.repository.CustomerRepository;
import com.grocery.repository.GroceryItemRepository;
import com.grocery.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private GroceryItemRepository groceryItemRepository;

    public Order createOrder(OrderRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + request.getCustomerId()));

        List<GroceryItem> items = groceryItemRepository.findAllById(request.getGroceryItemIds());
        if (items.isEmpty()) {
            throw new ResourceNotFoundException("No valid grocery items found for provided IDs");
        }

        // Calculate the total price based on the selected items
        double totalPrice = items.stream().mapToDouble(GroceryItem::getPrice).sum();

        Order order = new Order();
        order.setCustomer(customer);
        order.setGroceryItems(items);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }
    
    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }
}