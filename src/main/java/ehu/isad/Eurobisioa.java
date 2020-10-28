package ehu.isad;

import ehu.isad.controllers.ui.AukeratuKud;
import ehu.isad.controllers.ui.HasieraKud;
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

    private Stage stage;

    private HasieraKud hasieraKud;
    private AukeratuKud aukeratuKud;

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
        this.pantailakKargatu();
        stage.setScene(new Scene(hasieraUI, 250, 100));
        stage.show();
    }

    private void pantailakKargatu() throws IOException {
        FXMLLoader loaderHasiera = new FXMLLoader(getClass().getResource("/Hasiera.fxml"));
        hasieraUI = (Parent) loaderHasiera.load();
        hasieraKud = loaderHasiera.getController();
        hasieraKud.setMainApp(this);

        FXMLLoader loaderAukeratu = new FXMLLoader(getClass().getResource("/Aukeratu.fxml"));
        aukeratuUI = (Parent) loaderAukeratu.load();
        aukeratuKud = loaderAukeratu.getController();
        aukeratuKud.setMainApp(this);
    }

    public void aukeratuErakutsi() {
        stage.setScene(new Scene(aukeratuUI, 600, 450));
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - 600) / 2);
        stage.setY((primScreenBounds.getHeight() - 450) / 2);
        stage.show();
    }
}
