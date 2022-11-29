package com.binotify;

import com.binotify.service.SubscriptionServiceImpl;

import jakarta.xml.ws.Endpoint;

public class App 
{
    public static void main( String[] args )
    {
        Endpoint.publish("http://0.0.0.0:8888/ws/subscription", new SubscriptionServiceImpl());
    }
}
