package com.mirai.importback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="ProductList")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name",nullable = false,length = 50)
    private String name;
    @Column(name="imgURL",nullable = false,length = 10000)
    private String imgURL;
    @Column(name="price",nullable = false,length = 10000)
    private String price;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Type_StoreProducts")
    @JsonIgnore
    StoreProducts StoreProducts;



}