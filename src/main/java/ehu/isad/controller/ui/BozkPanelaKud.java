package ehu.isad.controller.ui;

import ehu.isad.Eurobisioa;
import ehu.isad.controller.db.EurobisioDBKud;
import ehu.isad.model.BozkatuModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BozkPanelaKud implements Initializable {

    @FXML
    private Label lblTestua;

    @FXML
    private TableView<BozkatuModel> tbBozkaketak;

    @FXML
    private TableColumn<BozkatuModel, Image> bandera;

    @FXML
    private TableColumn<BozkatuModel, String> herrialdea;

    @FXML
    private TableColumn<BozkatuModel, String> artista;

    @FXML
    private TableColumn<BozkatuModel, String> abestia;

    @FXML
    private TableColumn<BozkatuModel, Integer> puntuak;

    @FXML
    private Label lblInfo;

    @FXML
    private Button btnAdos;

    @FXML
    private Button btnAtzera;

    private Eurobisioa mainApp;
    private ArrayList<BozkatuModel> ordezkaritzak;
    private String herrialdeBozkatu;
    private int puntuFalta;

    public void setMainApp(Eurobisioa main) {
        mainApp = main;
    }

    @FXML
    void onClickAdos() throws Exception {
        btnAdos.setDisable(true);
        ArrayList<BozkatuModel> bozkaketak = this.getBozkaketak();
        for (BozkatuModel bozkaketa : bozkaketak) {
            EurobisioDBKud.getEurobisioDBKud().bozkaketaGorde(bozkaketa.getHerrialdea(), this.herrialdeBozkatu, bozkaketa.getPuntuak());
            EurobisioDBKud.getEurobisioDBKud().ordezkaritzaEguneratu(bozkaketa.getHerrialdea(), bozkaketa.getPuntuak());
        }
        mainApp.rankingErakutsi();
    }

    @FXML
    void onClickAtzera() throws Exception {
        btnAtzera.setDisable(true);
        mainApp.aukeratuErakutsi();
    }

    private ArrayList<BozkatuModel> getBozkaketak() {
        ArrayList<BozkatuModel> bozkaketak = new ArrayList<>();
        for (BozkatuModel bozkatuModel : this.ordezkaritzak) {
            if (bozkatuModel.getPuntuak() > 0) bozkaketak.add(bozkatuModel);
        }
        return bozkaketak;
    }

    public void initialize(URL location, ResourceBundle resources) {
        tbBozkaketak.setEditable(true);
        herrialdea.setCellValueFactory(new PropertyValueFactory<>("Herrialdea"));
        artista.setCellValueFactory(new PropertyValueFactory<>("Artista"));
        abestia.setCellValueFactory(new PropertyValueFactory<>("Abestia"));
        puntuak.setCellValueFactory(new PropertyValueFactory<>("Puntuak"));

        puntuak.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        puntuak.setOnEditCommit(
                t -> {
                    if (t.getNewValue() > t.getOldValue() && t.getOldValue() >= 0) {
                        puntuFalta = puntuFalta - (t.getNewValue() - t.getOldValue());
                    } else if (t.getNewValue() < t.getOldValue() && t.getNewValue() >= 0) {
                        puntuFalta = puntuFalta + (t.getOldValue() - t.getNewValue());
                    }
                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setPuntuak(t.getNewValue());
                    this.balioakKudeatu();
                }
        );

        this.bozkTaulaSortu();

        Callback<TableColumn<BozkatuModel, Integer>, TableCell<BozkatuModel, Integer>> defaultTextFieldCellFactory
                = TextFieldTableCell.<BozkatuModel, Integer>forTableColumn(new IntegerStringConverter());

        puntuak.setCellFactory(col -> {
            TableCell<BozkatuModel, Integer> cell = defaultTextFieldCellFactory.call(col);

            cell.setOnMouseClicked(event -> {
                if (! cell.isEmpty()) {
                    cell.setEditable(!cell.getTableView().getSelectionModel().getSelectedItem().getHerrialdea().equals(this.herrialdeBozkatu));
                }
            });

            return cell ;
        });

        bandera.setCellValueFactory(new PropertyValueFactory<>("Bandera"));

        bandera.setCellFactory(p -> new TableCell<>() {
            public void updateItem(Image image, boolean empty) {
                if (image != null && !empty){
                    final ImageView imageview = new ImageView();
                    imageview.setFitHeight(15);
                    imageview.setFitWidth(20);
                    imageview.setImage(image);
                    setGraphic(imageview);
                    setAlignment(Pos.CENTER);
                }else{
                    setGraphic(null);
                    setText(null);
                }
            }
        });

        this.puntuFalta = 5;
        this.balioakKudeatu();
    }

    private void balioakKudeatu() {
        if (negatiboDago()) {
            lblInfo.setText("Ezin dira balio negatiboak erabili");
            lblInfo.setTextFill(Color.RED);
        } else {
            if (puntuFalta == 0) {
                lblInfo.setText("");
                btnAdos.setDisable(false);
            } else {
                if (!btnAdos.isDisabled()) {
                    btnAdos.setDisable(true);
                }
                if (puntuFalta > 1) {
                    lblInfo.setText(puntuFalta + " puntu falta dira banatzeko");
                    lblInfo.setTextFill(Color.BLACK);
                } else if (puntuFalta == 1) {
                    lblInfo.setText("Puntu bat falta da banatzeko");
                    lblInfo.setTextFill(Color.BLACK);
                } else if (puntuFalta < 0) {
                    int zenbatSoberan = -puntuFalta;
                    lblInfo.setText("Ezin dira 5 baino puntu gehiago banatu. " + zenbatSoberan + " puntu daude soberan");
                    lblInfo.setTextFill(Color.RED);
                }
            }
        }
    }

    private boolean negatiboDago() {
        ObservableList<BozkatuModel> bozkatuModels = tbBozkaketak.getItems();
        for (BozkatuModel bozkatuModel : bozkatuModels) {
            if (bozkatuModel.getPuntuak() < 0) return true;
        }
        return false;
    }

    private void bozkTaulaSortu() {
        ordezkaritzak = EurobisioDBKud.getEurobisioDBKud().getBozkatuModel();
        ObservableList<BozkatuModel> ordezkaritzakObs = FXCollections.observableArrayList(ordezkaritzak);
        tbBozkaketak.setItems(ordezkaritzakObs);
    }

    public void setHerrialdeBozkatu(String herrialdeBozkatu) {
        this.herrialdeBozkatu = herrialdeBozkatu;
    }

    public void setTestua() {
        lblTestua.setText(this.herrialdeBozkatu + lblTestua.getText());
    }
}