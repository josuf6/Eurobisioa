package ehu.isad.controller.db;

import ehu.isad.model.HerrialdeModel;
import ehu.isad.model.Herrialdea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BozkatuKud {

    private static final BozkatuKud nBozkatuKud = new BozkatuKud();

    public static BozkatuKud getBozkatuKud() {
        return nBozkatuKud;
    }

    private BozkatuKud() {}

    public boolean bozkatuDu(String pHerrialdeIzen) {
        String query = "select izena from Bozkaketa b, Herrialde h where izena='" + pHerrialdeIzen + "' and urtea=(select strftime('%Y','now')) and bozkatuDu=izena";
        ResultSet rs = DBKud.getDBKud().execSQL(query);
        try {
            if (rs.next()) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ObservableList<HerrialdeModel> getHerrialdeModel() {
        ObservableList<HerrialdeModel> herrialdeModels = FXCollections.observableArrayList();
        String query = "select h.bandera, h.izena, o.artista, o.abestia " +
                "from Herrialde h, Ordezkaritza o " +
                "where h.izena=o.herrialdea " +
                "and o.urtea=(select strftime('%Y','now')) " +
                "order by h.izena asc";
        ResultSet rs = DBKud.getDBKud().execSQL(query);
        try {
            while (rs.next()) {
                String imagePath = "banderak/" + rs.getString("bandera") + ".png";
                String herrialde = rs.getString("izena");
                String artista = rs.getString("artista");
                String abestia = rs.getString("abestia");
                herrialdeModels.add(new HerrialdeModel(imagePath, herrialde, artista, abestia));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return herrialdeModels;
    }
}
