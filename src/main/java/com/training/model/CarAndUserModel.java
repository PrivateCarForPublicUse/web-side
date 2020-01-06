package com.training.model;

import com.training.domain.Car;
import com.training.domain.User;

import java.util.List;

public class CarAndUserModel {
    private List<Car> carList;
    private List<User> userList;

    public CarAndUserModel(List<Car> carList, List<User> userList) {
        this.carList = carList;
        this.userList = userList;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
