package com.votingservice.voterdata.utilities;

import com.votingservice.voterdata.model.Admin;
import com.votingservice.voterdata.model.UserApplication;
import com.votingservice.voterdata.model.Voter;
import com.votingservice.voterdata.repository.UserDetailsManagementRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AddUserDetails {

    @Autowired
    private UserDetailsManagementRepository udmrp;

    private UserApplication user;
    private static final Logger LOGGER = LogManager.getLogger(AddUserDetails.class);

    public void  AddVoterUserDetails(Voter voter) {
        UserApplication user = new UserApplication();
        user.setUsername(voter.getFirstName() + voter.getStudentId());
        user.setPassword(new BCryptPasswordEncoder().encode(voter.getPassword()));
        user.setRole("VOTER");
        udmrp.save(user);
    }

    public void AddAdminUserDetails(Admin admin) {
        try{
            UserApplication user = new UserApplication();
            user.setUsername(admin.getFirstName() + admin.getEmployeeId());
            String pwd = admin.getPassword();
            user.setPassword(new BCryptPasswordEncoder().encode(pwd));
            user.setRole("ADMIN");
            udmrp.save(user);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public UserApplication getUserDetails(){
        return user;
    }

}
