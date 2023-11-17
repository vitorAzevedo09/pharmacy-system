package com.pharmacy.system.store.api.assembler;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pharmacy.system.store.api.dto.OrderWithIdDTO;
import com.pharmacy.system.store.api.dto.OrderDTO;
import com.pharmacy.system.store.api.dto.OrderItemDTO;
import com.pharmacy.system.store.api.dto.OrderItemWithIdDTO;
import com.pharmacy.system.store.domain.model.Order;
import com.pharmacy.system.store.domain.model.OrderItem;
import com.pharmacy.system.store.domain.service.ProductService;

/**
 * OrderAssembler
 */
@Component
public class OrderAssembler {
    @Autowired
    private ProductService productService;

    public OrderWithIdDTO toOutput(Order entity) {
        return new OrderWithIdDTO(
                entity.getId(),
                entity.getCustomer().getId(),
                entity.getTimeCreated(),
                entity.getTotalAmount(),
                entity.getStatus(),
                entity.getItems()
                        .stream()
                        .map(oi -> toOutput(oi))
                        .collect(Collectors.toSet()));
    }

    public OrderItemWithIdDTO toOutput(OrderItem entity) {
        return new OrderItemWithIdDTO(
                entity.getId(),
                entity.getQuantity(),
                entity.getPricePerUnit());
    }

    public OrderItem toEntity(OrderItemWithIdDTO dto) {
        OrderItem entity = new OrderItem();
        entity.setQuantity(dto.quantity());
        entity.setId(dto.orderItemID());
        entity.setPricePerUnit(dto.pricePerUnit());
        return entity;
    }

    public Order toEntity(OrderDTO dto) {
        Order entity = new Order();
        entity.setCustomer(customerAssembler.toEntity(dto.customer()));
        entity.setItems(dto.items()
                .stream()
                .map(oi -> toEntity(oi))
                .collect(Collectors.toSet()));
        return entity;
    }

    public Order toEntity(OrderWithIdDTO dto) {
        Order entity = new Order();
        entity.setId(dto.ID());
        entity.setCustomer(dto.customer());
        entity.setTotalAmount(dto.totalAmount());
        entity.setItems(dto.items()
                .stream()
                .map(oi -> toEntity(oi))
                .collect(Collectors.toSet()));
        return entity;
    }

    public OrderItem toEntity(OrderItemDTO dto) {
        OrderItem entity = new OrderItem();
        entity.setProduct(productService.findOrFail(dto.product()
                .ID()));
        entity.setQuantity(dto.quantity());
        return entity;
    }

}
