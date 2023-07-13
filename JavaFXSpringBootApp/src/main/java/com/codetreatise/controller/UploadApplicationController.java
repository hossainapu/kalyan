package com.codetreatise.controller;

import com.codetreatise.common.Defs;
import com.codetreatise.common.Utils;
import com.codetreatise.dto.*;
import com.codetreatise.service.IApplicationService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import java.net.URL;
import java.util.*;

@Controller
public class UploadApplicationController extends BaseController implements Initializable {

    @Autowired
    private IApplicationService service;

    @FXML private TextField sReferenceNumber;

    @FXML private TextField sNID;

    @FXML private TextField sName;

    @FXML private TextField sFatherName;

    @FXML private TextField sContactNumber;

    @FXML private ComboBox<String> sApplicationType;

    @FXML private ComboBox<Integer> sEMIPaid;

    @FXML private DatePicker sFromDate;

    @FXML private DatePicker sToDate;

    @FXML private ImageView idServerStatus;


    @FXML private Button btnSelectAll;

    @FXML private TableView<ApplicationInfo> uploadTable;

    @FXML private TableColumn<ApplicationInfo, Boolean> tSelect;

    @FXML private TableColumn<ApplicationInfo,Integer> tApplicationId;

    @FXML private TableColumn<ApplicationInfo,String> tReferenceNumber;

    @FXML private TableColumn<ApplicationInfo,String> tApplicantName;

    @FXML private TableColumn<ApplicationInfo,String> tApplicationType;

    @FXML private TableColumn<ApplicationInfo, Date> tDateOfBirth;

    @FXML private TableColumn<ApplicationInfo,String> tContactNumber;

    @FXML private TableColumn<ApplicationInfo,Boolean> tTotalEMIPaid;

    @FXML private TableColumn<ApplicationInfo,String> tStatus;

    @FXML private Button btnUpload;

    private String token = null;

    private boolean isOnline = false;

    @FXML
    private Pagination pagination;

    private int from=0,to=0;
    private static final int recordPerPage =100;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            RestTemplate template = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

            LoginRequest request = new LoginRequest();
            request.setUserName("ADMIN");
            request.setPassword("Abc@1234.");

            HttpEntity<LoginRequest> entityReq = new HttpEntity<>(request, headers);

            LoginResponse response = template.postForObject(Defs.LOGIN_LINK, entityReq, LoginResponse.class);
            if(response != null && !Utils.isEmpty(response.getAccessToken())) {
                this.token = response.getAccessToken();
                this.isOnline = true;
            }

