package com.codetreatise.controller;


import com.codetreatise.common.Defs;
import com.codetreatise.common.Utils;
import com.codetreatise.config.StageManager;
import com.codetreatise.dto.ApplicationInfo;
import com.codetreatise.dto.ApplicationSearchCriteria;
import com.codetreatise.service.IApplicationService;
import com.codetreatise.view.FxmlView;
import javafx.collections.FXCollections;;
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
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

@Controller
public class ListApplicationController extends BaseController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private IApplicationService service;

    @FXML
    private TableView<ApplicationInfo> table;

    @FXML
    private TableColumn<ApplicationInfo,String> colName;

    @FXML
    private TableColumn<ApplicationInfo,String> colReferenceNumber;

    @FXML
    private TableColumn<ApplicationInfo,String> colContact;

    @FXML
    private TableColumn<ApplicationInfo,String> colType;

    @FXML
    private TableColumn<ApplicationInfo,Boolean> colInstallmentInfo;

    @FXML
    private TableColumn<ApplicationInfo,Integer> colId;

    @FXML
    private TableColumn<ApplicationInfo,String> colGender;

    @FXML private TableColumn<ApplicationInfo, Date> colCreatedDate;

    @FXML
    private TableColumn<ApplicationInfo,Boolean> colAction;

    @FXML private TextField sName;

    @FXML private ComboBox<String> sApplicationType;

    @FXML private TextField sContactNumber;

    @FXML private ComboBox<String> sGender;

    @FXML private ComboBox<Integer> sInstallment;

    @FXML private Button sButton;

    @FXML private TextField sReferenceNumber;

    @FXML
    private Pagination pagination;

    private int from=0,to=0;
    private static final int recordPerPage =10;


    @FXML public void searchApplication() {
        Integer count = service.countApplication(getSearchCriteria());
        System.out.println("--COUNT:"+count);
        pagination.setCurrentPageIndex(0);
        pagination.setPageCount((count/recordPerPage)+1);
        pagination.setPageFactory(this::createPage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.sApplicationType.setItems(FXCollections.observableArrayList(Utils.getApplicationTypes(true)));
        this.sGender.setItems(FXCollections.observableArrayList(Utils.getGender(true)));
        this.sInstallment.setItems(FXCollections.observableArrayList(Utils.getInstallmentPaid(true)));
        this.sContactNumber.setTextFormatter(new TextFormatter<>(Utils.getDigitFilter()));
        Utils.addTextLimiter(this.sContactNumber,11);
        colName.setCellValueFactory(new PropertyValueFactory<>("nameEn"));
        colReferenceNumber.setCellValueFactory(new PropertyValueFactory<>("referenceNumber"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colType.setCellValueFactory(new PropertyValueFactory<>("applicationType"));
        colInstallmentInfo.setCellFactory(installmentPaid);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colCreatedDate.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        colAction.setCellFactory(cellFactory);

        Integer count = service.countApplication(getSearchCriteria());
        pagination.setPageCount((count/recordPerPage)+1);
        pagination.setPageFactory(this::createPage);

        sButton.setOnAction(e->{
            searchApplication();
        });
    }

    Callback<TableColumn<ApplicationInfo, Boolean>, TableCell<ApplicationInfo, Boolean>> cellFactory =
            new Callback<TableColumn<ApplicationInfo, Boolean>, TableCell<ApplicationInfo, Boolean>>() {
                @Override
                public TableCell<ApplicationInfo, Boolean> call( final TableColumn<ApplicationInfo, Boolean> param) {
                    final TableCell<ApplicationInfo, Boolean> cell = new TableCell<ApplicationInfo, Boolean>() {
                        Image imgDtl = new Image(getClass().getResourceAsStream("/images/details.png"));
                        Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
                        final Button btnDetail = new Button();
                        final Button btnEdit = new Button();

                        @Override
                        public void updateItem(Boolean check, boolean empty) {
                            super.updateItem(check, empty);
                            if(empty) {
                                setGraphic(null);
                                setText(null);
                            } else {

                                btnDetail.setOnAction(e->{
                                    ApplicationInfo applicationInfo = getTableView().getItems().get(getIndex());
                                    detailsApplication(applicationInfo);
                                });

                                btnEdit.setOnAction(e->{
                                    ApplicationInfo applicationInfo = getTableView().getItems().get(getIndex());
                                    updateApplication(applicationInfo);
                                });

                                btnDetail.setStyle("-fx-background-color: transparent;");
                                ImageView iv = new ImageView();
                                iv.setImage(imgDtl);
                                iv.setPreserveRatio(true);
                                iv.setSmooth(true);
                                iv.setCache(true);
                                btnDetail.setGraphic(iv);

                                btnEdit.setStyle("-fx-background-color: transparent;");
                                iv = new ImageView();
                                iv.setImage(imgEdit);
                                iv.setPreserveRatio(true);
                                iv.setSmooth(true);
                                iv.setCache(true);
                                btnEdit.setGraphic(iv);

                                HBox box = new HBox();
                                box.getChildren().add(btnDetail);
                                box.getChildren().add(btnEdit);

                                setGraphic(box);
                                setAlignment(Pos.CENTER);
                                setText(null);
                            }
                        }

                        private void detailsApplication(ApplicationInfo applicationInfo) {
                            Defs.APPLICATION_ID = applicationInfo.getId();
                            stageManager.switchScene(FxmlView.APPLICATION_DETAILS);
                        }

                        private void updateApplication(ApplicationInfo applicationInfo) {
                            Defs.APPLICATION_ID = applicationInfo.getId();
                            stageManager.switchScene(FxmlView.MODIFY_APPLICATION);
                        }
                    };
                return cell;
            }
    };

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


    private Node createPage(int pageIndex) {
        from = pageIndex*recordPerPage;
        to = from + recordPerPage;
        ApplicationSearchCriteria criteria = getSearchCriteria();
        criteria.setStartIndex(from);
        criteria.setLimit(to);
        table.setItems(FXCollections.observableArrayList(service.searchApplication(criteria)));
        return table;
    }

    private ApplicationSearchCriteria getSearchCriteria() {
        ApplicationSearchCriteria criteria = new ApplicationSearchCriteria();
        if(!Utils.isEmpty(sApplicationType.getSelectionModel().getSelectedItem()) && !Defs.ALL.equalsIgnoreCase(sApplicationType.getSelectionModel().getSelectedItem())) {
            criteria.setApplicationType(sApplicationType.getSelectionModel().getSelectedItem());
        }
        if(!Utils.isEmpty(sName.getText())) {
            criteria.setName(sName.getText().trim());
        }
        if(!Utils.isEmpty(sGender.getSelectionModel().getSelectedItem()) && !Defs.ALL.equalsIgnoreCase(sGender.getSelectionModel().getSelectedItem())) {
            criteria.setGender(sGender.getSelectionModel().getSelectedItem());
        }
        if(!Utils.isEmpty(sContactNumber.getText())) {
            criteria.setContactNumber(sContactNumber.getText().trim());
        }
        if(!Utils.isNull(sInstallment.getSelectionModel().getSelectedItem())) {
            criteria.setInstallment(sInstallment.getSelectionModel().getSelectedItem()+"");
        }

        if(!Utils.isEmpty(sReferenceNumber.getText())) {
            criteria.setReferenceNumber(sReferenceNumber.getText().trim());
        }

        criteria.setStartIndex(0);
        criteria.setLimit(10);

        return criteria;
    }
}
