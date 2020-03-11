package com.training.service.Impl;

import com.training.domain.Car;
import com.training.domain.CarRate;
import com.training.domain.User;
import com.training.model.CarRateModel;
import com.training.repository.CarRateRepository;
import com.training.repository.CarRepository;
import com.training.repository.UserRepository;
import com.training.response.ResponseResult;
import com.training.service.CarRateService;
import com.training.service.CarService;
import com.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * by Huang
 */
@Service
public class CarRateServiceImpl implements CarRateService {
    @Autowired
    CarRateRepository carRateRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    CarService carService;
    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseResult getCarRates(Long companyId){
        List<CarRate> carRates = carRateRepository.findAllByCompany(companyId);
        if (carRates.size() == 0)
            return new ResponseResult(500,"不存在任何评价!");
        return new ResponseResult(carRates);
    }

    @Override
    public ResponseResult save(CarRate carRate){
        try {
            double rate=0;
            List<CarRate> carRates=carRateRepository.findByCarId(carRate.getCarId());
            for(CarRate carRate1 : carRates){
                rate+=carRate1.getRate();
            }
            CarRate c = carRateRepository.save(carRate);
            Car car = carRepository.findById(carRate.getCarId()).get();
            car.setStarOfCar(rate/carRates.size());
            carService.save(car);
            return new ResponseResult(c);
        }
        catch (Exception e){
            return new ResponseResult(501,"新建失败!");
        }
    }

//    @Override
//    public ResponseResult update(CarRate carRate) {
//        try {
//            CarRate c = carRateRepository.save(carRate);
//            return new ResponseResult(c);
//        }
//        catch (Exception e){
//            return new ResponseResult(504,"更新失败!");
//        }
//    }

    @Override
    public ResponseResult findByUserId(Long userId) {
        List<CarRate> carRates = carRateRepository.findByUserId(userId);
        if (carRates.size() == 0)
            return new ResponseResult(502,"该用户没有发表过任何评价!");
        return new ResponseResult(carRates);
    }

    @Override
    public ResponseResult findByCarId(Long carId) {
        List<CarRate> carRates = carRateRepository.findByCarId(carId);
        if (carRates.size() == 0)
            return new ResponseResult(503,"该车未曾收到过评价!");
        List<CarRate> carRateList = carRateRepository.findByCarId(carId);
        List<CarRateModel> list=new ArrayList<>();
        for(CarRate carRate : carRateList){
            list.add(carRateToCarRateModel(carRate));
        }
        return new ResponseResult(list);
    }

    //将CarRate 转换为 包含User的CarRateModel
    private CarRateModel carRateToCarRateModel(CarRate carRate){
        return new CarRateModel(userRepository.findUserById(carRate.getUserId()),carRate);
    }

}
