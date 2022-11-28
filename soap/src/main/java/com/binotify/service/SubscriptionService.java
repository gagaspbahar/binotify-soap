package com.binotify.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface SubscriptionService {
  @WebMethod
  public String newSubscription(int user, int artist);

  @WebMethod
  public String updateSubscription(int user, int artist, String status);
}
