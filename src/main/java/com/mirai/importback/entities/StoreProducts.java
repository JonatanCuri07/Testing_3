package com.mirai.importback.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="store_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name",nullable = false,length = 50)
    private String name;

    @OneToMany(mappedBy = "StoreProducts")
    List<ProductList> productList;

}
