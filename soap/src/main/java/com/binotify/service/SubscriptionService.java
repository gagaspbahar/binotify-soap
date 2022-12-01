package com.binotify.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.DOCUMENT, parameterStyle = ParameterStyle.WRAPPED)
public interface SubscriptionService {
	@WebMethod
	public String newSubscription(
			@WebParam(name = "subscriber", targetNamespace = "http://service.binotify.com/") int subscriber,
			@WebParam(name = "creator", targetNamespace = "http://service.binotify.com/") int creator);

	@WebMethod
	public String checkSubscription(
			@WebParam(name = "subscriber", targetNamespace = "http://service.binotify.com/") int subscriber,
			@WebParam(name = "creator", targetNamespace = "http://service.binotify.com/") int creator);

	@WebMethod
	public String updateSubscription(
			@WebParam(name = "subscriber", targetNamespace = "http://service.binotify.com/") int subscriber,
			@WebParam(name = "creator", targetNamespace = "http://service.binotify.com/") int creator,
			@WebParam(name = "status", targetNamespace = "http://service.binotify.com/") String status);

	@WebMethod
	public String getAllSubscriptionRequest(
			@WebParam(name = "page", targetNamespace = "http://service.binotify.com/") int page);

	@WebMethod
	public String getAllSubscriptionRequestBySubscriber(
			@WebParam(name = "subscriber", targetNamespace = "http://service.binotify.com/") int subscriber);

	@WebMethod
	public String getAllSubscribedArtistsBySubscriber(
			@WebParam(name = "subscriber", targetNamespace = "http://service.binotify.com/") int subscriber);

}
