package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.JobRepository;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/api")
@Controller
public class UseController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JobRepository jobRepository;

    @GetMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkLogin(@RequestParam(required = true, defaultValue = "") String username,
                                        @RequestParam(required = true, defaultValue = "") String password) {

        LinkedHashMap<String, Object> res = new LinkedHashMap<>();

        try {
            List<UserModel> data = userRepository.checkLogin(username, password);
            res.put("data", data);
            return ResponseEntity.ok().body(res);
        } catch (Exception e) {
            res.put("data", e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }

    @GetMapping(path = "/recruitment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllJob() {
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        List<JobModel> data = jobRepository.getJobList();
        res.put("data", data);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping(path = "/recruitment/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllJobDetail(@PathVariable String name) {
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        List<JobDetailModel> data = jobRepository.getJobDetailList(name);
        res.put("data", data);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping(path = "/yes", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllJson() {
        String uri = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";
        RestTemplate restTemplate = new RestTemplate();

        JSONArray json = new JSONArray(Objects.requireNonNull(restTemplate.getForObject(uri, Object[].class)));
        System.out.println(json);
        System.out.println(json.getJSONObject(0).get("id"));

        return json.toString();
    }
}
