package ehu.isad.controller.ui;

import ehu.isad.Eurobisioa;
import ehu.isad.controller.db.EurobisioDBKud;
import ehu.isad.model.RankingModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RankingKud implements Initializable {

    @FXML
    private ImageView imgHerrialde1;

    @FXML
    private Label lblHerrialde1;

    @FXML
    private ImageView imgHerrialde2;

    @FXML
    private Label lblHerrialde2;

    @FXML
    private ImageView imgHerrialde3;

    @FXML
    private Label lblHerrialde3;

    @FXML
    private Button btnAdos;

    private Eurobisioa mainApp;

    public void setMainApp(Eurobisioa main) {
        mainApp = main;
    }

    @FXML
    void onClick() throws Exception {
        btnAdos.setDisable(true);
        mainApp.aukeratuErakutsi();
    }

    public void initialize(URL location, ResourceBundle resources) {
        this.rankingSortu();
    }

    private void rankingSortu() {
        ArrayList<RankingModel> rankingModels = EurobisioDBKud.getEurobisioDBKud().getRankingModel();
        imgHerrialde1.setImage(rankingModels.get(0).getBandera());
        lblHerrialde1.setText(rankingModels.get(0).getHerrialdea() + " - " + rankingModels.get(0).getPuntuak() + " puntu");
        imgHerrialde2.setImage(rankingModels.get(1).getBandera());
        lblHerrialde2.setText(rankingModels.get(1).getHerrialdea() + " - " + rankingModels.get(1).getPuntuak() + " puntu");
        imgHerrialde3.setImage(rankingModels.get(2).getBandera());
        lblHerrialde3.setText(rankingModels.get(2).getHerrialdea() + " - " + rankingModels.get(2).getPuntuak() + " puntu");
    }
}