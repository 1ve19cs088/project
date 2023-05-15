package com.votingservice.voterdata.service.admin;

import com.votingservice.voterdata.model.Admin;
import com.votingservice.voterdata.repository.AdminManagementRepository;
import com.votingservice.voterdata.utilities.AddUserDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminManagementImp implements AdminManagement {

    @Autowired
    private AdminManagementRepository admr;

    private static final Logger LOGGER = LogManager.getLogger(AdminManagementImp.class);

    @Autowired
    private AddUserDetails addusdel;

    @Override
    public Admin registerAdmin(Admin admin) throws Exception {
        Admin adminRes = admr.save(admin);
        addusdel.AddAdminUserDetails(admin);
        return adminRes;
    }

    @Override
    public Admin getAdmin(Admin admin) throws Exception {
        return null;
    }

    @Override
    public Admin updateAdmin(Admin admin) throws Exception {
        return null;
    }

    @Override
    public void deleteAdmin(String employeeId) throws Exception {

    }


}
