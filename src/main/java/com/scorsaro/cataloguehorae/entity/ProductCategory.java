package com.scorsaro.cataloguehorae.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product_category")
@Getter
@Setter
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String categoryName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="category")
    private Set<Product> products;
}