package com.teamcomputers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.model.Type_text;
import com.teamcomputers.repository.Type_textRepository;

@Service
public class Type_textService {
    @Autowired
    private Type_textRepository type_textRepository;

    public Type_text saveTypeText(Type_text type_text) {
        return type_textRepository.save(type_text);
    }

    // Add other service methods as required
}
