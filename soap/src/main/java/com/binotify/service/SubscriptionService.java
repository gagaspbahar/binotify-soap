package com.binotify.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface SubscriptionService {
  @WebMethod
  public String newSubscription(@WebParam(name = "user", targetNamespace = "http://service.binotify.com/") int user,
  @WebParam(name = "artist", targetNamespace = "http://service.binotify.com/") int artist);

  @WebMethod
  public String updateSubscription(@WebParam(name = "user", targetNamespace = "http://service.binotify.com/") int user,
  @WebParam(name = "artist", targetNamespace = "http://service.binotify.com/") int artist, @WebParam(name = "status", targetNamespace = "http://service.binotify.com/") String status);
}
