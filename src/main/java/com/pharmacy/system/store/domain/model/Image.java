package com.pharmacy.system.store.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Image
 */
@Entity(name = "images")
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "url")
  private String imageUrl;

  @Column(name = "description")
  private String description;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  public Image(String imageUrl, String description, Product product) {
    this.imageUrl = imageUrl;
    this.description = description;
    this.product = product;
  }

  public Image() {
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
