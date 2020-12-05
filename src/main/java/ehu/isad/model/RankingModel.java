package ehu.isad.model;

import javafx.scene.image.Image;

public class RankingModel {
    private final Image bandera;
    private final String herrialdea;
    private final int puntuak;

    public RankingModel(String pImagePath, String pHerrialdea, int pPuntuak) {
        bandera = new Image(pImagePath);
        herrialdea = pHerrialdea;
        puntuak = pPuntuak;
    }

    public Image getBandera() {
        return bandera;
    }

    public String getHerrialdea() {
        return herrialdea;
    }

    public int getPuntuak() {
        return puntuak;
    }
}