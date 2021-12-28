package controller;

import bo.BOFactory;
import bo.custom.ProgrammeBO;
import bo.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAOFactory;
import dao.custom.impl.ProgrammeDAOImpl;
import dto.StudentDTO;
import entity.Programme;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class ManageStudentRegistrationFormController {
    public AnchorPane srContext;
    public Label lblDate;
    public Label lblTime;
    public TableView tblRegister;
    public TableColumn colRegNo;
    public TableColumn colName;
    public TableColumn colAge;
    public TableColumn colNic;
    public TableColumn colContactNumber;
    public TableColumn colAddress;
    public TableColumn colDob;
    public TableColumn colEmail;
    public TableColumn colGender;
    public JFXTextField txtRegNo;
    public JFXTextField txtName;
    public JFXTextField txtDob;
    public JFXTextField txtContactNumber;
    public JFXTextField txtAddress;
    public JFXTextField txtAge;
    public JFXTextField txtEmail;
    public JFXComboBox cmbProgrammeID01;
    public JFXTextField txtProgramme01;
    public JFXTextField txtFee01;
    public JFXTextField cmbDuration01;
    public JFXComboBox cmbProgrammeID02;
    public JFXTextField txtProgramme02;
    public JFXTextField txtFee02;
    public JFXTextField cmbDuration02;
    public JFXComboBox cmbProgrammeID03;
    public JFXTextField txtProgramme03;
    public JFXTextField txtFee03;
    public JFXTextField cmbDuration03;
    public JFXTextField txtNic;
    public RadioButton rdMale;
    public RadioButton rdFemale;
    public ToggleGroup gender;
    public JFXCheckBox cb1;
    public JFXCheckBox cb2;
    public JFXCheckBox cb3;
    public JFXTextField txtSearchId;



    StudentBOImpl studentBO = (StudentBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.STUDENT);
    private final ProgrammeDAOImpl programDAO = (ProgrammeDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROGRAMME);


    public void initialize(){
        loadDateAndTime();
        loadProgramId();
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour()+ " : "+currentTime.getMinute()+ " : "+currentTime.getSecond()
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
        Stage window = (Stage) srContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
        window.setResizable(false);
    }

    public void txtSearch(KeyEvent keyEvent) {
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
     StudentDTO studentDTO = new StudentDTO(
                txtRegNo.getText(),
                txtName.getText(),
                Integer.parseInt(txtAge.getText()),
                txtContactNumber.getText(),
                txtAddress.getText(),
                txtDob.getText(),
                txtEmail.getText(),
                txtNic.getText(),
                SelectGender()

        );
        if (studentBO.add(studentDTO)){
            new Alert(Alert.AlertType.CONFIRMATION,"Programme Added").show();
        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }

    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }

    public void tblOnClicked(MouseEvent mouseEvent) {
    }

    private void loadProgramId(){
        List<String> allProgramIds = programDAO.getAllProgramIds();
        cmbProgrammeID01.getItems().addAll(allProgramIds);
        cmbProgrammeID02.getItems().addAll(allProgramIds);
        cmbProgrammeID03.getItems().addAll(allProgramIds);
    }
    public String SelectGender() {
        String selectGender = null;
        if (rdMale.isSelected()) {
            selectGender = "Male";
        } else if (rdFemale.isSelected()) {
            selectGender = "Female";
        }
        return selectGender;
    }
}
