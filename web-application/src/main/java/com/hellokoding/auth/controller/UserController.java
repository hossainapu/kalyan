package com.hellokoding.auth.controller;

import com.hellokoding.auth.common.Defs;
import com.hellokoding.auth.common.ServiceError;
import com.hellokoding.auth.common.Utils;
import com.hellokoding.auth.model.dto.User;
import com.hellokoding.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public User initUser() {
        return new User();
    }

    @ModelAttribute("roles")
    public List<String> loadRoles() {
        return Utils.getRoles();
    }

    @GetMapping("/create-user")
    public String loadCreateUser() {
        return "createUser";
    }

    @PostMapping("/create-user")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        Object result = userService.createUser(user);

        if(result instanceof ServiceError) {
            model.addAttribute("user",user);
            model.addAttribute("message",Utils.buildMessage(((ServiceError)result).getMessage(),2));
        }

        if(result instanceof String) {
            if(Defs.OPERATION_SUCCESS.equalsIgnoreCase((String)result)) {
                model.addAttribute("message",Utils.buildMessage("User created successfully!",1));
            } else {
                model.addAttribute("message",Utils.buildMessage("User creation failed!",2));
            }
        }

        return "createUser";
    }

    @GetMapping("/search-user")
    public String loadSearchUser() {
        return "userSummary";
    }

    @PostMapping("/search-user")
    public String searchUser(Model model,@RequestParam("userName") String userName,@RequestParam("contactNumber") String contactNumber,@RequestParam("email") String emailAddress,@RequestParam("roleName") String roleName,@RequestParam("name") String name) {
        List<User> userList = userService.searchUser(userName,contactNumber,emailAddress,roleName,name);
        if(userList != null && userList.size() >0) {
            model.addAttribute("users",userList);
        } else {
            model.addAttribute("users",new ArrayList<>());
        }

        model.addAttribute("userName",userName);
        model.addAttribute("contactNumber",contactNumber);
        model.addAttribute("email",emailAddress);
        model.addAttribute("roleName",roleName);
        model.addAttribute("name",name);

        return "userSummary";
    }

    @GetMapping("/update-user/{id}")
    public String loadUpdateUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes,Model model) {
        User user = userService.findById(id);
        if(user == null || Utils.isNull(user.getId())) {
            redirectAttributes.addFlashAttribute("message",Utils.buildMessage("User not found with given id!",2));
            return "redirect:/user/search-user";
        }

        model.addAttribute("user",user);
        return "updateUser";

    }

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("user") User user,Model model) {
        Object result = userService.updateUser(user);
        if(result instanceof ServiceError) {
            model.addAttribute("message",Utils.buildMessage(((ServiceError)result).getMessage(),2));
        }

        if(result instanceof String) {
            if(Defs.OPERATION_SUCCESS.equalsIgnoreCase((String)result)) {
                model.addAttribute("message",Utils.buildMessage("User updated successfully!",1));
            } else {
                model.addAttribute("message",Utils.buildMessage("User updated failed!",2));
            }
        }

        model.addAttribute("user",user);
        return "updateUser";
    }

    @GetMapping("/reset-password/{id}")
    public String loadResetPassword(@PathVariable("id") Integer userId,RedirectAttributes redirectAttributes,Model model) {
        User user = userService.findById(userId);
        if(user == null || Utils.isNull(user.getId())) {
            redirectAttributes.addFlashAttribute("message",Utils.buildMessage("User not found with given id!",2));
            return "redirect:/user/search-user";
        }

        model.addAttribute("user",user);
        return "resetPassword";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@ModelAttribute("user") User user,Model model) {
        Object result = userService.updateUser(user);
        if(result instanceof ServiceError) {
            model.addAttribute("message",Utils.buildMessage(((ServiceError)result).getMessage(),2));
        }

        if(result instanceof String) {
            if(Defs.OPERATION_SUCCESS.equalsIgnoreCase((String)result)) {
                model.addAttribute("message",Utils.buildMessage("User updated successfully!",1));
            } else {
                model.addAttribute("message",Utils.buildMessage("User updated failed!",2));
            }
        }

        model.addAttribute("user",user);
        return "resetPassword";
    }
}
