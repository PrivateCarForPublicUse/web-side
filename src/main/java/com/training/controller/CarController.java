package com.training.controller;

import com.training.domain.Account;
import com.training.domain.Car;
import com.training.domain.Master;
import com.training.domain.User;
import com.training.dto.AuditCarDTO;
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
    public ResponseResult cars(HttpServletRequest request){
        HttpSession session=request.getSession();
        Account account= (Account) session.getAttribute("account");
        int flag=account.getFlag();
        if(flag==1){
            Master master= (Master) session.getAttribute("master");
            Long companyId=master.getCompanyId();
            return carService.getCars(companyId);
        }else{
            return new ResponseResult(600,"没有权限!");
        }
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
    @ApiOperation("用户接口：新增车辆")
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
            @ApiResponse(code=200,message="更新车辆状态成功"),
            @ApiResponse(code=600,message="没有权限")
    })
    @ApiOperation("管理员接口：修改车辆审核状态，审核通过置isAccept为0，不通过isAccept为-1")
    @GetMapping("/passAddCar")
    public ResponseResult passAddCarOrNot(@RequestBody AuditCarDTO auditCarDTO,HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Master master = (Master) session.getAttribute("master");
        if(master==null) return new ResponseResult(600,"没有权限");
        return carService.passAddCarOrNot(auditCarDTO);
    }

    @ApiResponses({
            @ApiResponse(code=507,message="不存在待审核的车辆")
    })
    @ApiOperation("管理员接口：查看待审核的车辆")
    @GetMapping("/reviewAddCar")
    public ResponseResult reviewAddCar(HttpServletRequest request){
        HttpSession session=request.getSession();
        Account account= (Account) session.getAttribute("account");
        int flag=account.getFlag();
        if(flag==1){
            Master master= (Master) session.getAttribute("master");
            Long companyId=master.getCompanyId();
            return carService.findCarWaitForCheck(companyId);
        }else{
            return new ResponseResult(600,"没有权限!");
        }
    }

    @ApiResponses({
            @ApiResponse(code=508,message="没有该状态的车辆")
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

    @ApiResponse(code=200,message="成功")
    @ApiOperation("用户接口：返回我的所有车辆（不区分公私）")
    @GetMapping("/myAllCar")
    public ResponseResult getMyAllCar(HttpServletRequest request){
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        Long id=user.getId();
        return carService.findCarsByUserId(id);
    }

    @ApiOperation("管理员接口：根据用户ID获取其所有车辆")
    @GetMapping("/getUserCars/{userId}")
    public ResponseResult getCarsByUser(@PathVariable("userId")Long userId){
        return carService.findCarsByUserId(userId);
    }

    @ApiResponses({
            @ApiResponse(code=508,message="没有该状态的车辆")
    })
    @ApiOperation("管理员接口：查看本公司出行或空闲的车辆，isUse为2表示出行中，0表示空闲")
    @ApiImplicitParam(name = "isUse", value = "出行或者空闲")
    @GetMapping("/getRunningCar")
    public ResponseResult getCarByIsUse(@RequestParam("isUse")int isUse,HttpServletRequest request){
        HttpSession session=request.getSession();
        Account account= (Account) session.getAttribute("account");
        int flag=account.getFlag();
        if(flag==1){
            Master master= (Master) session.getAttribute("master");
            Long companyId=master.getCompanyId();
            return carService.findCarByIsUse(isUse,companyId);
        }else{
            return new ResponseResult(600,"没有权限!");
        }
    }

    // 返回所有车辆 by pja
    @ApiResponse(code=200,message="返回所有车辆")
    @ApiOperation("返回所有车辆，不区分公司")
    @GetMapping("/all")
    public ResponseResult getAllCars(){
        return carService.findAllCars();
    }

    @ApiResponses({
            @ApiResponse(code=508,message="没有该状态的车辆")
    })
    @ApiOperation("管理员接口：查看审核通过的车辆列表")
    @GetMapping("/getCarPassed")
    public ResponseResult getCarPassed(HttpServletRequest request){
        HttpSession session=request.getSession();
        Account account= (Account) session.getAttribute("account");
        int flag=account.getFlag();
        if(flag==1){
            Master master= (Master) session.getAttribute("master");
            Long companyId=master.getCompanyId();
            return carService.findCarPassed(companyId);
        }else{
            return new ResponseResult(600,"没有权限!");
        }
    }


}
