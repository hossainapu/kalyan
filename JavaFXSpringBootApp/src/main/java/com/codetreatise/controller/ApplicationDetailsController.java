package com.codetreatise.controller;

import com.codetreatise.common.Defs;
import com.codetreatise.common.Utils;
import com.codetreatise.dto.ApplicationInfo;
import com.codetreatise.service.IApplicationService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ApplicationDetailsController extends BaseController implements Initializable {

    @Autowired
    private IApplicationService service;

    @FXML private TextField referenceNumber;

    @FXML
    private TextField personName;

    @FXML
    private TextField fatherName;

    @FXML
    private TextField spouseName;

    @FXML
    private RadioButton rbMale;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private RadioButton rbThird;

    @FXML
    private TextField nidNumber;

    @FXML
    private TextField contactNumber;

    @FXML
    private TextField emergencyContactNumber;

    @FXML
    private TextField cDivision;

    @FXML
    private TextField cDistrict;

    @FXML
    private TextField cThana;

    @FXML
    private TextField cAddressLine;

    @FXML
    private TextField pDivision;

    @FXML
    private TextField pDistrict;

    @FXML
    private TextField pThana;

    @FXML
    private TextField pAddressLine;

    @FXML
    private ImageView photo;

    @FXML
    private ImageView signature;

    @FXML private Button firstCheckBox;

    @FXML private Button secondCheckBox;

    @FXML private  Button thirdCheckBox;

    @FXML private Button fourthCheckBox;

    @FXML private TextField dateOfBirth;

    @FXML private TextField bloodGroup;

    @FXML private TextField maritalStatus;

    @FXML private TextField emergencyName;

    @FXML private TextField emergencyRelation;

    @FXML private TextField bankTransactionNumber;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Integer applicationId = Defs.APPLICATION_ID;
        if(Utils.isNull(applicationId)) {
            Utils.failedAlert("Application Id not properly loaded!");
        } else {
            Defs.APPLICATION_ID = null;
        }

        ApplicationInfo applicationInfo = service.findById(applicationId);
        setData(applicationInfo);
    }

    private void setData(ApplicationInfo applicationInfo) {
        this.personName.setText(applicationInfo.getNameEn());
        this.fatherName.setText(applicationInfo.getFatherEn());
        this.spouseName.setText(applicationInfo.getSpouseEn());
        if(applicationInfo.getGender().equalsIgnoreCase("MALE")) {
            this.rbMale.setSelected(true);
        } else if(applicationInfo.getGender().equalsIgnoreCase("FEMALE")) {
            this.rbFemale.setSelected(true);
        } else {
            this.rbThird.setSelected(true);
        }
        this.nidNumber.setText(applicationInfo.getNid());
        this.contactNumber.setText(applicationInfo.getContactNumber());
        this.emergencyContactNumber.setText(applicationInfo.getEmergencyContact());
        this.pDivision.setText(applicationInfo.getPermanentDivision());
        this.pDistrict.setText(applicationInfo.getPermanentDistrict());
        this.pThana.setText(applicationInfo.getPermanentThana());
        this.pAddressLine.setText(applicationInfo.getPermanentAddressLine());
        this.bankTransactionNumber.setText(applicationInfo.getBankTransactionNumber());

        this.cDivision.setText(applicationInfo.getPresentDivision());
        this.cDistrict.setText(applicationInfo.getPresentDistrict());
        this.cThana.setText(applicationInfo.getPresentThana());
        this.cAddressLine.setText(applicationInfo.getPresentAddressLine());
        this.referenceNumber.setText(applicationInfo.getReferenceNumber());
        try {
            if(applicationInfo.getPhoto() != null && applicationInfo.getSignature() != null) {
                ByteArrayInputStream bis = new ByteArrayInputStream(applicationInfo.getPhoto());
                this.photo.setStyle("-fx-background-color: none;");
                this.photo.setImage(new Image(bis));
                bis = new ByteArrayInputStream(applicationInfo.getSignature());
                this.signature.setStyle("-fx-background-color: none;");
                this.signature.setImage(new Image(bis));
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        if(applicationInfo.getNumberOfEmiPaid() != null) {
            if(applicationInfo.getNumberOfEmiPaid().intValue() ==0) {
                this.firstCheckBox.setStyle("-fx-background-color: red;");
                this.secondCheckBox.setStyle("-fx-background-color: red;");
                this.thirdCheckBox.setStyle("-fx-background-color: red;");
                this.fourthCheckBox.setStyle("-fx-background-color: red;");
            } else if(applicationInfo.getNumberOfEmiPaid() ==1) {
                this.firstCheckBox.setStyle("-fx-background-color: green;");
                this.secondCheckBox.setStyle("-fx-background-color: red;");
                this.thirdCheckBox.setStyle("-fx-background-color: red;");
                this.fourthCheckBox.setStyle("-fx-background-color: red;");
            } else if(applicationInfo.getNumberOfEmiPaid() ==2) {
                this.firstCheckBox.setStyle("-fx-background-color: green;");
                this.secondCheckBox.setStyle("-fx-background-color: green;");
                this.thirdCheckBox.setStyle("-fx-background-color: red;");
                this.fourthCheckBox.setStyle("-fx-background-color: red;");
            } else if(applicationInfo.getNumberOfEmiPaid() ==3) {
                this.firstCheckBox.setStyle("-fx-background-color: green;");
                this.secondCheckBox.setStyle("-fx-background-color: green;");
                this.thirdCheckBox.setStyle("-fx-background-color: green;");
                this.fourthCheckBox.setStyle("-fx-background-color: red;");
            } else {
                this.firstCheckBox.setStyle("-fx-background-color: green;");
                this.secondCheckBox.setStyle("-fx-background-color: green;");
                this.thirdCheckBox.setStyle("-fx-background-color: green;");
                this.fourthCheckBox.setStyle("-fx-background-color: green;");
            }
        }
        if(applicationInfo.getDateOfBirth() != null) {
            this.dateOfBirth.setText(Utils.dateToString(applicationInfo.getDateOfBirth()));
        }
        this.bloodGroup.setText(applicationInfo.getBloodGroup());
        this.maritalStatus.setText(applicationInfo.getMaritalStatus());
        this.emergencyName.setText(applicationInfo.getEmergencyName());
        this.emergencyRelation.setText(applicationInfo.getEmergencyRelation());

    }
}
