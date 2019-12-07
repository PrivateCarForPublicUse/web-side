package com.training.service.Impl;


import com.training.domain.Master;
import com.training.repository.MasterRepository;
import com.training.response.ResponseResult;
import com.training.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterServiceImpl implements MasterService {

    @Autowired
    MasterRepository masterRepository;

    @Override
    public ResponseResult findAllMasters() {
        return new ResponseResult(masterRepository.findAll());
    }

    @Override
    public ResponseResult findMastersByName(String newname) {
        return new ResponseResult(masterRepository.findAllByName(newname));
    }

    @Override
    public ResponseResult saveMaster(Master master) {
        Master m =  masterRepository.save(master);
        return new ResponseResult(m);
    }

    @Override
    public void deleteMaster(Master master) {
        masterRepository.delete(master);
    }

    @Override
    public void deleteMasterById(Long id) { masterRepository.deleteById(id);
    }

    @Override
    public ResponseResult updateMaster(Master master) {
        return new ResponseResult(masterRepository.save(master));
    }

    @Override
    public ResponseResult findMasterById(Long id) {
        return new ResponseResult(masterRepository.findById(id).get());
    }

    @Override
    public ResponseResult updateNameOfMastersById(Long id,String name){
        Master m = masterRepository.findById(id).get();
        m.setName(name);
        return new ResponseResult(masterRepository.save(m));
    }
}
