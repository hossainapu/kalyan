package com.codetreatise.controller;

import com.codetreatise.common.Defs;
import com.codetreatise.common.SelectItem;
import com.codetreatise.common.ServiceError;
import com.codetreatise.common.Utils;
import com.codetreatise.config.StageManager;
import com.codetreatise.dto.ApplicationInfo;
import com.codetreatise.service.impl.ApplicationService;
import com.codetreatise.view.FxmlView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

@Controller
public class EnrollApplicationController extends BaseController implements Initializable {


    @FXML private TextField officeCode;

    @FXML private TextField referenceNumber;

    @FXML private TextField personName;

    @FXML private TextField fatherName;

    @FXML private TextField spouseName;


    @FXML private RadioButton rbMale;

    @FXML private RadioButton rbFemale;

    @FXML private RadioButton rbThird;

    @FXML private ComboBox<String> bloodGroup;

    @FXML private ComboBox<String> maritalStatus;

    @FXML private TextField emergencyName;

    @FXML private TextField emergencyRelation;

    @FXML private DatePicker dateOfBirth;

    @FXML private TextField nidNumber;

    @FXML private ComboBox<String> applicationType;

    @FXML private  ComboBox<Integer> installmentPaid;

    @FXML private TextField contactNumber;

    @FXML private TextField emergencyContactNumber;

    @FXML private ComboBox<SelectItem> cDivisionId;

    @FXML private ComboBox<SelectItem> cDistrictId;

    @FXML private ComboBox<SelectItem> cThanaId;

    @FXML private TextField cAddressLine;

    @FXML private ComboBox<SelectItem> pDivisionId;

    @FXML private ComboBox<SelectItem> pDistrictId;

    @FXML private ComboBox<SelectItem> pThanaId;

    @FXML private TextField pAddressLine;

    @FXML private TextField bankTransactionNumber;


    @FXML private ImageView photo;

    @FXML private ImageView signature;

    @FXML private Button scanPhoto;

    @FXML private Button scanSignature;

    @FXML private VBox leftPanel;

    @FXML private VBox centerPanel;

    @Autowired
    private ApplicationService service;


    @FXML void reset(ActionEvent event) {
        clearFields();
    }



    @FXML void saveApplication(ActionEvent event) {

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(SwingFXUtils.fromFXImage(new Image(getClass().getResourceAsStream("/images/face.png")),null), "png", bos );
            byte [] data = bos.toByteArray();

            bos = new ByteArrayOutputStream();
            ImageIO.write(SwingFXUtils.fromFXImage(this.photo.getImage(),null),"png",bos);
            byte[] data1 = bos.toByteArray();

            if(data.length == data1.length) {
                Utils.failedAlert("Please scan photo!");
                return;
            }

            bos = new ByteArrayOutputStream();
            ImageIO.write(SwingFXUtils.fromFXImage(new Image(getClass().getResourceAsStream("/images/signature.png")),null),"png",bos);
            int originalLength = bos.toByteArray().length;

            bos = new ByteArrayOutputStream();

            ImageIO.write(SwingFXUtils.fromFXImage(this.signature.getImage(),null),"png",bos);

            int length = bos.toByteArray().length;

            if(originalLength == length) {
                Utils.failedAlert("Please scan a signature!");
                return;
            }

        } catch (Throwable t) {

        }

        if(Utils.isEmpty(referenceNumber.getText())) {
            Utils.missingAlert(referenceNumber.getId());
            return;
        }
        if(Utils.isEmpty(personName.getText())) {
            Utils.missingAlert(personName.getId());
            return;
        }
        if(Utils.isEmpty(fatherName.getText())) {
            Utils.missingAlert(fatherName.getId());
            return;
        }
        if(Utils.isEmpty(contactNumber.getText())) {
            Utils.missingAlert(contactNumber.getId());
            return;
        }
        if(!Utils.isValidMobile(contactNumber.getText())) {
            Utils.isValidMobile(contactNumber.getId());
            return;
        }

        if(dateOfBirth.getValue() == null) {
            Utils.missingAlert(dateOfBirth.getId());
            return;
        }

        if(Utils.isEmpty(this.officeCode.getText())) {
            Utils.missingAlert(this.officeCode.getId());
            return;
        }

        if(!Utils.isEmpty(emergencyContactNumber.getText()) && !Utils.isValidMobile(emergencyContactNumber.getText())) {
            Utils.isValidMobile(emergencyContactNumber.getId());
            return;
        }

        if(Utils.isEmpty(pAddressLine.getText())) {
            Utils.missingAlert("permanentAddressLine");
            return;
        }
        if(Utils.isEmpty(cAddressLine.getText())) {
            Utils.missingAlert("presentAddressLine");
            return;
        }

