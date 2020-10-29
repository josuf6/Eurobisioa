package ehu.isad.controller.ui;

import ehu.isad.Eurobisioa;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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
    void onClick() throws Exception {
        btnBozkatu.setDisable(true);
        mainApp.aukeratuErakutsi();
    }

    public void initialize(URL location, ResourceBundle resources) {}
}
