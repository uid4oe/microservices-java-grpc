package com.uid4oe.java.bff;

import com.uid4oe.java.bff.dto.AdviceDto;
import com.uid4oe.java.bff.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/")
class BffController {

    BffBusiness business;

    @Autowired
    public BffController(BffBusiness business) {
        this.business = business;
    }

    @GetMapping("users/")
    ResponseEntity<?> getUsers() {
        var data = business.getUsers();
        return ResponseEntity.ok(BffResponse.builder().data(data).build());
    }

    @GetMapping("users/{id}")
    ResponseEntity<?> getUserDetails(@PathVariable String id) {
        var data = business.getUserDetails(id);
        return ResponseEntity.ok(BffResponse.builder().data(data).build());
    }

    @PostMapping("users/")
    ResponseEntity<?> createUser(@RequestBody UserDto req) {
        var data = business.createUser(req);
        return ResponseEntity.ok(BffResponse.builder().data(data).build());
    }

    @PutMapping("users/{id}")
    ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserDto req) {
        var data = business.updateUser(id, req);
        return ResponseEntity.ok(BffResponse.builder().data(data).build());
    }

    @PutMapping("advices/")
    ResponseEntity<?> updateUserAdvice(@RequestBody AdviceDto req) {
        var data = business.updateAdvice(req);
        return ResponseEntity.ok(BffResponse.builder().data(data).build());
    }
}

