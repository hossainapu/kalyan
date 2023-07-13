package com.codetreatise.controller;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.codetreatise.common.Defs;
import com.codetreatise.common.Utils;
import com.codetreatise.dto.Authority;
import com.codetreatise.entity.District;
import com.codetreatise.entity.Division;
import com.codetreatise.entity.EnrollAuthority;
import com.codetreatise.entity.Thana;
import com.codetreatise.repository.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.codetreatise.config.StageManager;
import com.codetreatise.service.UserService;
import com.codetreatise.view.FxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * @author Ram Alapure
 * @since 05-04-2017
 */

@Controller
public class LoginController implements Initializable{

	@FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label lblLogin;
    
    @Autowired
    private UserService userService;
    
    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
	private BaseDao dao;
        
	@FXML
    private void login(ActionEvent event) throws IOException{
    	if(userService.authenticate(getUsername(), getPassword())){
			Defs.LOGGED_USER = getUsername();
			List<Division> div = dao.findAll(Division.class);
			List<District> dis = dao.findAll(District.class);
			List<Thana> tha = dao.findAll(Thana.class);
			Defs.prepareList(div,dis,tha);

			EnrollAuthority enrollAuthority = dao.findById(EnrollAuthority.class,1);
			if(enrollAuthority == null) {
				Utils.failedAlert("EnrollAuthority not found!");
				return;
			}
			Defs.AUTHORITY = enrollAuthority;

			Defs.AUTHORITY_LIST.clear();

			List<com.codetreatise.entity.Authority> authorityList = dao.findAll(com.codetreatise.entity.Authority.class);
			if(authorityList != null && !authorityList.isEmpty()) {
				authorityList.stream().filter(a-> a != null).forEach(authority -> {
					Defs.AUTHORITY_LIST.add(new Authority(authority));
				});
			}

    		stageManager.switchScene(FxmlView.DASHBOARD);
    		
    	}else{
    		lblLogin.setText("Login Failed.");
    	}
    }
	
	public String getPassword() {
		return password.getText();
	}

	public String getUsername() {
		return username.getText();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Defs.LOGGED_USER = null;
	}

}
