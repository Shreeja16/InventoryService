package com.inventory.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {
    private String skuCode;
    private Integer quantity;
}
