/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.java.back.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author spezia
 */
public class ConexaoMysqlJDBC implements ConexaoJDBC {

    private Connection connection = null;

    public ConexaoMysqlJDBC() throws SQLException, ClassNotFoundException {
        Class.forName("org.mysql.Driver");

        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "1");

        //antigo postgres
        //this.connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:5432/test?ApplicationName=TaskList", properties);
        
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",properties.getProperty("user"),properties.getProperty("password")); //tasks-java-backend
        this.connection.setAutoCommit(false);
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConexaoMysqlJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void commit() throws SQLException {
        this.connection.commit();
        this.close();
    }

    @Override
    public void rollback() {
        if (this.connection != null) {
            try {
                this.connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(ConexaoMysqlJDBC.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                this.close();
            }
        }
    }

}
