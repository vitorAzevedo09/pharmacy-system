package com.pharmacy.system.store.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Order
 */

import java.util.Date;
import java.util.Set;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long ID;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @Column(name = "order_date")
  @Temporal(TemporalType.DATE)
  private Date orderDate;

  @Column(name = "TotalAmount")
  private BigDecimal totalAmount;

  @Enumerated(EnumType.STRING)
  private OrderStatus status = OrderStatus.CREATED;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private Set<OrderItem> items;

  private OffsetDateTime confirmationDate;
  private OffsetDateTime cancellationDate;
  private OffsetDateTime deliveryDate;

  public void setID(Long orderID) {
    this.ID = orderID;
  }

  public void copyFrom(Order order) {
    if (order.getCustomer() != null)
      this.customer = order.customer;
    if (order.getOrderDate() != null)
      this.orderDate = order.getOrderDate();
    if (order.getTotalAmount() != null)
      this.totalAmount = order.getTotalAmount();
  }

  public void calcTotalValue() {
    this.totalAmount = getItems().stream()
        .map(item -> item.getPricePerUnit())
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public void confirm() {
    if (getStatus() != OrderStatus.CREATED) {
      throw new OrderFlowException(getStatus(), OrderStatus.CONFIRMED);
    }
    setStatus(OrderStatus.CONFIRMED);
    setConfirmationDate(OffsetDateTime.now());
  }

  public void delivery() {
    if (getStatus() != OrderStatus.CREATED) {
      throw new OrderFlowException(getStatus(), OrderStatus.CONFIRMED);
    }
    setStatus(OrderStatus.CONFIRMED);
    setConfirmationDate(OffsetDateTime.now());
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public void setConfirmationDate(OffsetDateTime confirmationDate) {
    this.confirmationDate = confirmationDate;
  }

  public void setCancellationDate(OffsetDateTime cancellationDate) {
    this.cancellationDate = cancellationDate;
  }

  public void setDeliveryDate(OffsetDateTime deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  public Long getID() {
    return ID;
  }

  public Customer getCustomer() {
    return customer;
  }

  public Date getOrderDate() {
    return orderDate;
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

  public OffsetDateTime getConfirmationDate() {
    return confirmationDate;
  }

  public OffsetDateTime getCancellationDate() {
    return cancellationDate;
  }

  public OffsetDateTime getDeliveryDate() {
    return deliveryDate;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  public void setItems(Set<OrderItem> items) {
    this.items = items;
  }

}
