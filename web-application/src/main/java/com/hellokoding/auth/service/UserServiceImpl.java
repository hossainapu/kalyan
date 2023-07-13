package com.hellokoding.auth.service;

import com.hellokoding.auth.common.Defs;
import com.hellokoding.auth.common.ErrorCodes;
import com.hellokoding.auth.common.ServiceError;
import com.hellokoding.auth.common.Utils;
import com.hellokoding.auth.model.dto.User;
import com.hellokoding.auth.repository.BaseRepository;
import com.hellokoding.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ILookupService lookupService;

    @Autowired
    private BaseRepository repository;

    @Override
    public Object createUser(User user) {
        if(user == null) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"user");
        }
        if(Utils.isEmpty(user.getUsername())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"username");
        }
        if(Utils.isEmpty(user.getContactNumber())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"contact number");
        }
        if(Utils.isEmpty(user.getName())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"name");
        }

        if(Utils.isEmpty(user.getPassword())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"Password");
        }

        if(Utils.isEmpty(user.getPasswordConfirm())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"confirm password");
        }

        if(Utils.isEmpty(user.getRoleName())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"role name");
        }

        if(!user.getPassword().equals(user.getPasswordConfirm())) {
            return new ServiceError(ErrorCodes.ERROR_CUSTOM,"Password and Confirm password doesn't matched!");
        }
        if(!Utils.isValidMobile(user.getContactNumber())) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"contact number");
        }

        com.hellokoding.auth.model.db.User userEO = userRepository.findByUsername(user.getUsername().trim().toUpperCase());
        if(userEO != null && !Utils.isNull(userEO.getId())) {
            return new ServiceError(ErrorCodes.ERROR_CUSTOM,"User name already exists!");
        }

        User loggedInUser = lookupService.getLoggedInUser();

        userEO = new com.hellokoding.auth.model.db.User();

        userEO.setName(user.getName());
        userEO.setUsername(user.getUsername().trim().toUpperCase());
        userEO.setContactNumber(user.getContactNumber().trim().toUpperCase());
        userEO.setRoleName(user.getRoleName().trim());
        userEO.setEmail(user.getEmail());
        userEO.setPassword(Utils.getMd5(user.getPassword().trim()));
        userEO.setAuthorityCode("GCC");
        userEO.setCreateAt(Utils.getCurrentTimeStamp());
        userEO.setUpdatedAt(Utils.getCurrentTimeStamp());
        if(loggedInUser != null && !Utils.isEmpty(loggedInUser.getUsername())) {
            userEO.setCreatedBy(loggedInUser.getUsername());
        }

        try {
            userRepository.save(userEO);
            return Defs.OPERATION_SUCCESS;
        } catch (Throwable t) {
            t.printStackTrace();
            return Defs.OPERATION_FAILED;
        }
    }

    @Override
    public List<User> searchUser(String userName,String contactNumber,String emailAddress,String roleName,String name) {
        String sql = "SELECT o FROM User o where 1=1 ";
        Map<String,Object> param = new HashMap<>();
        if(!Utils.isEmpty(userName)) {
            sql += " AND o.username =:username ";
            param.put("username",userName.trim().toUpperCase());
        }
        if(!Utils.isEmpty(contactNumber)) {
            sql += " AND o.contactNumber = :contactNumber ";
            param.put("contactNumber",contactNumber.trim());
        }
        if(!Utils.isEmpty(emailAddress)) {
            sql += " AND UPPER(o.email) =:email ";
            param.put("email",emailAddress.trim().toUpperCase());
        }
        if(!Utils.isEmpty(roleName)) {
            sql += " AND o.roleName = :roleName ";
            param.put("roleName",roleName.trim());
        }

        if(!Utils.isEmpty(name)) {
            sql += "AND UPPER(o.name) like :name ";
            param.put("name","%"+name.trim().toUpperCase()+"%");
        }

        List<User> list = new ArrayList<>();

        List<com.hellokoding.auth.model.db.User> results = repository.findByJpql(sql,com.hellokoding.auth.model.db.User.class,param);
        if(results != null && results.size() >0) {
            results.stream().filter(user -> user != null).forEach(user->{
                list.add(new User(user));
            });
        }

        return list;
    }

    @Override
    public User findById(Integer id) {
        return new User(userRepository.findById(id).orElse(null));
    }

    @Override
    public Object updateUser(User user) {
        return updateUser(user,false);
    }

    @Override
    public Object updateUser(User user,boolean isReset) {
        if(user == null) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"user");
        }
        if(Utils.isNull(user.getId())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"user id");
        }

        if(Utils.isEmpty(user.getName())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"name");
        }

        if(Utils.isEmpty(user.getUsername())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"username");
        }

        if(Utils.isEmpty(user.getPassword())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"password");
        }

        if(Utils.isEmpty(user.getPasswordConfirm())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"confirm password");
        }

        if(Utils.isEmpty(user.getContactNumber())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"contact number");
        }

        if(Utils.isEmpty(user.getRoleName())) {
            return new ServiceError(ErrorCodes.ERROR_MISSING,"role name");
        }

        if(!user.getPassword().equals(user.getPasswordConfirm())) {
            return new ServiceError(ErrorCodes.ERROR_CUSTOM,"Password and Confirm password doesn't matched!");
        }
        if(!Utils.isValidMobile(user.getContactNumber())) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"contact number");
        }

        com.hellokoding.auth.model.db.User userEO = userRepository.findByUsername(user.getUsername().trim().toUpperCase());
        if(userEO == null && Utils.isNull(userEO.getId())) {
            return new ServiceError(ErrorCodes.ERROR_INVALID,"user id");
        }

        com.hellokoding.auth.model.db.User userDUP = userRepository.findFirstByEmail(user.getEmail());
        if(userDUP != null && !Utils.isEmpty(userDUP.getEmail())) {
            if(userDUP.getId() != user.getId()) {
                return new ServiceError(ErrorCodes.ERROR_DUPLICATE,"email address");
            }
        }

        userEO.setName(user.getName().trim());
        userEO.setContactNumber(user.getContactNumber().trim().toUpperCase());
        if(isReset) {
            userEO.setPassword(Utils.getMd5(user.getPassword().trim()));
        }
        userEO.setRoleName(user.getRoleName().trim());
        userEO.setEmail(user.getEmail());
        userEO.setAuthorityCode("GCC");
        userEO.setUpdatedAt(Utils.getCurrentTimeStamp());

        try {
            userRepository.save(userEO);
            return Defs.OPERATION_SUCCESS;
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return Defs.OPERATION_FAILED;
    }

}
