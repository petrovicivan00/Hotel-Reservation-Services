package com.sk02.sk02_user_service.domain;

import javax.persistence.*;

@Entity
@Table (name = "ranks", indexes = {
        @Index(columnList = "name", unique = true),
        @Index(columnList = "reservationLimit", unique = true)})
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int reservationLimit;
    private double discount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReservationLimit() {
        return reservationLimit;
    }

    public void setReservationLimit(int reservationLimit) {
        this.reservationLimit = reservationLimit;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
