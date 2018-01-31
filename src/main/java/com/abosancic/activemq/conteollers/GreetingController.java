/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abosancic.activemq.conteollers;

/**
 *
 * @author aleksandar
 */
import com.abosancic.activemq.models.Message;
import com.abosancic.activemq.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    private MessageService messageService;
    
    @GetMapping("/message")
    public Message getMessage(@RequestParam(value = "name", defaultValue = "World") String name) {
        return messageService.getMessage(name);
    }
    
    @GetMapping("/init-message")
    public void sendJms(@RequestParam(value = "num", defaultValue = "1") Integer num) {
        messageService.sendMessages(num);
    }

}
