package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class UseController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkLogin(@RequestParam(required = true, defaultValue = "") String username,
                                        @RequestParam(required = true, defaultValue = "") String password) {

        LinkedHashMap<String, Object> res = new LinkedHashMap<>();

        try {
            List<UserModel> data = userRepository.checkLogin(username, password);
            res.put("data", data);
            return ResponseEntity.ok().body(res);
        }catch (Exception e) {
            res.put("data", e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }

}