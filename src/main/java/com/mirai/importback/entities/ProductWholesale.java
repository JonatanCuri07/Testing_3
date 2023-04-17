package com.mirai.importback.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name="ProductWholesale")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductWholesale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name",nullable = false,length = 50)
    private String name;
    @Column(name="quantity",nullable = false,length = 50)
    private String quantity;
    @Column(name="price",nullable = false,length = 50)
    private String price;
    @Column(name="picture",nullable = false,length = 1000)
    private String picture;
    @Column(name="description",nullable = false,length = 1000)
    private String description;
    @Column(name="principalFeature1",nullable = false,length = 1000)
    private String principalFeature1;
    @Column(name="principalFeature2",nullable = false,length = 1000)
    private String principalFeature2;
    @Column(name="principalFeature3",nullable = false,length = 1000)
    private String principalFeature3;
}