package com.uid4oe.java.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBusiness {

    UserRepository repository;

    @Autowired
    public UserBusiness(UserRepository repository) {
        this.repository = repository;
    }

    List<UserModel> getUsers() {
        return repository.findAll();
    }

    UserModel getUserDetails(String id) {
        return repository.findById(id).get();
    }

    UserModel createUpdateUser(UserModel userModel) {
        return repository.save(userModel);
    }

}