            idServerStatus.setImage(new Image(getClass().getResourceAsStream("/images/online.png")));

        } catch (Throwable t) {
            t.printStackTrace();
            idServerStatus.setImage(new Image(getClass().getResourceAsStream("/images/offline.png")));
        }

        this.sApplicationType.setItems(FXCollections.observableArrayList(Utils.getApplicationTypes(true)));
        this.sEMIPaid.setItems(FXCollections.observableArrayList(Utils.getInstallmentPaid(true)));
        this.sContactNumber.setTextFormatter(new TextFormatter<>(Utils.getDigitFilter()));
        Utils.addTextLimiter(this.sContactNumber,11);
        this.sNID.setTextFormatter(new TextFormatter<>(Utils.getDigitFilter()));
        Utils.addTextLimiter(this.sNID,17);



        this.tSelect.setCellFactory(cellFactory);
        this.tApplicationId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.tReferenceNumber.setCellValueFactory(new PropertyValueFactory<>("referenceNumber"));
        this.tApplicantName.setCellValueFactory(new PropertyValueFactory<>("nameEn"));
        this.tApplicationType.setCellValueFactory(new PropertyValueFactory<>("applicationType"));
        this.tDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        this.tContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        this.tStatus.setCellValueFactory(new PropertyValueFactory<>("strStatus"));
        this.tTotalEMIPaid.setCellFactory(installmentPaid);

        this.btnSelectAll.setOnAction(e->{

        });

        this.btnUpload.setOnAction(e->{
            Map<Integer,ApplicationInfo> map = new HashMap<>();
            List<EnrollApplication> infoList = new ArrayList<>();
            for (ApplicationInfo app : uploadTable.getItems()) {
                if(app != null && !Utils.isNull(app.getId()) && app.isSelected()) {
                    infoList.add(new EnrollApplication(app));
                }
                if(app != null && !Utils.isNull(app.getId())) {
                    map.put(app.getId(), app);
                }
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.set("Authorization","Bearer "+this.token);

            for (EnrollApplication enrollApplication : infoList) {
                try {
                    RestTemplate template = new RestTemplate();

                    ApplicationEnrollRequest request = new ApplicationEnrollRequest();
                    request.setApplicationInfo(enrollApplication);

                    HttpEntity<ApplicationEnrollRequest> entityReq = new HttpEntity<>(request, headers);
                    ApplicationEnrollResponse response = template.postForObject(Defs.UPLOAD_LINK,entityReq,ApplicationEnrollResponse.class);
                    if(response.isOperationStatus()) {
                        //Utils.successAlert("Successfully uploaded:"+enrollApplication.getReferenceNumber());
                        String sql = "UPDATE APPLICATION_INFO SET STATUS =:status WHERE id =:id ";
                        Map<String,Object> param = new HashMap<>();
                        param.put("status",3);
                        param.put("id",enrollApplication.getId());
                        int count = service.updateByNativeQuery(sql,param);
                        System.out.println("---UPDATED:"+count);
                        map.get(enrollApplication.getId()).setStrStatus("UPLOADED");
                    } else {
                        System.err.println("ERROR:"+response.getError().getErrorMessage());
                    }

                } catch (Throwable t) {
                    t.printStackTrace();
                }
                uploadTable.setItems(FXCollections.observableArrayList(map.values()));
                uploadTable.refresh();

            }


        });

    }


    Callback<TableColumn<ApplicationInfo, Boolean>, TableCell<ApplicationInfo, Boolean>> cellFactory =
            new Callback<TableColumn<ApplicationInfo, Boolean>, TableCell<ApplicationInfo, Boolean>>() {
                @Override
                public TableCell<ApplicationInfo, Boolean> call( final TableColumn<ApplicationInfo, Boolean> param) {
                    final TableCell<ApplicationInfo, Boolean> cell = new TableCell<ApplicationInfo, Boolean>() {
                        final CheckBox checkBox = new CheckBox();

                        @Override
                        public void updateItem(Boolean check, boolean empty) {
                            super.updateItem(check, empty);
                            if(empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                checkBox.selectedProperty().addListener(l->{
                                    if(checkBox.isSelected()) {
                                        getTableView().getItems().get(getIndex()).setSelected(true);
                                    } else {
                                        getTableView().getItems().get(getIndex()).setSelected(false);
                                    }
                                });
                                if(getTableView().getItems().get(getIndex()).getStatus() == 3 || !isOnline) {
                                    checkBox.setDisable(true);
                                }
                                setGraphic(checkBox);
                                setAlignment(Pos.CENTER);
                                setText(null);
                            }
                        }
                    };
                    return cell;
                }
            };

    @FXML public void searchApplication(ActionEvent event) {
        Integer count = service.countApplication(getSearchCriteria());
        System.out.println("--COUNT:"+count);
        pagination.setCurrentPageIndex(0);
        pagination.setPageCount((count/recordPerPage)+1);
        pagination.setPageFactory(this::createPage);
    }

    private Node createPage(int pageIndex) {
        from = pageIndex*recordPerPage;
        to = from + recordPerPage;
        ApplicationSearchCriteria criteria = getSearchCriteria();
        criteria.setStatus(1);
        criteria.setStartIndex(from);
        criteria.setLimit(to);
        this.uploadTable.setItems(FXCollections.observableArrayList(service.searchApplication(criteria)));
        return this.uploadTable;
    }


    private ApplicationSearchCriteria getSearchCriteria() {
        ApplicationSearchCriteria criteria = new ApplicationSearchCriteria();
        if(!Utils.isEmpty(sApplicationType.getSelectionModel().getSelectedItem()) && !sApplicationType.getSelectionModel().getSelectedItem().equalsIgnoreCase("ALL")) {
            criteria.setApplicationType(sApplicationType.getSelectionModel().getSelectedItem());
        }
        if(!Utils.isEmpty(sName.getText())) {
            criteria.setName(sName.getText().trim());
        }
        if(!Utils.isEmpty(sReferenceNumber.getText())) {
            criteria.setReferenceNumber(sReferenceNumber.getText());
        }
        if(!Utils.isEmpty(sContactNumber.getText())) {
            criteria.setContactNumber(sContactNumber.getText().trim());
        }
        if(!Utils.isEmpty(sNID.getText())) {
            criteria.setNid(sNID.getText());
        }

        if(!Utils.isEmpty(sFatherName.getText())) {
            criteria.setFatherName(sFatherName.getText());
        }

        if(sFromDate != null && sFromDate.getValue() != null) {
            criteria.setFromDate(Utils.getDateFromLocalDate(sFromDate.getValue()));
        }

        if(sToDate != null && sToDate.getValue() != null) {
            criteria.setToDate(Utils.getDateFromLocalDate(sToDate.getValue()));
        }

        if(!Utils.isNull(sEMIPaid.getSelectionModel().getSelectedItem())) {
            criteria.setInstallment(sEMIPaid.getSelectionModel().getSelectedItem()+"");
        }

        criteria.setStartIndex(0);
        criteria.setLimit(10);

        return criteria;
    }


    Callback<TableColumn<ApplicationInfo, Boolean>, TableCell<ApplicationInfo, Boolean>> installmentPaid =
            new Callback<TableColumn<ApplicationInfo, Boolean>, TableCell<ApplicationInfo, Boolean>>() {
                @Override
                public TableCell<ApplicationInfo, Boolean> call( final TableColumn<ApplicationInfo, Boolean> param) {
                    final TableCell<ApplicationInfo, Boolean> cell = new TableCell<ApplicationInfo, Boolean>() {

                        @Override
                        public void updateItem(Boolean check, boolean empty) {
                            super.updateItem(check, empty);
                            if(empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                Integer paid = getTableView().getItems().get(getIndex()).getNumberOfEmiPaid();
                                if(paid != null) {
                                    Button b1 = new Button();
                                    Button b2 = new Button();
                                    Button b3 = new Button();
                                    Button b4 = new Button();
                                    if(paid ==0) {
                                        b1.setStyle("-fx-background-color: red;");
                                        b2.setStyle("-fx-background-color: red;");
                                        b3.setStyle("-fx-background-color: red;");
                                        b4.setStyle("-fx-background-color: red;");
                                    } else if(paid ==1) {
                                        b1.setStyle("-fx-background-color: green;");
                                        b2.setStyle("-fx-background-color: red;");
                                        b3.setStyle("-fx-background-color: red;");
                                        b4.setStyle("-fx-background-color: red;");
                                    } else if(paid ==2) {
                                        b1.setStyle("-fx-background-color: green;");
                                        b2.setStyle("-fx-background-color: green;");
                                        b3.setStyle("-fx-background-color: red;");
                                        b4.setStyle("-fx-background-color: red;");
                                    } else if(paid ==3) {
                                        b1.setStyle("-fx-background-color: green;");
                                        b2.setStyle("-fx-background-color: green;");
                                        b3.setStyle("-fx-background-color: green;");
                                        b4.setStyle("-fx-background-color: red;");
                                    } else {
                                        b1.setStyle("-fx-background-color: green;");
                                        b2.setStyle("-fx-background-color: green;");
                                        b3.setStyle("-fx-background-color: green;");
                                        b4.setStyle("-fx-background-color: green;");
                                    }

                                    b1.setDisable(true);
                                    b2.setDisable(true);
                                    b3.setDisable(true);
                                    b4.setDisable(true);

                                    HBox box = new HBox();
                                    box.getChildren().add(b1);
                                    box.getChildren().add(new Label(" "));
                                    box.getChildren().add(b2);
                                    box.getChildren().add(new Label(" "));
                                    box.getChildren().add(b3);
                                    box.getChildren().add(new Label(" "));
                                    box.getChildren().add(b4);

                                    setGraphic(box);
                                    setAlignment(Pos.CENTER);
                                    setText(null);
                                }

                            }
                        }
                    };
                    return cell;
                }
            };
}
