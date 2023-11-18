package com.pharmacy.system.store.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Order
 */

import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import com.pharmacy.system.store.domain.exception.OrderFlowException;
import com.pharmacy.system.store.domain.model.enumerate.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Builder.Default;

@Entity(name = "orders")
@Builder
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @Column(name = "total_amount")
  private BigDecimal totalAmount;

  @Default
  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private OrderStatus status = OrderStatus.CREATED;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private Set<OrderItem> items;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderStatusHistory> statusHistory;

  public void setId(Long orderID) {
    this.id = orderID;
  }

  public OffsetDateTime getTimeCreated() {
    return this.statusHistory.stream()
        .filter(status -> status.getStatus()
            .equals(OrderStatus.CREATED))
        .findFirst()
        .orElse(null)
        .getChangeDate();
  }

  public void calcTotalValue() {
    this.totalAmount = getItems().stream()
        .map(item -> item.getPricePerUnit())
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public void createOrderHistory() {
    if (getStatus() != null || !getStatusHistory().isEmpty()) {
      throw new OrderFlowException(getStatus(), OrderStatus.CREATED);
    }
    this.statusHistory = new ArrayList<>();
  }

  private void updateOrderStatus(OrderStatus newStatus) {
    if (this.statusHistory == null) {
      this.statusHistory = new ArrayList<>();
    }

    OrderStatusHistory statusHistoryEntry = new OrderStatusHistory();
    statusHistoryEntry.setOrder(this);
    statusHistoryEntry.setStatus(newStatus);
    statusHistoryEntry.setChangeDate(OffsetDateTime.now());

    this.statusHistory.add(statusHistoryEntry);
    this.status = newStatus; // Update current status
  }

  public void updateStatusToCreated() {
    if (getStatus() != OrderStatus.CREATED) {
      throw new OrderFlowException(getStatus(), OrderStatus.CREATED);
    }
    updateOrderStatus(OrderStatus.CREATED);
  }

  public void updateStatusToProcessing() {
    if (getStatus() != OrderStatus.CREATED) {
      throw new OrderFlowException(getStatus(), OrderStatus.PROCESSING);
    }
    updateOrderStatus(OrderStatus.PROCESSING);
  }

  public void updateStatusToShipped() {
    if (getStatus() != OrderStatus.PROCESSING) {
      throw new OrderFlowException(getStatus(), OrderStatus.SHIPPED);
    }
    updateOrderStatus(OrderStatus.SHIPPED);
  }

  public void updateStatusToDelivered() {
    if (getStatus() != OrderStatus.SHIPPED) {
      throw new OrderFlowException(getStatus(), OrderStatus.DELIVERED);
    }
    updateOrderStatus(OrderStatus.DELIVERED);
  }

  public void updateStatusToCancelled() {
    if (getStatus() == OrderStatus.SHIPPED) {
      throw new OrderFlowException(getStatus(), OrderStatus.CANCELED);
    }
    updateOrderStatus(OrderStatus.CANCELED);
  }

  public Long getId() {
    return id;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public Set<OrderItem> getItems() {
    return items;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  public void setItems(Set<OrderItem> items) {
    this.items = items;
  }

  public List<OrderStatusHistory> getStatusHistory() {
    return statusHistory;
  }

  public Customer getCustomer() {
    return customer;
  }

}
