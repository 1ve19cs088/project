package com.votingservice.voterdata.controller;

import com.votingservice.voterdata.model.Admin;
import com.votingservice.voterdata.service.admin.AdminManagementImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;


@RestController
@RequestMapping("/adminmanagement")

public class AdminMangementController {
    @Autowired
    private AdminManagementImp AdminManagementImp;

    private static final Logger LOGGER = LogManager.getLogger(AdminMangementController.class);

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity createAdmin(@Valid @RequestBody Admin adm) throws Exception {
        HashMap<String, Object> resp = new HashMap<>();
        LOGGER.info("Inside post call "+ adm.getPassword());
        AdminManagementImp.registerAdmin(adm);
        resp.put("admin", adm);
        return new ResponseEntity<>(resp, HttpStatus.OK);

    }

}