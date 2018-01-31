/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abosancic.activemq.receiver;

/**
 *
 * @author aleksandar
 */
import com.abosancic.activemq.models.Message;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private static final Log log = LogFactory.getLog(Receiver.class);
    
    @Autowired
    private Gson gson;

    /**
     * When you receive a message, print it out, then shut down the application.
     * Finally, clean up any ActiveMQ server stuff.
     * 
     * @param message
     */
    @JmsListener(destination = "mailbox-destination")
    public void receiveMessage(String message) {
        log.info("Received <" + message + ">");
        processMessage(message);
    }

    /**
     * When you receive a message, print it out, then shut down the application.
     * Finally, clean up any ActiveMQ server stuff.
     * 
     * @param message
     */
    @JmsListener(destination = "spring-boot-test")
    public void receiveMessage1(String message) {
        log.info("Received <" + message + ">");
    }
    
    private void processMessage(String message) {
        Message recieved = gson.fromJson(message, Message.class);
        log.info(recieved);
    }
}
