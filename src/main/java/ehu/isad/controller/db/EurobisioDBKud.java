package ehu.isad.controller.db;

import ehu.isad.model.BozkatuModel;
import ehu.isad.model.Herrialdea;
import ehu.isad.model.RankingModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EurobisioDBKud {

    private static final EurobisioDBKud nEurobisioDBKud = new EurobisioDBKud();

    public static EurobisioDBKud getEurobisioDBKud() {
        return nEurobisioDBKud;
    }

    private EurobisioDBKud() {}

    public ObservableList<Herrialdea> getHerrialdeak() {
        ObservableList<Herrialdea> herrialdeList = FXCollections.observableArrayList();
        String query = "select izena,bandera from Herrialde";
        ResultSet rs = DBKud.getDBKud().execSQL(query);
        try {
            while (rs.next()) {
                herrialdeList.add(new Herrialdea(rs.getString("izena"), rs.getString("bandera")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return herrialdeList;
    }

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

    public ArrayList<BozkatuModel> getBozkatuModel() {
        ArrayList<BozkatuModel> bozkatuModels = new ArrayList<>();
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
                bozkatuModels.add(new BozkatuModel(imagePath, herrialde, artista, abestia));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bozkatuModels;
    }

    public ArrayList<RankingModel> getRankingModel() {
        ArrayList<RankingModel> rankingModels = new ArrayList<>();
        String query = "select h.bandera, h.izena, o.puntuak " +
                "from Herrialde h, Ordezkaritza o " +
                "where h.izena=o.herrialdea " +
                "and o.urtea=(select strftime('%Y','now')) " +
                "order by o.puntuak desc " +
                "limit 3";
        ResultSet rs = DBKud.getDBKud().execSQL(query);
        try {
            while (rs.next()) {
                String imagePath = "banderak/" + rs.getString("bandera") + ".png";
                String herrialde = rs.getString("izena");
                int puntuak = rs.getInt("puntuak");
                rankingModels.add(new RankingModel(imagePath, herrialde, puntuak));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rankingModels;
    }

    public void bozkaketaGorde(String herrialdea, String herrialdeBozkatu, Integer puntuak) {
        String query = "insert into Bozkaketa " +
                "values('" + herrialdea + "','" + herrialdeBozkatu + "',(select strftime('%Y','now'))," + puntuak + ")";
        DBKud.getDBKud().execSQL(query);
    }

    public void ordezkaritzaEguneratu(String herrialdea, Integer puntuak) {
        String query = "update Ordezkaritza set puntuak=puntuak+" + puntuak + " " +
                "where herrialdea='" + herrialdea + "'";
        DBKud.getDBKud().execSQL(query);
    }
}