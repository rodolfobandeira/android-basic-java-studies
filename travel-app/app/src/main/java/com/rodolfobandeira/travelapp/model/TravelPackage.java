package com.rodolfobandeira.travelapp.model;

import java.io.Serializable;
import java.math.BigDecimal;


public class TravelPackage implements Serializable {
/*
When we pass "implements Serializable" we can pass objects through intents when
we need to pass information between actitivies.

Using "Parcelable" is modern and has a better performance.
*/

    private final String city;
    private final String image;
    private final int days;
    private final BigDecimal price;

    public TravelPackage(String city, String image, int days, BigDecimal price) {
        this.city = city;
        this.image = image;
        this.days = days;
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public String getImage() {
        return image;
    }

    public int getDays() {
        return days;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
