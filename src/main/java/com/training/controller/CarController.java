package com.training.controller;

import com.training.domain.Account;
import com.training.domain.Car;
import com.training.domain.User;
import com.training.model.SelectCarModel;
import com.training.response.ResponseResult;
import com.training.service.CarService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * by Huang
 * 车辆增删改查
 */
@Api(value="/car",tags="车辆信息接口")
@RequestMapping("/car")
@RestController
public class CarController {
    @Autowired
    CarService carService;

    @ApiResponses({
            @ApiResponse(code=500,message="不存在任何车辆")
    })
    @ApiOperation("管理员接口：查看本公司所有车辆")
    @GetMapping("/fd")
    public ResponseResult cars(@RequestParam("masterId")Long masterId){
        return carService.getCars(masterId);
    }

    @ApiResponses({
            @ApiResponse(code=501,message="id不存在")
    })
    @ApiOperation("通过id获取车辆信息")
    @ApiImplicitParam(name="id",value = "车辆id")
    @GetMapping("/id/{id}")
    public ResponseResult getCarById(@PathVariable("id") Long id){
        return carService.getCarById(id);
    }

    @ApiResponses({
            @ApiResponse(code=502,message="新建失败")
    })
    @ApiOperation("用户接口：添加车辆")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody Car car){
        car.setIsUse(1);
        return carService.save(car);
    }

    @ApiResponses({
            @ApiResponse(code=503,message="更新失败")
    })
    @ApiOperation("用户接口：更新车辆")
    @PutMapping("/update")
    public ResponseResult update(@RequestBody Car car){
        return carService.save(car);
    }

    @ApiResponses({
            @ApiResponse(code=200,message="删除成功"),
            @ApiResponse(code=504,message="删除失败")
    })
    @ApiOperation("删除车辆")
    @ApiImplicitParam(name="id",value = "车辆id")
    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable("id") Long id){
        return carService.delete(id);
    }

//    @ApiResponses({
//            @ApiResponse(code=505,message="没有私车"),
//            @ApiResponse(code=506,message="没有公车"),
//            @ApiResponse(code=507,message="无效的识别码")
//    })
//    @ApiOperation("根据公私状态1公车，0私车获取对应的车辆列表")
//    @ApiImplicitParam(name="isPublic",value = "是否为私车")
//    @GetMapping("/isPublic/{isPublic}")
//    public ResponseResult findByIsPublic(@PathVariable("isPublic") int isPublic){
//        return carService.findByIsPublic(isPublic);
//    }
//
//    @ApiResponses({
//            @ApiResponse(code=508,message="没有空闲的车"),
//            @ApiResponse(code=509,message="没有审核中的车"),
//            @ApiResponse(code=510,message="没有使用中的车"),
//            @ApiResponse(code=507,message="无效的识别码")
//    })
//    @ApiOperation("根据使用状态0空闲,1审核中,2使用中获取对应的车辆列表")
//    @ApiImplicitParam(name="isUse",value = "使用状态")
//    @GetMapping("/isUse/{isUse}")
//    public ResponseResult findByIsUse(@PathVariable("isUse") int isUse){
//        return carService.findByIsUse(isUse);
//    }
//
//    @ApiResponses({
//            @ApiResponse(code=511,message="不存在有效的车辆"),
//            @ApiResponse(code=512,message="没有已删除的车"),
//            @ApiResponse(code=507,message="无效的识别码")
//    })
//    @ApiOperation("查看没有被删除的所有车，0为未删除，-1为已删除")
//    @ApiImplicitParam(name="isDeleted",value = "是否已删除")
//    @GetMapping("/isDeleted/{isDeleted}")
//    public ResponseResult findByIsDeleted(@PathVariable("isDeleted") int isDeleted){
//        return carService.findByIsDeleted(isDeleted);
//    }

    @ApiResponses({
            @ApiResponse(code=505,message="不存在该时间段可用的车辆")
    })
    @ApiOperation("用户接口：根据选定时间查看自己或别人的车,isMine的1表示自己的车，0表示别人的车")
    @PostMapping("/getCarByTime")
    public ResponseResult getCarByTime(@RequestBody SelectCarModel model,HttpServletRequest request){
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        Long userId=user.getId();
        Long companyId=user.getCompanyId();
        return carService.findByTimeAndUserID(model,userId,companyId);
    }

    @ApiResponses({
            @ApiResponse(code=506,message="不存在待审核的车辆")
    })
    @ApiOperation("管理员接口：查看待审核的车辆")
    @GetMapping("/reviewAddCar")
    public ResponseResult reviewAddCar(@RequestParam("masterId")Long masterId){
        return carService.findCarWaitForCheck(masterId);
    }

    @ApiResponses({
            @ApiResponse(code=507,message="您没有该状态的车辆")
    })
    @ApiOperation("用户接口：管理我的车辆，isPublic为1表示公车，0表示私车")
    @ApiImplicitParam(name="isPublic",value = "是否公用")
    @GetMapping("/getMyCar")
    public ResponseResult getMyCar(@RequestParam("isPublic")int isPublic,HttpServletRequest request){
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        Long id=user.getId();
        return carService.findMyCarByIsPublicAndUserID(isPublic,id);
    }

    @ApiResponses({
            @ApiResponse(code=508,message="不存在待审核的车辆")
    })
    @ApiOperation("管理员接口：审核车辆，审核通过isUse为0，不通过isUse为-1")
    @GetMapping("/passAddCar")
    public ResponseResult passAddCarOrNot(@RequestParam("carId")Long carId, @RequestParam("isUse")int isUse){
        return carService.updateCarIsUseOrNot(carId,isUse);
    }
}
