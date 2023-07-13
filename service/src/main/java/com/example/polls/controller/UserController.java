package com.example.polls.controller;

import com.example.polls.model.User;
import com.example.polls.payload.*;
import com.example.polls.repository.UserRepository;
import com.example.polls.security.UserPrincipal;
import com.example.polls.security.CurrentUser;
import com.example.polls.util.ErrorCodes;
import com.example.polls.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/user/change-password")
    public PasswordChangeResponse changePassword(@RequestBody PasswordChangeRequest request,@CurrentUser UserPrincipal currentUser) {

        if(request == null) {
            return new PasswordChangeResponse(ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"request"));
        }
        if(Utils.isEmpty(request.getUserName())) {
            return new PasswordChangeResponse(ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"user name"));
        }
        if(Utils.isEmpty(request.getOldPassword())) {
            return new PasswordChangeResponse(ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"old password"));
        }

        if(Utils.isEmpty(request.getNewPassword())) {
            return new PasswordChangeResponse(ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_MISSING,"new password"));
        }

        if(!request.getUserName().equalsIgnoreCase(currentUser.getUsername())) {
            return new PasswordChangeResponse(ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_INVALID,"user name"));
        }

        Optional<User> result = userRepository.findByUsername(request.getUserName());
        if(!result.isPresent()) {
            return new PasswordChangeResponse(ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_INVALID,"user name"));
        }



        User userEO = result.get();
        if(!userEO.getPassword().equalsIgnoreCase(Utils.getMd5(request.getOldPassword().trim()))) {
            return new PasswordChangeResponse(ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_INVALID,"old password"));
        }

        userEO.setPassword(Utils.getMd5(request.getNewPassword().trim()));
        try {
            userRepository.save(userEO);
            return new PasswordChangeResponse("Password updated successfully!");
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return new PasswordChangeResponse(ErrorCodes.getServiceError(ErrorCodes.ERROR_GENERAL_CUSTOM,"Failed to update password!"));
    }

}
