package ehu.isad.controller.db;

import ehu.isad.model.Herrialdea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HerrialdeKud {

    private static final HerrialdeKud nHerrialdeKud = new HerrialdeKud();

    public static HerrialdeKud getHerrialdeKud() {
        return nHerrialdeKud;
    }

    private HerrialdeKud() {}

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
}
