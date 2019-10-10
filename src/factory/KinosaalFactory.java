package factory;

import db_connector.Connector;
import db_connector.QueryBuilder;
import helper.SupportMethods;
import oo.Kinosaal;
import oo.Sitz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KinosaalFactory {
    public static Kinosaal getKinosaal(int id) {

        Sitz[] Sitzplan;
        Connection c = Connector.getConnection();
        String sql = QueryBuilder.getSitzplanBySaalID(id);
        ResultSet rs = Connector.getQueryResult(c, sql);
        SupportMethods sup = new SupportMethods();

        if(rs != null) {
            int rsSize = sup.getResultSetSize(rs);
            Sitzplan = new Sitz[rsSize];
            for(int i =0; i<rsSize;i++) {
                try {
                    rs.next();
                    Sitzplan[i]=new Sitz(rs.getInt("SitzplatzID"),rs.getInt("Nummer"),rs.getString("Reihe").charAt(0),rs.getString("Sitzklasse").charAt(0),"", 0.0f); //TODO beschreibung und grundpreis verbinden.
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else{
            Sitzplan = new Sitz[1];
            Sitzplan[0] = new Sitz(1,2,'A','B',"test", 1.6f);
        }

        Kinosaal kinosaal = null;
        sql = QueryBuilder.getSaalById(id);
        rs = Connector.getQueryResult(c, sql);
        sup = null;
        sup = new SupportMethods();
        if(rs != null) {
            int rsSize = sup.getResultSetSize(rs);
            if(rsSize > 0) {
                try {
                    rs.next();
                    kinosaal = new Kinosaal(id, rs.getString("Saalbezeichnung"), Sitzplan);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        Connector.closeConnection(c);
        return kinosaal;
    }
}
