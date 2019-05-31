package xyz.dommi.db;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class DBManager {
    protected Connection con;
    protected String tablename;

    public DBManager(Connection con, String tablename) {
        this.con = con;
        this.tablename = tablename;
        init();
    }

    protected abstract void init();

    protected void insert(String[] keys,String[] values) {

        try {
            Statement statement = con.createStatement();
            String keyStr = "";
            for (int i = 0; i < keys.length; i++) {
                keyStr += "`"+keys[i]+"`" + ((i < keys.length-1)? "," : "");
            }
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr += "'"+values[i]+"'" + ((i < values.length-1)? "," : "");
            }
            statement.executeUpdate("INSERT INTO `db`.`" + tablename + "`("+keyStr+") VALUES (" + valueStr + ");");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    protected void update(String key, String keyValue,String updateKey, String updateValue){
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("update " + tablename + " set `"+updateKey+"`='"+updateValue+"' where `"+key+"`='" + keyValue + "'");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    protected String getString(String key, String value,String resultColumnLabel){
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from " + tablename + " where `"+key+"`='" + value + "'");
            while (rs.next()){
                return rs.getString(resultColumnLabel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    protected String getString(String table1, String table2, String key, String value,String resultColumnLabel){
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from " + table1 + ", "+table2+" where `"+key+"`='" + value + "'");
            while (rs.next()){
                return rs.getString(resultColumnLabel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    protected List<String> getStrings(String table1, String table2, String key, String value, String[] resultColumnLabels){
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from " + table1 + ", "+table2+" where `"+key+"`='" + value + "'");
            List<String> strings = new ArrayList<>();
            while (rs.next()){
                for(String label : resultColumnLabels){
                    strings.add(rs.getString(label));
                }
                return strings;
            }
            return strings;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    protected int getInt(String key, String value,String resultColumnLabel){
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from " + tablename + " where `"+key+"`='" + value + "'");
            while (rs.next()){
                return rs.getInt(resultColumnLabel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    protected int getInt(String table1, String table2, String key, String value,String resultColumnLabel){
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from " + table1 + ", "+table2+" where `"+key+"`='" + value + "'");
            while (rs.next()){
                return rs.getInt(resultColumnLabel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    protected Date getDate(String key, String value,String resultColumnLabel){
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from " + tablename + " where `"+key+"`='" + value + "'");
            while (rs.next()){
                return rs.getDate(resultColumnLabel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    protected int getLastId(String idKey){
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from " + tablename + " order by " + idKey + " desc limit 1");
            while (rs.next()){
                return rs.getInt(idKey);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    protected String getCurrentDateString(){
        java.util.Date dt = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dt);
    }



}
