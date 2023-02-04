package com.easyrestaurant.core;

import com.easyrestaurant.utils.Defaults;

import java.sql.*;

public class PSQL extends Defaults {
    private Connection con;
    private ResultSet res;
    private Statement stm;


    public void connectToDb() {
        try {
            con = DriverManager.getConnection(SQL_URL, SQL_USERNAME, SQL_PASSWORD);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Use executeQuery method for SELECT statements
    public ResultSet executeQueryAndGetResults(String query) {
        try {
            stm = con.createStatement();
            res = stm.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    // Use executeUpdate method for DML (INSERT, UPDATE or DELETE)
    public void executeUpdate(String query) {
        try {
            stm = con.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }


    public void tearDown() {
        try {
            con.close();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
