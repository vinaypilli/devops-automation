package com.teamcomputers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.model.Data;
import com.teamcomputers.repository.DatasRepository;

@Service
public class DatasService {
    @Autowired
    private DatasRepository datasRepository;

    public Data saveDatas(Data data) {
        return datasRepository.save(data);
    }

    // Add other service methods as required
}