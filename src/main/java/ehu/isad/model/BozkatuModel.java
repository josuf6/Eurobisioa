package ehu.isad.model;

import javafx.scene.image.Image;

public class BozkatuModel {

    private final Image bandera;
    private final String herrialdea;
    private final String artista;
    private final String abestia;
    private Integer puntuak;

    public BozkatuModel(String pImagePath, String pHerrialdea, String pArtista, String pAbestia) {
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