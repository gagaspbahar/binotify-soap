package com.binotify.service;

import jakarta.annotation.Resource;
import jakarta.jws.WebService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.handler.MessageContext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.binotify.core.Database;

@WebService
public class SubscriptionServiceImpl implements SubscriptionService {
  @Resource 
  public WebServiceContext wsContext;

  // @WebMethod(exclude = true)
  // public void setWsContext(WebServiceContext wsContext) {
  //   this.wsContext = wsContext;
  // }

  // @WebMethod(operationName = "getInfo")
  // public String getInfo()
  // {
  //   HttpServletRequest request = (HttpServletRequest)wsContext.getMessageContext().get(MessageContext.SERVLET_REQUEST);
  //   return "IP: "+ request.getRemoteAddr()+", Port: "+request.getRemotePort()+", Host: "+request.getRemoteHost();
  // }
  

  @Override
  public String newSubscription(int user, int artist) {
    MessageContext msgContext = wsContext.getMessageContext();
    HttpServletRequest request = (HttpServletRequest)msgContext.get(MessageContext.SERVLET_REQUEST);
    System.out.println(msgContext);
    System.out.println(request);
    // HttpServletRequest request = (HttpServletRequest)wsContext.getMessageContext().get(MessageContext.SERVLET_REQUEST);
    System.out.println(request.getRemoteAddr());
    try {
      Database db = new Database();
      Connection connection = db.getConnection();
      Statement statement = connection.createStatement();
      String query = "SELECT * FROM subscriptions WHERE user_id = " + user + " AND artist_id = " + artist;
      ResultSet result = statement.executeQuery(query);
      if (result.next()) {
        return "User already subscribed to artist";
      }
      String query2 = "INSERT INTO subscription (subscriber_id, creator_id) VALUES (" + user + ", " + artist + ")";
      statement.executeUpdate(query2);
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
      String sql = "UPDATE subscription SET status = '" + status + "' WHERE subscriber_id = " + user
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
