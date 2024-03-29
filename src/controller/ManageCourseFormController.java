package controller;

import bo.BOFactory;
import bo.custom.impl.ProgrammeBOImpl;
import com.jfoenix.controls.JFXTextField;
import dto.ProgrammeDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.Validation;
import view.tm.ProgrammeTM;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ManageCourseFormController {
    public AnchorPane cmContext;
    public Label lblDate;
    public Label lblTime;
    public JFXTextField txtProgrammeID;
    public JFXTextField txtProgramme;
    public JFXTextField txtDuration;
    public JFXTextField txtFee;
    public TableView<ProgrammeTM> tblProgramme;
    public TableColumn colProgrammeID;
    public TableColumn colProgrammeName;
    public TableColumn colDuration;
    public TableColumn colFee;
    public JFXTextField txtSearch;
    public Button btnAddContext;



    ProgrammeBOImpl programmeBO = (ProgrammeBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.PROGRAMME);

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern courserIdPattern = Pattern.compile("^(C)[-]?[0-9]{3}$");
    Pattern courserNamePattern = Pattern.compile("^[A-z ]{1,30}$");
    Pattern courserDurationPattern = Pattern.compile("^[A-z 0-9 ]{1,10}$");
    Pattern courserFeePattern = Pattern.compile("^[0-9 ]{1,30}$");

    public void initialize() {
        loadDateAndTime();
        showProgrammesOnTable();
        storeValidations();
    }


    private void storeValidations() {
        map.put(txtProgrammeID,courserIdPattern);
        map.put(txtProgramme,courserNamePattern);
        map.put(txtDuration,courserDurationPattern);
        map.put(txtFee,courserFeePattern);
    }


    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() + " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }



    public void btnBackToHomeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) cmContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
        window.setResizable(false);
        System.out.println();
    }

    public void txtSearch(KeyEvent keyEvent) {
        ObservableList<ProgrammeTM> search = programmeBO.search(txtSearch.getText());
        tblProgramme.setItems(search);
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        ProgrammeTM selectedItem = tblProgramme.getSelectionModel().getSelectedItem();
        String programmeID = selectedItem.getProgrammeID();

        if (programmeBO.delete(programmeID)) {
            showProgrammesOnTable();
            new Alert(Alert.AlertType.CONFIRMATION, "DELETED successfully").show();
            clearText();

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    private void clearText() {
        txtProgrammeID.clear();
        txtProgramme.clear();
        txtDuration.clear();
        txtFee.clear();
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        ProgrammeDTO programme = new ProgrammeDTO(
                txtProgrammeID.getText(),
                txtProgramme.getText(),
                txtDuration.getText(),
                Double.parseDouble(txtFee.getText())
        );

        if (programmeBO.add(programme)) {
            showProgrammesOnTable();
            clearText();
            new Alert(Alert.AlertType.CONFIRMATION, "Course Add Successfully").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something Went Wrong").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
       /* ProgrammeTM selectedItem = tblProgramme.getSelectionModel().getSelectedItem();
        String programmeID = selectedItem.getProgrammeID();*/

        ProgrammeDTO program = new ProgrammeDTO(
                txtProgrammeID.getText(),
                txtProgramme.getText(),
                txtDuration.getText(),
                Double.parseDouble(txtFee.getText())
        );
        if (programmeBO.update(program)) {
            showProgrammesOnTable();
            clearText();
            new Alert(Alert.AlertType.CONFIRMATION, "Program Updated Successfully").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void btnClear(ActionEvent actionEvent) {
        txtProgrammeID.clear();
        txtProgramme.clear();
        txtDuration.clear();
        txtFee.clear();
    }

    public void showProgrammesOnTable() {
        ObservableList<ProgrammeTM> list = programmeBO.find();
        colProgrammeID.setCellValueFactory(new PropertyValueFactory<>("programmeID"));
        colProgrammeName.setCellValueFactory(new PropertyValueFactory<>("programmeName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        tblProgramme.setItems(list);

    }

    public void tblOnMouseClicked(MouseEvent mouseEvent) {
        try {
            ProgrammeTM selectRow = tblProgramme.getSelectionModel().getSelectedItem();
            txtProgrammeID.setText(selectRow.getProgrammeID());
            txtProgramme.setText(selectRow.getProgrammeName());
            txtDuration.setText(selectRow.getDuration());
            txtFee.setText(String.valueOf(selectRow.getFee()));
        } catch (Exception e) {

        }
    }


    public void txtCourseKeyRelease(KeyEvent keyEvent) {
        btnAddContext.setDisable(true);
        Object response = Validation.validate(map,btnAddContext,"Green");
        if (keyEvent.getCode()== KeyCode.ENTER) {
            if (response instanceof TextField){
                TextField error  = (TextField) response;
                error.requestFocus();
            }else if (response instanceof Boolean){
                new Alert(Alert.AlertType.CONFIRMATION, "Done").show();
            }
        }
    }
}
