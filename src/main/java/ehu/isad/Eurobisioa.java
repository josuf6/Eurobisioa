package ehu.isad;

import ehu.isad.controller.ui.AukeratuKud;
import ehu.isad.controller.ui.BozkPanelaKud;
import ehu.isad.controller.ui.ErroreaKud;
import ehu.isad.controller.ui.HasieraKud;
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

    private Stage stage;

    private HasieraKud hasieraKud;
    private AukeratuKud aukeratuKud;
    private ErroreaKud erroreaKud;
    private BozkPanelaKud bozkPanelaKud;

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
        stage.setScene(new Scene(hasieraUI, 250, 100));
        stage.show();
    }

    public void aukeratuErakutsi() throws Exception {
        this.aukeratuPantailaKargatu();
        stage.setScene(new Scene(aukeratuUI, 250, 150));
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setY((primScreenBounds.getHeight() - 150) / 3);
        stage.show();
    }

    public void erroreaErakutsi(String pHerrialdeIzen) throws Exception {
        this.errorePantailaKargatu();
        erroreaKud.setTestua(pHerrialdeIzen);
        stage.setScene(new Scene(erroreaUI, 250, 100));
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setY((primScreenBounds.getHeight() - 100) / 3);
        stage.show();
    }

    public void bozkPanelaErakutsi(Herrialdea pHerrialdea) throws Exception {
        this.bozkPanelaPantailaKargatu(pHerrialdea);
        bozkPanelaKud.setHerrialdeBozkatu(pHerrialdea.getIzena());
        bozkPanelaKud.setTestua();
        stage.setScene(new Scene(bozkPanelaUI, 600, 400));
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - 600) / 2);
        stage.setY((primScreenBounds.getHeight() - 400) /3);
        stage.show();
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

    private void bozkPanelaPantailaKargatu(Herrialdea pHerrialdea) throws IOException {
        FXMLLoader loaderBozkPanela = new FXMLLoader(getClass().getResource("/BozkPanela.fxml"));
        bozkPanelaUI = (Parent) loaderBozkPanela.load();
        bozkPanelaKud = loaderBozkPanela.getController();
        bozkPanelaKud.setMainApp(this);
    }
}
