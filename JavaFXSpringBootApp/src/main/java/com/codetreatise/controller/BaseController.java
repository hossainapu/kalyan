package com.codetreatise.controller;

import com.codetreatise.config.StageManager;
import com.codetreatise.view.FxmlView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;

public class BaseController {

    @Lazy
    @Autowired
    private StageManager stageManager;

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }
    @FXML private void uploadApplication() {stageManager.switchScene(FxmlView.APPLICATION_UPLOAD);}

    @FXML private void showApplication(ActionEvent event) {
        stageManager.switchScene(FxmlView.LIST_APPLICATION);
    }

    @FXML private void enrollApplication(ActionEvent event) {
        stageManager.switchScene(FxmlView.APPLICATION);
    }

    @FXML private void dashboard(ActionEvent event) {
        stageManager.switchScene(FxmlView.DASHBOARD);
    }

    @FXML private void goBack() {
        stageManager.switchScene(FxmlView.LIST_APPLICATION);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        stageManager.switchScene(FxmlView.LOGIN);
    }

    @FXML private  void createUser() {
        stageManager.switchScene(FxmlView.USER);
    }
}
