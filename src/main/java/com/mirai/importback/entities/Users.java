package com.mirai.importback.entities;


import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name",nullable = false,length = 50)
    private String name;
    @Column(name="age",nullable = true,length = 9)
    private String age;
    @Column(name = "password",nullable = false,length = 50)
    private String password;
    @Column(name="dni",nullable = false,length = 8)
    private String dni;
    @Column(name="genre",nullable = true,length = 150)
    private String genre;
    @Column(name="day",nullable = true,length = 9)
    private String day;
    @Column(name="month",nullable = true,length = 9)
    private String month;
    @Column(name="year",nullable = true,length = 9)
    private String year;
    @Column(name="email",nullable = true,length = 50)
    private String email;
    @Column(name="phone",nullable = true,length = 9)
    private String phone;
    @Column(name="address",nullable = true,length = 150)
    private String address;



}

