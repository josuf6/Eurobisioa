package ehu.isad.controller.ui;

import ehu.isad.Eurobisioa;
import ehu.isad.controller.db.EurobisioDBKud;
import ehu.isad.model.Herrialdea;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AukeratuKud implements Initializable {

    private Eurobisioa mainApp;

    @FXML
    private ComboBox<Herrialdea> comboHerrialdeak;

    @FXML
    private Button btnAdos;

    public void setMainApp(Eurobisioa main) {
        mainApp = main;
    }

    @FXML
    void onClick() throws Exception {
        btnAdos.setDisable(true);
        Herrialdea herrialdea = comboHerrialdeak.getValue();
        if (EurobisioDBKud.getEurobisioDBKud().bozkatuDu(herrialdea.getIzena())) {
            mainApp.erroreaErakutsi(herrialdea.getIzena());
        }
        else mainApp.bozkPanelaErakutsi(herrialdea);
    }

    public void initialize(URL location, ResourceBundle resources) {
        this.herrialdeakSartu();
        comboHerrialdeak.setOnAction(event -> {
            if (btnAdos.isDisabled()) {
                btnAdos.setDisable(false);
            }
        });
    }

    private void herrialdeakSartu() {
        ObservableList<Herrialdea> herrialdeList = EurobisioDBKud.getEurobisioDBKud().getHerrialdeak();
        comboHerrialdeak.setItems(herrialdeList);
    }
}