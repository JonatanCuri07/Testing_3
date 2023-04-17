package com.mirai.importback.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="Coupon")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title",nullable = false,length = 50)
    private String title;
    @Column(name="discount",nullable = false,length = 50)
    private String discount;
    @Column(name="code",nullable = false,length = 50)
    private String code;
    @Column(name="validDate",nullable = false,length = 50)
    private String validDate;
    @Column(name="situation",nullable = false,length = 200)
    private String situation;
    @Column(name="description",nullable = false,length = 200)
    private String description;


}