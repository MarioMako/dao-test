package db;

import java.io.*;
import java.sql.*;
import java.util.*;
public class DB {

    private static Connection conn = null;
    private static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("src/main/properties/db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        }catch(IOException e){
            throw new DbException(e.getMessage());
        }
    }
    public static Connection getConn(){
        if(conn==null){
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            }catch(SQLException e){
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }
    public static void closeConn(){
        if(conn!=null){
            try{
                conn.close();
            }catch(SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }
    public static void closeStatement(Statement st){
        if(st != null){
            try{
                st.close();
            } catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }
    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            } catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

}

