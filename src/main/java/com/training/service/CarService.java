package com.training.service;

import com.training.domain.Car;
import com.training.dto.AuditCarDTO;
import com.training.model.SelectCarModel;
import com.training.response.ResponseResult;

import java.util.List;

/**
 * by Huang
 */
public interface CarService {
    ResponseResult getCars(Long companyId);
    ResponseResult getCarById(Long id);
    ResponseResult save(Car car);
    ResponseResult update(Car car);
    ResponseResult delete(Long id);
//    ResponseResult findByIsDeleted(int isDeleted);
    ResponseResult findByTimeAndUserID(SelectCarModel model, Long userId,Long companyId);
    ResponseResult updateCarIsUseOrNot(Long carId,int status);
    ResponseResult findCarWaitForCheck(Long companyId);
    ResponseResult findMyCarByIsPublicAndUserID(int isPublic, Long userId);
    ResponseResult findCarByIsUse(int isUse, Long companyId);
    //获取所有车辆
    ResponseResult findAllCars();
    //返回当前用户的所有车辆
    ResponseResult findCarsByUserId(Long userId);
    ResponseResult findCarPassed(Long companyId);
    ResponseResult passAddCarOrNot(AuditCarDTO auditCarDTO);
    ResponseResult findCarByIsPublicAndCompanyId(int isPublic, Long companyId);
}
