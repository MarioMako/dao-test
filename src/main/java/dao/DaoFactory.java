package dao;

import db.DB;
import query.VendedorQuery;

public class DaoFactory {
    public static VendedorDAO createVendedorDAO(){
        return new VendedorQuery(DB.getConn());
    }
}
