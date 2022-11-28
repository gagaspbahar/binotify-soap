package com.binotify.service;

import jakarta.jws.WebService;

import java.sql.Connection;
import java.sql.Statement;

import com.binotify.core.Database;

@WebService(endpointInterface = "com.binotify.service.SubscriptionService")
public class SubscriptionServiceImpl implements SubscriptionService {

  @Override
  public String newSubscription(int user, int artist) {
    try {
      Database db = new Database();
      Connection connection = db.getConnection();
      Statement statement = connection.createStatement();
      String query = "INSERT INTO subscriptions (user_id, artist_id) VALUES (" + user + ", " + artist + ")";
      statement.executeUpdate(query);
      return "Subscription created with status PENDING";
    } catch (Exception e) {
      e.printStackTrace();
      return "Error creating subscription";
    }
  }

  @Override
  public String updateSubscription(int user, int artist, String status) {
    try {
      Database db = new Database();
      Connection conn = db.getConnection();
      Statement statement = conn.createStatement();
      String sql = "UPDATE binotify_soap.subscriptions SET status = '" + status + "' WHERE subscriber_id = " + user
          + " AND creator_id = " + artist;
      statement.executeUpdate(sql);
      return "Successfully updated status.";
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error updating subscription");
      return "Error updating subscription";
    }

  }
}
