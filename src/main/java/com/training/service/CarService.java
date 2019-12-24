package com.training.service;

import com.training.domain.Car;
import com.training.model.SelectCarModel;
import com.training.response.ResponseResult;

import java.util.List;

/**
 * by Huang
 */
public interface CarService {
    ResponseResult getCars();
    ResponseResult getCarById(Long id);
    ResponseResult save(Car car);
    ResponseResult update(Car car);
    ResponseResult delete(Long id);
    ResponseResult findByIsPublic(int isPublic);
    ResponseResult findByIsUse(int isUse);
    ResponseResult findByIsDeleted(int isDeleted);
    ResponseResult findByTimeAndUserID(SelectCarModel model, Long id);
    ResponseResult updateCarIsUseOrNot(Long carId, Long secRouteId,int status);
}
