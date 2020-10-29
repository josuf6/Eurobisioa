package ehu.isad.controller.ui;

import ehu.isad.Eurobisioa;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ErroreaKud implements Initializable {

    private Eurobisioa mainApp;

    @FXML
    private Label lblTestua;

    @FXML
    private Button btnAdos;

    public void setMainApp(Eurobisioa main) {
        mainApp = main;
    }

    @FXML
    void onClick() throws Exception {
        btnAdos.setDisable(true);
        mainApp.aukeratuErakutsi();
    }

    public void initialize(URL location, ResourceBundle resources) {}

    public void setTestua(String pHerrialdeIzen) {
        lblTestua.setText(pHerrialdeIzen + lblTestua.getText());
    }
}
