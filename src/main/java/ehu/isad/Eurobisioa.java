package ehu.isad;

import ehu.isad.controller.ui.*;
import ehu.isad.model.Herrialdea;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Eurobisioa extends Application {

    private Parent hasieraUI;
    private Parent aukeratuUI;
    private Parent erroreaUI;
    private Parent bozkPanelaUI;
    private Parent rankingUI;

    private Stage stage;

    private HasieraKud hasieraKud;
    private AukeratuKud aukeratuKud;
    private ErroreaKud erroreaKud;
    private BozkPanelaKud bozkPanelaKud;
    private RankingKud rankingKud;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setResizable(false);
        this.hasieraErakutsi();
        stage.setTitle("Eurobisioa");
    }

    public void hasieraErakutsi() throws Exception {
        this.hasieraPantailaKargatu();
        this.sceneEgokitu(hasieraUI, 250, 100);
        stage.show();
    }

    public void aukeratuErakutsi() throws Exception {
        this.aukeratuPantailaKargatu();
        this.sceneEgokitu(aukeratuUI, 250, 150);
        stage.show();
    }

    public void erroreaErakutsi(String pHerrialdeIzen) throws Exception {
        this.errorePantailaKargatu();
        erroreaKud.setTestua(pHerrialdeIzen);
        this.sceneEgokitu(erroreaUI, 250, 100);
        stage.show();
    }

    public void bozkPanelaErakutsi(Herrialdea pHerrialdea) throws Exception {
        this.bozkPanelaPantailaKargatu();
        bozkPanelaKud.setHerrialdeBozkatu(pHerrialdea.getIzena());
        bozkPanelaKud.setTestua();
        this.sceneEgokitu(bozkPanelaUI, 600, 400);
        stage.show();
    }

    public void rankingErakutsi() throws IOException {
        this.rankingPantailaKargatu();
        this.sceneEgokitu(rankingUI, 250, 150);
        stage.show();
    }

    private void sceneEgokitu(Parent inerfazea, double zabalera, double altuera) {
        stage.setScene(new Scene(inerfazea, zabalera, altuera));
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - zabalera) / 2);
        stage.setY((primScreenBounds.getHeight() - altuera) / 3);
    }

    private void hasieraPantailaKargatu() throws IOException {
        FXMLLoader loaderHasiera = new FXMLLoader(getClass().getResource("/Hasiera.fxml"));
        hasieraUI = (Parent) loaderHasiera.load();
        hasieraKud = loaderHasiera.getController();
        hasieraKud.setMainApp(this);
    }

    private void aukeratuPantailaKargatu() throws IOException {
        FXMLLoader loaderAukeratu = new FXMLLoader(getClass().getResource("/Aukeratu.fxml"));
        aukeratuUI = (Parent) loaderAukeratu.load();
        aukeratuKud = loaderAukeratu.getController();
        aukeratuKud.setMainApp(this);
    }

    private void errorePantailaKargatu() throws IOException {
        FXMLLoader loaderErrorea = new FXMLLoader(getClass().getResource("/Errorea.fxml"));
        erroreaUI = (Parent) loaderErrorea.load();
        erroreaKud = loaderErrorea.getController();
        erroreaKud.setMainApp(this);
    }

    private void bozkPanelaPantailaKargatu() throws IOException {
        FXMLLoader loaderBozkPanela = new FXMLLoader(getClass().getResource("/BozkPanela.fxml"));
        bozkPanelaUI = (Parent) loaderBozkPanela.load();
        bozkPanelaKud = loaderBozkPanela.getController();
        bozkPanelaKud.setMainApp(this);
    }

    private void rankingPantailaKargatu() throws IOException {
        FXMLLoader loaderRanking = new FXMLLoader(getClass().getResource("/Ranking.fxml"));
        rankingUI = (Parent) loaderRanking.load();
        rankingKud = loaderRanking.getController();
        rankingKud.setMainApp(this);
    }
}