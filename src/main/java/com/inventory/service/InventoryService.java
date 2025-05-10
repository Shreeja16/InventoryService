package com.inventory.service;

import com.inventory.dto.InventoryRequest;
import com.inventory.dto.InventoryResponse;
import com.inventory.model.Inventory;
import com.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public List<InventoryResponse> updateInventory(List<InventoryRequest> requestList) {
        // Map InventoryRequest to Inventory and save to DB
        List<Inventory> inventories = requestList.stream()
                .map(req -> Inventory.builder()
                        .skuCode(req.getSkuCode())
                        .quantity(req.getQuantity())
                        .build())
                .collect(Collectors.toList());

        inventoryRepository.saveAll(inventories);

        // Map saved Inventory to InventoryResponse
        return inventories.stream()
                .map(inv -> InventoryResponse.builder()
                        .skuCode(inv.getSkuCode())
                        .isInStock(inv.getQuantity() > 0)
                        .build())
                .collect(Collectors.toList());
    }

    public List<InventoryResponse> isInStock(List<String> skuCodes) {
        List<Inventory> inventories = inventoryRepository.findBySkuCodeIn(skuCodes);

        return inventories.stream()
                .map(inv -> InventoryResponse.builder()
                        .skuCode(inv.getSkuCode())
                        .isInStock(inv.getQuantity() > 0)
                        .build())
                .collect(Collectors.toList());
    }
}
