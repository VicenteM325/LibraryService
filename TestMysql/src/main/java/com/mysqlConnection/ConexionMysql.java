package com.mysqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMysql {
    private static final String url = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String user = "VM";
    private static final String password = "C325MTV";

    public static Connection conectar() throws SQLException{
        return DriverManager.getConnection(url,user,password);

    }
}
