package com.codetreatise.controller;


import com.codetreatise.common.Defs;
import com.codetreatise.common.Utils;
import com.codetreatise.config.StageManager;
import com.codetreatise.dto.Authority;
import com.codetreatise.dto.LoginRequest;
import com.codetreatise.dto.LoginResponse;
import com.codetreatise.dto.OnlineApplicationResponse;
import com.codetreatise.entity.EnrollAuthority;
import com.codetreatise.repository.BaseDao;
import com.codetreatise.service.IApplicationService;
import com.codetreatise.view.FxmlView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.*;

@Controller
public class DashboardController extends BaseController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private BaseDao dao;

    @Autowired
    private IApplicationService service;

    @FXML private ImageView btnOne;

    @FXML private ImageView btnTwo;

    @FXML private ImageView btnThree;

    @FXML private ImageView btnFour;

    @FXML private ImageView btnSettings;

    @FXML private ImageView btnOnlineDownload;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnSettings.setOnMouseClicked(event->{

            Dialog<Authority> dialog = new Dialog<>();
            dialog.setTitle("Authority EnrollAuthority");
            dialog.setResizable(true);

            Label labelAuthority = new Label("Authority: ");
            Label labelCircle = new Label("Circle: ");
            ComboBox<Authority> comAuthority = new ComboBox<>();
            ComboBox<String> comCircle = new ComboBox<>();
            comCircle.setItems(FXCollections.observableArrayList(Arrays.asList("01","02","03","04","05","06","07","08","09","10")));
            comAuthority.setItems(FXCollections.observableArrayList(Defs.AUTHORITY_LIST));

            GridPane grid = new GridPane();
            grid.add(labelAuthority, 1, 1);
            grid.add(comAuthority, 2, 1);
            grid.add(labelCircle, 1, 2);
            grid.add(comCircle, 2, 2);
            dialog.getDialogPane().setContent(grid);

            ButtonType buttonTypeOk = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(buttonTypeOk,buttonTypeCancel);

            dialog.setResultConverter(b->{
                if (b == buttonTypeOk) {
                    if(comAuthority.getSelectionModel().getSelectedItem() == null || Utils.isEmpty(comCircle.getSelectionModel().getSelectedItem())) {
                        Utils.failedAlert("Authority and Circle selection error!");
                        return null;
                    }
                    return new Authority(comAuthority.getSelectionModel().getSelectedItem().getId(),comAuthority.getSelectionModel().getSelectedItem().getCode(), comCircle.getSelectionModel().getSelectedItem());
                }
                if(b == buttonTypeCancel) {
                    dialog.close();
                }

                return null;
            });

            Optional<Authority> result = dialog.showAndWait();

            if (result.isPresent()) {
                String sql = "UPDATE selected_authority SET CODE =:code,CIRCLE=:circle";
                Map<String,Object> map = new HashMap<>();
                map.put("code",result.get().getCode());
                map.put("circle",result.get().getCircle());
                int updated = service.updateByNativeQuery(sql,map);
                System.out.println(updated+" Row Updated");

                if(updated >0) {
                    EnrollAuthority enrollAuthority = dao.findById(EnrollAuthority.class,1);
                    if(enrollAuthority == null) {
                        Utils.failedAlert("EnrollAuthority not found!");
                        return;
                    }
                    Defs.AUTHORITY = enrollAuthority;
                }
            }
        });

        btnOnlineDownload.setOnMouseClicked( event -> {

            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Enter Application uuid");
            dialog.setResizable(true);

            Label labelAuthority = new Label("Application UUID: ");
            TextField fieldUUID = new TextField();

            GridPane grid = new GridPane();
            grid.add(labelAuthority, 1, 1);
            grid.add(fieldUUID, 2, 1);
            dialog.getDialogPane().setContent(grid);
            ButtonType buttonTypeOk = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(buttonTypeOk,buttonTypeCancel);
            dialog.setResultConverter(b->{
                if (b == buttonTypeOk) {
                    if(Utils.isEmpty(fieldUUID.getText())) {
                        Utils.failedAlert("Authority and Circle selection error!");
                        return null;
                    }
                    return fieldUUID.getText();
                }
                if(b == buttonTypeCancel) {
                    dialog.close();
                }

                return null;
            });

            Optional<String> result = dialog.showAndWait();
            if(result.isPresent()) {

                try {
                    RestTemplate template = new RestTemplate();
                    HttpHeaders headers = new HttpHeaders();
                    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

                    LoginRequest request = new LoginRequest();
                    request.setUserName("ADMIN");
                    request.setPassword("Abc@123");

                    HttpEntity<LoginRequest> entityReq = new HttpEntity<>(request, headers);

                    LoginResponse response = template.postForObject(Defs.LOGIN_LINK, entityReq, LoginResponse.class);
                    if (response != null && !Utils.isEmpty(response.getAccessToken())) {
                        headers.set("Authorization", "Bearer " + response.getAccessToken());
                        HttpEntity<OnlineApplicationResponse> appRequest = new HttpEntity<>(headers);
                        ResponseEntity<OnlineApplicationResponse> oResponse = template.exchange(Defs.ONLINE_ENROLL_LINK+result.get(), HttpMethod.GET, appRequest, OnlineApplicationResponse.class);
                        if (oResponse.getStatusCode() == HttpStatus.OK) {
                            if (oResponse.getBody().isOperationStatus()) {
                                Defs.ONLINE = oResponse.getBody();
                            }
                        }
                    }

                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
            this.stageManager.switchScene(FxmlView.ONLINE_ENROLL_APPLICATION);

        });

        btnOne.setOnMouseClicked(event -> {
            Defs.APPLICATION_TYPE = Defs.SIX_SEATER_AUTO;
            stageManager.switchScene(FxmlView.APPLICATION);
        });

        btnTwo.setOnMouseClicked(event -> {
            Defs.APPLICATION_TYPE = Defs.TWO_SEATER_AUTO;
            stageManager.switchScene(FxmlView.APPLICATION);
        });

        btnThree.setOnMouseClicked(event -> {
            Defs.APPLICATION_TYPE = Defs.MANUAL_RICKSHAW;
            stageManager.switchScene(FxmlView.APPLICATION);
        });

        btnFour.setOnMouseClicked(event -> {
            Defs.APPLICATION_TYPE = Defs.BATTERY_RICKSHAW;
            stageManager.switchScene(FxmlView.APPLICATION);
        });
    }
}
