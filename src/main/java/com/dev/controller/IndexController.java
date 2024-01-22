package com.dev.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.model.marque.Marque;
import com.dev.model.message.Message;
import com.dev.model.service.MarqueService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/main")
public class IndexController {
    @GetMapping(path = "/hello" , produces = "application/json")
    public String getHello(){
        return "Hello All !!";
    }

    // @GetMapping(path = "/listeMongo", produces = "application/json")
    // public ArrayList<String> getMongoContain() {
    //     return new Message().envoyerMessage();
    // }
}
