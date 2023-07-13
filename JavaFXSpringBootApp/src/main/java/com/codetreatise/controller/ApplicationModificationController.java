package com.codetreatise.controller;

import com.codetreatise.common.Defs;
import com.codetreatise.common.SelectItem;
import com.codetreatise.common.ServiceError;
import com.codetreatise.common.Utils;
import com.codetreatise.dto.ApplicationInfo;
import com.codetreatise.service.IApplicationService;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class ApplicationModificationController extends BaseController implements Initializable {

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
    private ComboBox<SelectItem> cDivisionId;

    @FXML
    private ComboBox<SelectItem> cDistrictId;

    @FXML
    private ComboBox<SelectItem> cThanaId;

    @FXML
    private TextField cAddressLine;

    @FXML
    private ComboBox<SelectItem> pDivisionId;

    @FXML
    private ComboBox<SelectItem> pDistrictId;

    @FXML
    private ComboBox<SelectItem> pThanaId;

    @FXML
    private TextField pAddressLine;

    @FXML private TextField bankTransactionNumber;

    @FXML
    private ImageView photo;

    @FXML
    private ImageView signature;

    @FXML private Button scanPhoto;

    @FXML private Button scanSignature;

    @FXML private DatePicker dateOfBirth;

    @FXML private ComboBox<String> bloodGroup;

    @FXML private ComboBox<String> maritalStatus;

    @FXML private ComboBox<String> applicationType;

    @FXML private ComboBox<Integer> installmentPaid;

    @FXML private TextField emergencyName;

    @FXML private TextField emergencyRelation;

    private Integer appId;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Integer applicationId = Defs.APPLICATION_ID;
        appId = Defs.APPLICATION_ID;
        Defs.APPLICATION_ID = null;
        ApplicationInfo applicationInfo = service.findById(applicationId);
        setData(applicationInfo);
        bloodGroup.setItems(FXCollections.observableArrayList(Utils.getBloodGroups()));
        maritalStatus.setItems(FXCollections.observableArrayList(Utils.getMaritalStatus()));
        applicationType.setItems(FXCollections.observableArrayList(Utils.getApplicationTypes()));
        installmentPaid.setItems(FXCollections.observableArrayList(Arrays.asList(1,2,3,4)));

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



    @FXML private void updateApplication() {
        if(Utils.isEmpty(this.referenceNumber.getText())) {
            Utils.missingAlert(this.referenceNumber.getId());
            return;
        }
        if(Utils.isEmpty(this.personName.getText())) {
            Utils.missingAlert(this.personName.getId());
            return;
        }
        if(Utils.isEmpty(this.fatherName.getText())) {
            Utils.missingAlert(this.fatherName.getId());
            return;
        }
        if(Utils.isEmpty(this.contactNumber.getText())) {
            Utils.missingAlert(this.contactNumber.getId());
            return;
        }
        if(!Utils.isValidMobile(this.contactNumber.getText())) {
            Utils.isValidMobile(this.contactNumber.getId());
            return;
        }

        if(Utils.isEmpty(this.bankTransactionNumber.getText())) {
            Utils.missingAlert(this.bankTransactionNumber.getId());
            return;
        }

        if(this.dateOfBirth.getValue() == null) {
            Utils.missingAlert(this.dateOfBirth.getId());
            return;
        }

        if(!Utils.isEmpty(this.emergencyContactNumber.getText()) && !Utils.isValidMobile(this.emergencyContactNumber.getText())) {
            Utils.isValidMobile(this.emergencyContactNumber.getId());
            return;
        }

        if(Utils.isEmpty(this.pAddressLine.getText())) {
            Utils.missingAlert("permanentAddressLine");
            return;
        }
        if(Utils.isEmpty(this.cAddressLine.getText())) {
            Utils.missingAlert("presentAddressLine");
            return;
        }

        Object object =service.updateApplication(getApplication());
        if(object instanceof ServiceError) {
            Utils.failedAlert(((ServiceError)object).getErrorMessage());
            return;
        } else {
            Utils.successAlert("Application updated successfully!");
        }
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
        this.emergencyName.setText(applicationInfo.getEmergencyName());
        this.emergencyRelation.setText(applicationInfo.getEmergencyRelation());
        this.pDivisionId.setItems(FXCollections.observableArrayList(Utils.getDivisions()));
        if(!Utils.isEmpty(applicationInfo.getPermanentDivision())) {
            this.pDivisionId.setValue(Utils.getSelectedItem(Utils.getDivisions(),applicationInfo.getPermanentDivision()));
        }
        if(applicationInfo.getDateOfBirth() != null) {
            this.dateOfBirth.setValue(Utils.getLocalDate(applicationInfo.getDateOfBirth()));
        }
        if(!Utils.isEmpty(applicationInfo.getPermanentDivision())) {
            List<SelectItem> itemList = Utils.getDistricts(applicationInfo.getPermanentDivision());
            this.pDistrictId.setItems(FXCollections.observableArrayList(itemList));
            if(!Utils.isEmpty(applicationInfo.getPermanentDistrict())) {
                this.pDistrictId.setValue(Utils.getSelectedItem(itemList,applicationInfo.getPermanentDistrict()));
            }
        }
        if(!Utils.isEmpty(applicationInfo.getPermanentDistrict())) {
            List<SelectItem> items = Utils.getThanas(applicationInfo.getPermanentDistrict());
            this.pThanaId.setItems(FXCollections.observableArrayList(items));
            if(!Utils.isEmpty(applicationInfo.getPermanentThana())) {
                this.pThanaId.setValue(Utils.getSelectedItem(items,applicationInfo.getPermanentThana()));
            }
        }
        this.pAddressLine.setText(applicationInfo.getPermanentAddressLine());

        this.cDivisionId.setItems(FXCollections.observableArrayList(Utils.getDivisions()));
        if(!Utils.isEmpty(applicationInfo.getPresentDivision())) {
            this.cDivisionId.setValue(Utils.getSelectedItem(Utils.getDivisions(),applicationInfo.getPresentDivision()));
        }
        if(!Utils.isEmpty(applicationInfo.getPresentDivision())) {
            List<SelectItem> items = Utils.getDistricts(applicationInfo.getPresentDivision());
            this.cDistrictId.setItems(FXCollections.observableArrayList());
            if(!Utils.isEmpty(applicationInfo.getPresentDistrict())) {
                this.cDistrictId.setValue(Utils.getSelectedItem(items,applicationInfo.getPresentDistrict()));
            }
        }
        if(!Utils.isEmpty(applicationInfo.getPresentDistrict())) {
            List<SelectItem> items = Utils.getThanas(applicationInfo.getPresentDistrict());
            this.cThanaId.setItems(FXCollections.observableArrayList());
            if(!Utils.isEmpty(applicationInfo.getPresentThana())) {
                this.cThanaId.setValue(Utils.getSelectedItem(items,applicationInfo.getPresentThana()));
            }
        }
        this.bankTransactionNumber.setText(applicationInfo.getBankTransactionNumber());
        this.cAddressLine.setText(applicationInfo.getPresentAddressLine());
        this.referenceNumber.setText(applicationInfo.getReferenceNumber());

        this.bloodGroup.setValue(applicationInfo.getBloodGroup());
        this.maritalStatus.setValue(applicationInfo.getMaritalStatus());
        this.installmentPaid.setValue(applicationInfo.getNumberOfEmiPaid());
        this.applicationType.setValue(applicationInfo.getApplicationType());


        try {
            if(applicationInfo.getPhoto() != null && applicationInfo.getSignature() != null) {
                ByteArrayInputStream bis = new ByteArrayInputStream(applicationInfo.getPhoto());
                this.photo.setImage(new Image(bis));
                bis = new ByteArrayInputStream(applicationInfo.getSignature());
                this.signature.setImage(new Image(bis));
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }


    }

    private ApplicationInfo getApplication() {

        ApplicationInfo applicationInfo = new ApplicationInfo();
        applicationInfo.setId(this.appId);
        applicationInfo.setReferenceNumber(this.referenceNumber.getText());
        applicationInfo.setNameEn(this.personName.getText());
        applicationInfo.setFatherEn(this.fatherName.getText());
        applicationInfo.setSpouseEn(this.spouseName.getText());
        if(this.rbMale.isSelected()) {
            applicationInfo.setGender(Defs.GENDER_MALE);
        } else if(this.rbFemale.isSelected()){
            applicationInfo.setGender(Defs.GENDER_FEMALE);
        } else {
            applicationInfo.setGender(Defs.GENDER_THIRD);
        }
        applicationInfo.setMaritalStatus(this.maritalStatus.getSelectionModel().getSelectedItem());
        applicationInfo.setBloodGroup(this.bloodGroup.getSelectionModel().getSelectedItem());
        applicationInfo.setEmergencyName(this.emergencyName.getText());
        applicationInfo.setEmergencyRelation(this.emergencyRelation.getText());
        applicationInfo.setDateOfBirth(this.dateOfBirth.getValue() != null ? Utils.getDateFromLocalDate(this.dateOfBirth.getValue()) : null);
        applicationInfo.setNid(this.nidNumber.getText());
        applicationInfo.setContactNumber(this.contactNumber.getText());
        applicationInfo.setEmergencyContact(this.emergencyContactNumber.getText());
        applicationInfo.setPresentDivision(this.cDivisionId.getSelectionModel().getSelectedItem() != null ? this.cDivisionId.getSelectionModel().getSelectedItem().getName() : null);
        applicationInfo.setPresentDistrict(this.cDistrictId.getSelectionModel().getSelectedItem() != null ? this.cDistrictId.getSelectionModel().getSelectedItem().getName() : null);
        applicationInfo.setPresentThana(this.cThanaId.getSelectionModel().getSelectedItem() != null ? this.cThanaId.getSelectionModel().getSelectedItem().getName() : null);
        applicationInfo.setPresentAddressLine(this.cAddressLine.getText());

        applicationInfo.setPermanentDivision(this.pDivisionId.getSelectionModel().getSelectedItem() != null ? this.pDivisionId.getSelectionModel().getSelectedItem().getName() : null);
        applicationInfo.setPermanentDistrict(this.pDistrictId.getSelectionModel().getSelectedItem() != null ? this.pDistrictId.getSelectionModel().getSelectedItem().getName() : null);
        applicationInfo.setPermanentThana(this.pThanaId.getSelectionModel().getSelectedItem() != null ? this.pThanaId.getSelectionModel().getSelectedItem().getName() : null);
        applicationInfo.setPermanentAddressLine(this.pAddressLine.getText());
        applicationInfo.setBankTransactionNumber(this.bankTransactionNumber.getText());
        try {
            BufferedImage bImage = SwingFXUtils.fromFXImage(this.photo.getImage(), null);
            ByteArrayOutputStream s = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", s);
            byte[] res  = s.toByteArray();
            s.close();
            applicationInfo.setPhoto(res);

            bImage = SwingFXUtils.fromFXImage(this.signature.getImage(),null);
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
}
