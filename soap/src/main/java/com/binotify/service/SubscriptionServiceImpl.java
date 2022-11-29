package com.binotify.service;

import jakarta.annotation.Resource;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.handler.MessageContext;
import com.sun.net.httpserver.HttpExchange;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.binotify.core.Database;
import com.binotify.model.LogModel;

@WebService(endpointInterface = "com.binotify.service.SubscriptionService")
public class SubscriptionServiceImpl implements SubscriptionService {
  @Resource 
  public WebServiceContext wsContext;

  public Boolean validateApiKey() {
    String[] API_KEYS = {"PremiumApp", "Postman", "RestClient", "RegularApp"};
    MessageContext mc = wsContext.getMessageContext();
    HttpExchange exchange = (HttpExchange) mc.get("com.sun.xml.ws.http.exchange");
    String apiKey = exchange.getRequestHeaders().getFirst("X-API-KEY");
    if (apiKey == null) {
      return false;
    } else if (apiKey.equals(API_KEYS[0]) || apiKey.equals(API_KEYS[1]) || apiKey.equals(API_KEYS[2]) || apiKey.equals(API_KEYS[3])) {
      return true;
    } else {
      return false;
    }
  }

  public void log(String description) {
    MessageContext msgContext = wsContext.getMessageContext();
    HttpExchange httpExchange = (HttpExchange) msgContext.get("com.sun.xml.ws.http.exchange");
    String ip = httpExchange.getRemoteAddress().getAddress().getHostAddress();
    String endpoint = httpExchange.getRequestURI().toString();
    LogModel logModel = new LogModel();
    String apiKey = httpExchange.getRequestHeaders().getFirst("X-API-KEY");
    String desc = apiKey + ": " + description;
    logModel.InsertLog(desc, endpoint, ip);
  }
  
  @Override
  public String newSubscription(int subscriber, int creator) {
    if (!validateApiKey()){
      return "Invalid API Key";
    }
    try {
      Database db = new Database();
      Connection connection = db.getConnection();
      Statement statement = connection.createStatement();
      String query = "SELECT * FROM subscription WHERE subscriber_id = " + subscriber + " AND creator_id = " + creator;
      System.out.println(query);
      ResultSet result = statement.executeQuery(query);
      if (result.next()) {
        log("subscriber already subscribed to creator");
        return "subscriber already subscribed to creator";
      }
      String query2 = "INSERT INTO subscription (subscriber_id, creator_id) VALUES (" + subscriber + ", " + creator + ")";
      statement.executeUpdate(query2);
      String message = "subscriber " + subscriber + " subscribed to creator " + creator + " successfully with status Pending";
      log(message);
      return message;
    } catch (Exception e) {
      e.printStackTrace();
      log("Error creating subscription");
      return "Error creating subscription";
    }
  }

  @Override
  public String updateSubscription(int subscriber, int creator, String status) {
    if (!validateApiKey()){
      return "Invalid API Key";
    }
    try {
      Database db = new Database();
      Connection conn = db.getConnection();
      Statement statement = conn.createStatement();
      String sql = "UPDATE subscription SET status = '" + status + "' WHERE subscriber_id = " + subscriber
          + " AND creator_id = " + creator;
      System.out.println(sql);
      statement.executeUpdate(sql);
      String message = "Successfully updated status of subscription to " + status;
      log(message);
      return message;
    } catch (Exception e) {
      e.printStackTrace();
      String message = "Error updating subscription of " + subscriber + " to " + creator;
      log(message); 
      return message;
    }
  }

  public String checkSubscription(int subscriber, int creator) {
    if (!validateApiKey()){
      return "Invalid API Key";
    }
    try {
      Database db = new Database();
      Connection conn = db.getConnection();
      Statement statement = conn.createStatement();
      String sql = "SELECT status FROM subscription WHERE subscriber_id = " + subscriber + " AND creator_id = " + creator;
      ResultSet result = statement.executeQuery(sql);
      if (result.next()) {
        String status = result.getString("status");
        log("Successfully checked subscription for subscriber " + subscriber + " to creator " + creator + " with status " + status);
        return status;
      } else {
        String message = "subscriber " + subscriber + " is not subscribed to creator " + creator;
        log(message);
        return message;
      }
    } catch (Exception e) {
      e.printStackTrace();
      String message = "Error getting subscription of " + subscriber + " to " + creator;
      log(message); 
      return message;
    }
  }
}
