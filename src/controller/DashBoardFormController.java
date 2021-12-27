package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashBoardFormController {


    public AnchorPane DashBoardContext;

    public void btnManageStudentDetailsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/RegistrationDetalsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) DashBoardContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
        window.setResizable(false);
    }

    public void btnManageRegistrationOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageStudentRegistrationForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) DashBoardContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
        window.setResizable(false);
    }

    public void btnManageCourseOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageCourseForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) DashBoardContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
        window.setResizable(false);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) DashBoardContext.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
        window.setResizable(false);
    }

}
