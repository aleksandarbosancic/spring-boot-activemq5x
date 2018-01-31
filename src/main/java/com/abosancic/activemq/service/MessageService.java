/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abosancic.activemq.service;

import com.abosancic.activemq.models.Message;
import com.google.gson.Gson;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;
import javax.jms.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

/**
 *
 * @author aleksandar
 */
@Service
public class MessageService {

    private final Log log = LogFactory.getLog(MessageService.class);
    
    private static final String template = "Hello, %s!";
    
    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Gson gson;
    
    public Message getMessage(String name) {
        return new Message(counter.incrementAndGet(),
                String.format(template, name));
    }

    public void sendMessages(Integer num) {
        // Send messages
        IntStream.rangeClosed(1, num).forEach(index -> {
            sendMessage(index);
        });
    }

    private void sendMessage(int index) {
        new Thread(() -> {
            log.info("Sending a new message.");
            log.info("Thread -> " + Thread.currentThread().getName());
            jmsTemplate.send("mailbox-destination", createMessage());
        }).start();
    }
    
    private MessageCreator createMessage() {
        return (Session session) -> session.createTextMessage(gson.toJson(getMessage()));
    }

    private Message getMessage() {
        return new Message(counter.incrementAndGet(), UUID.randomUUID().toString());
    }
    
}
