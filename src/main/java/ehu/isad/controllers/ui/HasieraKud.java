package ehu.isad.controllers.ui;

import ehu.isad.Eurobisioa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class HasieraKud implements Initializable {

    private Eurobisioa mainApp;

    @FXML
    private Button btnBozkatu;

    public void setMainApp(Eurobisioa main) {
        mainApp = main;
    }

    @FXML
    void onClick(ActionEvent event) {
        btnBozkatu.setDisable(true);
        mainApp.aukeratuErakutsi();
    }

    public void initialize(URL location, ResourceBundle resources) {}
}
