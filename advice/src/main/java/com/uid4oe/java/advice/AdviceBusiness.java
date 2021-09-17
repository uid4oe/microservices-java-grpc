package com.uid4oe.java.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdviceBusiness {

    AdviceRepository repository;

    @Autowired
    public AdviceBusiness(AdviceRepository repository) {
        this.repository = repository;
    }

    AdviceModel getAdvice(String id) {
        return repository.findById(id).get();
    }

    AdviceModel createUpdateAdvice(AdviceModel adviceModel) {
        return repository.save(adviceModel);
    }

}
