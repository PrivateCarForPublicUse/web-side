package com.training.service.Impl;


import com.training.domain.Master;
import com.training.domain.Route;
import com.training.domain.User;
import com.training.dto.AuditUserDTO;
import com.training.model.AuditModel;
import com.training.model.CarUserModel;
import com.training.repository.CarRepository;
import com.training.repository.MasterRepository;
import com.training.repository.RouteRepository;
import com.training.repository.UserRepository;
import com.training.response.ResponseResult;
import com.training.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MasterServiceImpl implements MasterService {

    @Autowired
    MasterRepository masterRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    RouteRepository routeRepository;

    @Override
    public ResponseResult findAllMasters() {
        List<Master> Masters = masterRepository.findAll();
        if (Masters.size() == 0)
            return new ResponseResult(500,"管理员不存在!");
        return new ResponseResult(Masters);
    }

    @Override
    public ResponseResult findMastersByName(String newname) {
        List<Master> Masters =  masterRepository.findAllByName(newname);
        if (Masters.size() == 0)
            return new ResponseResult(501,"name不存在!");
        return new ResponseResult(Masters);
    }

    @Override
    public ResponseResult saveMaster(Master master) {
        try {
            Master m =  masterRepository.save(master);
            return new ResponseResult(m);
        }
        catch (Exception e){
            return new ResponseResult(503,"插入失败!");
        }
    }

    @Override
    public void deleteMaster(Master master) {
        masterRepository.delete(master);
    }

    @Override
    public ResponseResult deleteMasterById(Long id) {
        try {
            masterRepository.deleteById(id);
            return new ResponseResult();
        }
        catch (Exception e){
            return new ResponseResult(504,"删除失败!");
        }
    }

    @Override
    public ResponseResult updateMaster(Master master) {
        try {
            Master m =  masterRepository.save(master);
            return new ResponseResult(m);
        }
        catch (Exception e){
            return new ResponseResult(503,"更新失败!");
        }
    }

    @Override
    public ResponseResult findMasterById(Long id) {
        Master Master =  masterRepository.findById(id).get();
        if (Master == null)
            return new ResponseResult(501,"id不存在!");
        return new ResponseResult(Master);
    }

    @Override
    public ResponseResult updateNameOfMastersById(Long id,String name){
        try {
            Master m = masterRepository.findById(id).get();
            m.setName(name);
            masterRepository.save(m);
            return new ResponseResult(m);
        }
        catch (Exception e){
            return new ResponseResult(503,"更新失败!");
        }

    }

    @Override
    public ResponseResult loginByMasterName(String mastername,String password) {
//        Master master = masterRepository.findMasterByMasterName(mastername);
//        System.out.println(master == null);
//        if (master == null)
//            return new ResponseResult(500,"管理员不存在!");
//        if (master.getPassword().equals(password))
//            return new ResponseResult(200,"登录成功！",master);
//        return new ResponseResult(506,"密码错误!");
        return null;
    }

    @Override
    public ResponseResult getAuditNum() {
        AuditModel auditModel=new AuditModel(userRepository.getUsersByCheckStatus(0),carRepository.findByIsUse(1),routeRepository.findRoutesByStatus(0),routeRepository.findRoutesByStatusAndIsReimburse(3,2));
        return new ResponseResult(auditModel);
    }

    @Override
    public Master getMasterByName(String name) {
        return masterRepository.findMasterByMasterName(name);
    }

    @Override
    public ResponseResult getAuditUser(Master master) {
        List<User> list = userRepository.getUsersByCheckStatusAndCompanyId(0, master.getCompanyId());
        return new ResponseResult(list);
    }

    @Override
    public ResponseResult AuditUser(AuditUserDTO auditUserDTO) {
        User user = userRepository.findById(auditUserDTO.getUserId()).get();
        user.setCheckStatus(auditUserDTO.getIsAccept());
        userRepository.save(user);
        return new ResponseResult(user);
    }

    @Override
    public ResponseResult findAllMastersByCompanyId(Long id) {
        List<Master> masterByCompanyId = masterRepository.findMasterByCompanyId(id);
        return new ResponseResult(masterByCompanyId);
    }

    @Override
    public ResponseResult findUsersAndCars(Long companyId){
        List<Route> routes = routeRepository.findRoutesByStatusAndCompanyId(2,companyId);
        if (routes.size() == 0)
            return new ResponseResult(500,"没有正在进行中的行程!");
        List<CarUserModel> carUserModels = new ArrayList<>();
        for (Route r : routes){
            CarUserModel carUserModel = new CarUserModel();
            carUserModel.setCar(carRepository.findById(r.getCarId()).get());
            carUserModel.setUser(userRepository.findById(r.getUserId()).get());
            carUserModels.add(carUserModel);
        }
        return new ResponseResult(carUserModels);
    }

    @Override
    public Map<String,Integer>poll(Long companyId){
        Map<String, Integer>map=new HashMap<>();
        //轮询审核中的用户
        int userToReviewNum = userRepository.getUsersByCheckStatusAndCompanyId(0,companyId).size();
        int carToReviewNum = carRepository.findCarWaitForCheck(companyId).size();
        int routeToReviewNum = routeRepository.findRoutesByStatusAndCompanyId(0,companyId).size();
        int reimburseToReviewNum = routeRepository.findRoutesByStatusAndIsReimburseAndCompanyId(3,2,companyId).size();
        map.put("userToReviewNum",userToReviewNum);
        map.put("carToReviewNum",carToReviewNum);
        map.put("routeToReviewNum",routeToReviewNum);
        map.put("reimburseToReviewNum",reimburseToReviewNum);
        return map;
    }
}
