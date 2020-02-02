package com.training.service;

import com.training.domain.Account;
import com.training.domain.Master;
import com.training.domain.User;
import com.training.dto.ReviewReimburseDTO;
import com.training.response.ResponseResult;

public interface ReviewReimburseService {
    ResponseResult acceptReimburse(ReviewReimburseDTO reviewReimburseDTO, Master master);
    ResponseResult rejectReimburse(ReviewReimburseDTO reviewReimburseDTO, Master master);

    Long getCompanyId(ReviewReimburseDTO reviewReimburseDTO);
}
