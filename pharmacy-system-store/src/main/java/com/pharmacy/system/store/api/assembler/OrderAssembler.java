package com.pharmacy.system.store.api.assembler;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pharmacy.system.store.api.dto.OrderWithIdDTO;
import com.pharmacy.system.store.api.dto.OrderDTO;
import com.pharmacy.system.store.api.dto.OrderItemWithIdDTO;
import com.pharmacy.system.store.domain.model.Order;
import com.pharmacy.system.store.domain.model.OrderItem;

/**
 * OrderAssembler
 */
@Component
public class OrderAssembler {

    @Autowired
    ProductAssembler pAssembler;

    public OrderWithIdDTO toOutput(Order entity) {
        return new OrderWithIdDTO(
                entity.getID(),
                entity.getCustomer(),
                entity.getOrderDate(),
                entity.getTotalAmount(),
                entity.getStatus(),
                entity.getItems()
                        .stream()
                        .map(oi -> toOutput(oi))
                        .collect(Collectors.toSet()),
                entity.getConfirmationDate(),
                entity.getDeliveryDate(),
                entity.getCancellationDate());
    }

    public OrderItemWithIdDTO toOutput(OrderItem entity) {
        return new OrderItemWithIdDTO(
                entity.getOrderItemID(),
                toOutput(entity.getOrder()),
                entity.getQuantity(),
                entity.getPricePerUnit());
    }

    public OrderItem toEntity(OrderItemWithIdDTO dto) {
        OrderItem entity = new OrderItem();
        entity.setQuantity(dto.quantity());
        entity.setOrderItemID(dto.orderItemID());
        entity.setPricePerUnit(dto.pricePerUnit());
        return entity;
    }

    public Order toEntity(OrderDTO dto) {
        Order entity = new Order();
        // entity.setCustomer(dto.customer());
        entity.setOrderDate(dto.orderDate());
        entity.setTotalAmount(dto.totalAmount());
        // entity.setStatus(dto.status());
        entity.setItems(dto.items()
                .stream()
                .map(oi -> toEntity(oi))
                .collect(Collectors.toSet()));
        entity.setConfirmationDate(dto.confirmationDate());
        entity.setCancellationDate(dto.cancellationDate());
        entity.setDeliveryDate(dto.deliveryDate());
        return entity;
    }

    public Order toEntity(OrderWithIdDTO dto) {
        Order entity = new Order();
        entity.setID(dto.ID());
        entity.setCustomer(dto.customer());
        entity.setOrderDate(dto.orderDate());
        entity.setTotalAmount(dto.totalAmount());
        entity.setStatus(dto.status());
        entity.setItems(dto.items()
                .stream()
                .map(oi -> toEntity(oi))
                .collect(Collectors.toSet()));
        entity.setConfirmationDate(dto.confirmationDate());
        entity.setCancellationDate(dto.cancellationDate());
        entity.setDeliveryDate(dto.deliveryDate());
        return entity;
    }

}
