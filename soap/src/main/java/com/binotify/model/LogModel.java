package com.binotify.model;

import java.sql.Statement;

import com.binotify.core.Database;
import com.binotify.types.LogType;

public class LogModel extends Database {
  private LogModel() {
    super();
  }

  public String InsertLog(LogType log) {
    try {
      Statement statement = this.connection.createStatement();
      String query = "INSERT INTO logging (description, endpoint, requested_at, ip) VALUES ('" + log.description
          + "', '" + log.endpoint + "', '" + log.requested_at + "', '" + log.ip + "')";
      statement.executeUpdate(query);
    } catch (Exception e) {
      e.printStackTrace();
      return "Failed to insert log";
    }

    return "Successfully inserted log";
  }
}
