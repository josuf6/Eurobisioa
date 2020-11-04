package ehu.isad.controller.db;

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
}
