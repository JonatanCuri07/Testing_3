package com.mirai.importback.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name="Directions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Directions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="dni",nullable = false,length = 8)
    private String dni;
    @Column(name="name",nullable = false,length = 50)
    private String name;
    @Column(name="lastname",nullable = false,length = 200)
    private String lastname;
    @Column(name="direction1",nullable = true,length = 200)
    private String direction1;
    @Column(name = "phone",nullable = true,length = 9)
    private String phone;
    @Column(name="district",nullable = true,length = 200)
    private String district;
    @Column(name="province",nullable = true,length = 200)
    private String province;
    @Column(name="departament",nullable = true,length = 200)
    private String departament;



}