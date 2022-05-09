package com.example.a2.DTO;

import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@RestController
public class RestaurantDTO {

    private String name;
    private String location;
    private String deliveryZones;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDeliveryZones() {
        return deliveryZones;
    }

    public void setDeliveryZones(String deliveryZones) {
        this.deliveryZones = deliveryZones;
    }
}
