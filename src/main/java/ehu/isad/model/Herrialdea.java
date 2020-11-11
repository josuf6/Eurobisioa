package ehu.isad.model;

public class Herrialdea {
    private final String izena;
    private final String bandera;

    public Herrialdea(String pIzena, String pBandera) {
        izena = pIzena;
        bandera = pBandera;
    }

    public String getIzena() {
        return izena;
    }

    public String getBandera() {
        return bandera;
    }

    @Override
    public String toString() {
        return izena;
    }
}
