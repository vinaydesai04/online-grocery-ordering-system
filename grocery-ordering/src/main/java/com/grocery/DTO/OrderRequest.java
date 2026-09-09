package com.grocery.dto; // Remember to match your package name

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private Long customerId;
    private List<Long> groceryItemIds;
}