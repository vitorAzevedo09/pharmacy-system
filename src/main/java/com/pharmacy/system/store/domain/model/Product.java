package com.pharmacy.system.store.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "manufacturer", nullable = true)
  private String manufacturer;

  @Column(name = "description", nullable = true)
  private String description;

  @Column(name = "price")
  private BigDecimal price;

  @Column(name = "quantity_in_stock")
  private int quantityInStock;

  @Column(name = "expiration_date")
  @Temporal(TemporalType.DATE)
  private Date expirationDate;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Image> images;

  public static class Builder {

    private Long id;

    private String name;

    private String manufacturer;

    private String description;

    private BigDecimal price;

    private Integer quantityInStock;

    private Date expirationDate;

    private List<Image> images;

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder manufacturer(String manufacturer) {
      this.manufacturer = manufacturer;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder price(BigDecimal price) {
      this.price = price;
      return this;
    }

    public Builder quantityInStock(int quantityInStock) {
      this.quantityInStock = quantityInStock;
      return this;
    }

    public Builder expirationDate(Date expirationDate) {
      this.expirationDate = expirationDate;
      return this;
    }

    public Product build() {
      return new Product(this);
    }

  }

  public static Builder builder() {
    return new Builder();
  }

  public Product() {
  }

  private Product(Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.manufacturer = builder.manufacturer;
    this.description = builder.description;
    this.price = builder.price;
    this.quantityInStock = builder.quantityInStock;
    this.expirationDate = builder.expirationDate;
    this.images = builder.images;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public String getDescription() {
    return description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public Integer getQuantityInStock() {
    return quantityInStock;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public void copyFrom(Product another) {
    if (another.getId() != null) {
      this.id = another.getId();
    }
    if (another.getName() != null) {
      this.name = another.getName();
    }
    if (another.getManufacturer() != null) {
      this.manufacturer = another.getManufacturer();
    }
    if (another.getDescription() != null) {
      this.description = another.getDescription();
    }
    if (another.getPrice() != null) {
      this.price = another.getPrice();
    }
    if (another.getQuantityInStock() != null) {
      this.quantityInStock = another.getQuantityInStock();
    }
    if (another.getExpirationDate() != null) {
      this.expirationDate = another.getExpirationDate();
    }
  }

  public List<Image> getImages() {
    return images;
  }

}
