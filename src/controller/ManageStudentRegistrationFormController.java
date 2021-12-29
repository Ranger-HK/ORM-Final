package controller;

import bo.BOFactory;
import bo.custom.impl.ProgrammeBOImpl;
import bo.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAOFactory;
import dao.custom.impl.ProgrammeDAOImpl;
import dto.ProgrammeDTO;
import dto.StudentDTO;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class ManageStudentRegistrationFormController {
    private final ProgrammeDAOImpl programDAO = (ProgrammeDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROGRAMME);
    public AnchorPane srContext;
    public Label lblDate;
    public Label lblTime;
    public TableView<StudentTM>tblRegister;
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
    public JFXComboBox<String> cmbProgrammeID01;
    public JFXTextField txtProgramme01;
    public JFXTextField txtFee01;
    public JFXTextField cmbDuration01;
    public JFXComboBox<String> cmbProgrammeID02;
    public JFXTextField txtProgramme02;
    public JFXTextField txtFee02;
    public JFXTextField cmbDuration02;
    public JFXComboBox<String> cmbProgrammeID03;
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
    ProgrammeBOImpl programmeBO = (ProgrammeBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.PROGRAMME);

    public void initialize() {
        loadDateAndTime();
        loadProgramId();
        showStudentsOnTable();
        cmbProgrammeID01.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setProgramData(txtProgramme01, cmbDuration01, txtFee01, newValue);
        });
        cmbProgrammeID02.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setProgramData(txtProgramme02, cmbDuration02, txtFee02, newValue);
        });
        cmbProgrammeID03.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setProgramData(txtProgramme03, cmbDuration03, txtFee03, newValue);
        });


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
        Stage window = (Stage) srContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
        window.setResizable(false);
    }

    public void txtSearch(KeyEvent keyEvent) {
        ObservableList<StudentTM> search = studentBO.search(txtSearchId.getText());
        tblRegister.setItems(search);
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        StudentTM selectedItem = tblRegister.getSelectionModel().getSelectedItem();
        String studentId = selectedItem.getRegNumber();
        if (studentBO.delete(studentId)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            showStudentsOnTable();
            clearTexts();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
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
        if (studentBO.add(studentDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Programme Added").show();
            showStudentsOnTable();
            clearTexts();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }

    private void showStudentsOnTable() {
        ObservableList<StudentTM> list = studentBO.find();
        colRegNo.setCellValueFactory(new PropertyValueFactory<>("regNumber"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        tblRegister.setItems(list);
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
        StudentTM selectedItem = tblRegister.getSelectionModel().getSelectedItem();
        String studentId = selectedItem.getRegNumber();
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
        if (studentBO.update(studentDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Program Updated").show();
            showStudentsOnTable();
            clearTexts();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearTexts();
    }

    private void clearTexts() {
        txtName.clear();
        txtEmail.clear();
        txtNic.clear();
        txtDob.clear();
        txtRegNo.clear();
        txtAddress.clear();
        txtContactNumber.clear();
        txtAge.clear();
    }

    public void tblOnClicked(MouseEvent mouseEvent) {
        try {

            StudentTM selectedStudents = tblRegister.getSelectionModel().getSelectedItem();
            txtRegNo.setText(selectedStudents.getRegNumber());
            txtName.setText(selectedStudents.getName());
            txtAge.setText("" + selectedStudents.getAge());
            txtContactNumber.setText(selectedStudents.getContactNumber());
            txtAddress.setText(selectedStudents.getAddress());
            txtDob.setText(selectedStudents.getDob());
            txtEmail.setText(selectedStudents.getEmail());
            txtNic.setText(selectedStudents.getNic());
        } catch (Exception e) {
        }
    }

    private void loadProgramId() {
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

    private void setProgramData(JFXTextField enterProgram, JFXTextField enterDuration, JFXTextField enterFee, String ProgramID) {
        ProgrammeDTO programDetails = programmeBO.getProgramDetails(ProgramID);

        if (programDetails == null) {
        } else {
            enterProgram.setText(programDetails.getProgrammeName());
            enterDuration.setText(programDetails.getDuration());
            enterFee.setText(programDetails.getFee() + "");
        }
    }

}
