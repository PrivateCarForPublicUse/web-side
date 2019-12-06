package com.training.service;

import com.training.domain.Reimburse;

import java.util.List;

/**
 * by Huang
 */
public interface ReimburseService {
    List<Reimburse> getReimburses();
    Reimburse getReimburseById(Long id);
    Reimburse save(Reimburse reimburse);
    Reimburse update(Reimburse reimburse);
}
