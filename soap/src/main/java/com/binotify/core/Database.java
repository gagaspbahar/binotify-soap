package com.binotify.core;

import java.sql.*;

import io.github.cdimascio.dotenv.Dotenv;



public class Database {
  private Connection connection;
  private Dotenv dotenv = Dotenv.load();
  private String DB_URL = dotenv.get("DB_URL");
  private String DB_USERNAME = dotenv.get("DB_USERNAME");
  private String DB_PASSWORD = dotenv.get("DB_PASSWORD");

  public Database(){
    try{
      this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error connecting to database");
    }
  }

  public Connection getConnection(){
    return connection;
  }
}
