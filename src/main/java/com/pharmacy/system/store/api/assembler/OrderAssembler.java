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
        entity.setOrderDate(dto.orderDateTime());
        entity.setItems(dto.items()
                .stream()
                .map(oi -> toEntity(oi))
                .collect(Collectors.toSet()));
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

    public OrderItem toEntity(OrderItemDTO dto) {
        OrderItem entity = new OrderItem();
        System.out.println(dto.product_id());
        entity.setProduct(productService.findOrFail(dto.product_id()));
        entity.setQuantity(dto.quantity());
        entity.setPricePerUnit(dto.price_unit());
        return entity;
    }

}
