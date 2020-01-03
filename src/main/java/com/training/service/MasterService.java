package com.training.service;

import com.training.domain.Master;
import com.training.response.ResponseResult;

public interface MasterService {
    ResponseResult findAllMasters();
    ResponseResult findMastersByName(String newname);
    ResponseResult saveMaster(Master master);
    void deleteMaster(Master master);
    ResponseResult deleteMasterById(Long id);
    ResponseResult updateMaster(Master master);
    ResponseResult findMasterById(Long id);
    ResponseResult updateNameOfMastersById(Long id, String name);
    ResponseResult loginByMasterName(String mastername,String password);
    //获取当前需要审批的数量
    ResponseResult getAuditNum();

    Master getMasterByName(String name);
}
