package com.pharmacy.system.store.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * OrderItem
 */

@Entity(name = "order_items")
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne()
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "price_per_unit")
  private BigDecimal pricePerUnit;

  public Long getId() {
    return id;
  }

  public Order getOrder() {
    return order;
  }

  public Product getProduct() {
    return product;
  }

  public int getQuantity() {
    return quantity;
  }

  public BigDecimal getPricePerUnit() {
    return pricePerUnit;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public void setPricePerUnit(BigDecimal pricePerUnit) {
    this.pricePerUnit = pricePerUnit;
  }

}
