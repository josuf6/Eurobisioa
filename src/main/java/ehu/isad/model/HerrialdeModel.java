package ehu.isad.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HerrialdeModel {

    private Image bandera;
    private String herrialdea;
    private String artista;
    private String abestia;
    private Integer puntuak;

    private String imagePath;

    public HerrialdeModel(String pImagePath, String pHerrialdea, String pArtista, String pAbestia) {
        this.bandera = new Image(pImagePath);
        this.herrialdea = pHerrialdea;
        this.artista = pArtista;
        this.abestia = pAbestia;
        this.puntuak = 0;
    }

    public Image getBandera() {
        return bandera;
    }

    public String getHerrialdea() {
        return herrialdea;
    }

    public String getArtista() {
        return artista;
    }

    public String getAbestia() {
        return abestia;
    }

    public Integer getPuntuak() {
        return puntuak;
    }

    public void setPuntuak(Integer puntuak) {
        this.puntuak = puntuak;
    }
}
