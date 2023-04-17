package com.mirai.importback.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name="DomesticShipment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomesticShipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="imageProduct",nullable = false,length = 5000)
    private String imageProduct;
    @Column(name = "productName",nullable = false,length = 200)
    private String productName;
    @Column(name="userId",nullable = false,length = 500)
    private String userId;
    @Column(name="date",nullable = false,length = 150)
    private String date;
    @Column(name="departureTime",nullable = false,length = 150)
    private String departureTime;
    @Column(name="shippingDate",nullable = false,length = 150)
    private String shippingDate;
    @Column(name="shippingManager",nullable = false,length = 150)
    private String shippingManager;
    @Column(name="totalCost",nullable = false,length = 150)
    private String totalCost;
    @Column(name="trackingCode",nullable = false,length = 150)
    private String trackingCode;
    @Column(name="destiny",nullable = false,length = 150)
    private String destiny;

}
