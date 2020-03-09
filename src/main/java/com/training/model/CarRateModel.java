package com.training.model;

import com.training.domain.Car;
import com.training.domain.CarRate;
import com.training.domain.User;

public class CarRateModel {
    private User user;
    private CarRate carRate;

    public CarRateModel(){}

    public CarRateModel(User user, CarRate car) {
        this.user = user;
        this.carRate = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CarRate getCarRate() {
        return carRate;
    }

    public void setCarRate(CarRate carRate) {
        this.carRate = carRate;
    }
}
