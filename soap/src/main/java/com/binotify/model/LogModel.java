package com.binotify.model;

import java.sql.Statement;

import com.binotify.core.Database;
import com.binotify.types.LogType;

public class LogModel extends Database {
  public LogModel() {
    super();
  }

  public String InsertLog(String desc, String endpoint, String ip) {
    try {
      Statement statement = this.connection.createStatement();
      String query = "INSERT INTO logging (description, endpoint, ip) VALUES ('" + desc + "', '" + endpoint + "', '" + ip + "')";
      statement.executeUpdate(query);
    } catch (Exception e) {
      e.printStackTrace();
      return "Failed to insert log";
    }
    return "Successfully inserted log";
  }
}