        if(!Utils.isEmpty(this.nidNumber.getText()) && !(this.nidNumber.getText().length() ==10 || this.nidNumber.getText().length()==13 || this.nidNumber.getText().length() ==17) ) {
            Utils.failedAlert("Nid value is invalid!");
            return;
        }
        if(Utils.isEmpty(this.bankTransactionNumber.getText())) {
            Utils.missingAlert("Bank transaction number!");
            return;
        }


        Object object = service.saveApplication(getApplication());
        if(object instanceof ServiceError) {
            Utils.failedAlert(((ServiceError)object).getErrorMessage());
            return;
        } else {
            Utils.successAlert("Application saved successfully!");
            clearFields();
        }

    }


    private ApplicationInfo getApplication() {

        ApplicationInfo applicationInfo = new ApplicationInfo();

        applicationInfo.setOfficeCode(this.officeCode.getText());
        applicationInfo.setReferenceNumber(this.officeCode.getText() +"-"+referenceNumber.getText().toUpperCase());
        applicationInfo.setNameEn(personName.getText().toUpperCase());
        applicationInfo.setFatherEn(fatherName.getText().toUpperCase());
        applicationInfo.setSpouseEn(spouseName.getText());
        if(rbMale.isSelected()) {
            applicationInfo.setGender(Defs.GENDER_MALE);
        } else if(rbFemale.isSelected()){
            applicationInfo.setGender(Defs.GENDER_FEMALE);
        } else {
            applicationInfo.setGender(Defs.GENDER_THIRD);
        }
        applicationInfo.setBloodGroup(bloodGroup.getSelectionModel().getSelectedItem());
        applicationInfo.setMaritalStatus(this.maritalStatus.getSelectionModel().getSelectedItem());
        applicationInfo.setEmergencyName(emergencyName.getText());
        applicationInfo.setEmergencyRelation(emergencyRelation.getText());
        applicationInfo.setDateOfBirth(dateOfBirth.getValue() != null ? Utils.getDateFromLocalDate(dateOfBirth.getValue()) : null);
        applicationInfo.setNid(nidNumber.getText());
        applicationInfo.setContactNumber(contactNumber.getText());
        applicationInfo.setEmergencyContact(emergencyContactNumber.getText());
        applicationInfo.setPresentDivision(cDivisionId.getSelectionModel().getSelectedItem() != null ? cDivisionId.getSelectionModel().getSelectedItem().getName() : null);
        applicationInfo.setPresentDistrict(cDistrictId.getSelectionModel().getSelectedItem() != null ? cDistrictId.getSelectionModel().getSelectedItem().getName() : null);
        applicationInfo.setPresentThana(cThanaId.getSelectionModel().getSelectedItem() != null ? cThanaId.getSelectionModel().getSelectedItem().getName() : null);
        applicationInfo.setPresentAddressLine(cAddressLine.getText());

        applicationInfo.setPermanentDivision(pDivisionId.getSelectionModel().getSelectedItem() != null ? pDivisionId.getSelectionModel().getSelectedItem().getName() : null);
        applicationInfo.setPermanentDistrict(pDistrictId.getSelectionModel().getSelectedItem() != null ? pDistrictId.getSelectionModel().getSelectedItem().getName() : null);
        applicationInfo.setPermanentThana(pThanaId.getSelectionModel().getSelectedItem() != null ? pThanaId.getSelectionModel().getSelectedItem().getName() : null);
        applicationInfo.setPermanentAddressLine(pAddressLine.getText());
        applicationInfo.setBankTransactionNumber(bankTransactionNumber.getText());
        try {
            BufferedImage bImage = SwingFXUtils.fromFXImage(photo.getImage(), null);
            ByteArrayOutputStream s = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", s);
            byte[] res  = s.toByteArray();
            s.close();
            applicationInfo.setPhoto(res);

            bImage = SwingFXUtils.fromFXImage(signature.getImage(),null);
            s = new ByteArrayOutputStream();
            ImageIO.write(bImage,"png",s);
            res = s.toByteArray();
            applicationInfo.setSignature(res);
            s.close();

        } catch (Throwable t) {

        }


        applicationInfo.setApplicationType(applicationType.getSelectionModel().getSelectedItem());
        applicationInfo.setNumberOfEmi(4);
        applicationInfo.setNumberOfEmiPaid(installmentPaid.getSelectionModel().getSelectedItem());
        applicationInfo.setCreatedBy(Defs.LOGGED_USER);
        applicationInfo.setUpdatedBy(Defs.LOGGED_USER);
        applicationInfo.setLanguage("ENGLISH");
        return applicationInfo;
    }

    private void clearFields() {

        for (Node node : leftPanel.getChildren()) {
            if (node instanceof HBox) {
                for (Node t : ((HBox) node).getChildren()) {
                    if(t instanceof TextField) {
                        ((TextField)t).setText("");
                    }

                    if(t instanceof ComboBox) {
                        ((ComboBox)t).getSelectionModel().clearSelection();
                    }
                }

            }
        }

        for (Node node : centerPanel.getChildren()) {
            if (node instanceof HBox) {
                for (Node t : ((HBox) node).getChildren()) {
                    if(t instanceof TextField) {
                        if(t.isDisabled()) {
                            System.out.println("--THIS FIELD CANNOT BE EMPTY---");
                        } else {
                            ((TextField) t).setText("");
                        }
                    }

                    if(t instanceof ComboBox) {
                        ((ComboBox)t).getSelectionModel().clearSelection();
                    }
                }

            }
        }

        try {
            photo.setImage(new Image(getClass().getResourceAsStream("/images/face.png")));
            signature.setImage(new Image(getClass().getResourceAsStream("/images/signature.png")));
        } catch (Throwable t) {
            photo.setImage(null);
            signature.setImage(null);
        }
        dateOfBirth.setValue(null);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Utils.isEmpty(Defs.APPLICATION_TYPE)) {
            Utils.failedAlert("Missing Application Type."+Defs.SIX_SEATER_AUTO+" Will be selected by default");
            Defs.APPLICATION_TYPE = Defs.SIX_SEATER_AUTO;
        }
        this.applicationType.setItems(FXCollections.observableArrayList(Defs.APPLICATION_TYPE));
        this.applicationType.setValue(Defs.APPLICATION_TYPE);
        this.installmentPaid.setItems(FXCollections.observableArrayList(1,2,3,4));
        this.cDivisionId.setItems(FXCollections.observableArrayList(Utils.getDivisions()));
        this.pDivisionId.setItems(FXCollections.observableArrayList(Utils.getDivisions()));
        this.maritalStatus.setItems(FXCollections.observableArrayList(Utils.getMaritalStatus()));
        this.bloodGroup.setItems(FXCollections.observableArrayList(Utils.getBloodGroups()));
        this.officeCode.setText(Defs.AUTHORITY.getCode()+"-CIRCLE-"+Defs.AUTHORITY.getCircle());

        this.nidNumber.setTextFormatter(new TextFormatter<>(Utils.getDigitFilter()));
        Utils.addTextLimiter(this.nidNumber,17);
        this.contactNumber.setTextFormatter(new TextFormatter<>(Utils.getDigitFilter()));
        Utils.addTextLimiter(this.contactNumber,11);
        this.emergencyContactNumber.setTextFormatter(new TextFormatter<>(Utils.getDigitFilter()));
        Utils.addTextLimiter(emergencyContactNumber,11);
        this.referenceNumber.setTextFormatter(new TextFormatter<>(Utils.getDigitFilter()));
        Utils.addTextLimiter(this.referenceNumber,5);


        cDivisionId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("--OLD VALUE:"+oldValue);
            if(newValue != null) {
                System.out.println("--NEW VALUE:"+newValue.getName()+"--ID:"+newValue.getId());
                cDistrictId.setItems(FXCollections.observableArrayList(Utils.getDistricts(newValue.getId())));
            }
        });

        cDistrictId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                cThanaId.setItems(FXCollections.observableArrayList(Utils.getThanas(newValue.getId())));
            }
        });

        pDivisionId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("--OLD VALUE:"+oldValue);
            if(newValue != null) {
                System.out.println("--NEW VALUE:"+newValue.getName()+"--ID:"+newValue.getId());
                pDistrictId.setItems(FXCollections.observableArrayList(Utils.getDistricts(newValue.getId())));
            }
        });

        pDistrictId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                pThanaId.setItems(FXCollections.observableArrayList(Utils.getThanas(newValue.getId())));
            }
        });


        scanPhoto.setOnAction(e->{
            try {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select your photo!");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Image Files", "*.png"));
                File selectedFile = fileChooser.showOpenDialog(scanPhoto.getScene().getWindow());
                if(selectedFile != null) {
                    photo.setImage(new Image(selectedFile.toURI().toString()));
                }
            } catch ( Throwable t) {
                t.printStackTrace();
            }

        });


        scanSignature.setOnAction(e->{
            try {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select your Signature!");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Image Files", "*.png"));
                File selectedFile = fileChooser.showOpenDialog(scanSignature.getScene().getWindow());
                if(selectedFile != null) {
                    signature.setImage(new Image(selectedFile.toURI().toString()));
                }
            } catch ( Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
