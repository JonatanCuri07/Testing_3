package com.mirai.importback.services.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PriceCalculationService {

    private float price;
    private float feeInterestPercent;
    private LocalDate deliveryDate;
    private LocalDate purchaseDate;

    public PriceCalculationService(float price, float feeInterestPercent, LocalDate deliveryDate, LocalDate purchaseDate) {
        this.price = price;
        this.feeInterestPercent = feeInterestPercent;
        this.deliveryDate = deliveryDate;
        this.purchaseDate = purchaseDate;
    }

    // calculates the amount of days between the purchase date and the delivery date
    public int calculatePeriod() {
       long days = ChronoUnit.DAYS.between(deliveryDate, purchaseDate);
        return (int) days;
    }

    public float calculateFee() {
        float days = calculatePeriod();
        return (1 + feeInterestPercent / 100 * (days / 360));
    }

    public float calculatePrice(){
        float fee = calculateFee();
        return price * fee;
    }
}
