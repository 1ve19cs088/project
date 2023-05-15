package com.votingservice.voterdata.service.admin;

import com.votingservice.voterdata.model.Admin;

public interface AdminManagement {
    Admin registerAdmin(Admin admin) throws Exception;

    Admin getAdmin(Admin admin) throws Exception;

    Admin updateAdmin(Admin admin) throws Exception;

    void deleteAdmin(String employeeId) throws Exception;
}
