package com.ecommerce.micrommerce.web.controller;

import com.ecommerce.micrommerce.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ClientController {

    @GetMapping("/Clients")
    public String showClients() {
        return "Un exemple d'un client";
    }

    @GetMapping("/Clients/{id}")
    public Client showClient(@PathVariable int id) throws ParseException {
        String dateOfTheBirth = "1989-04-16 +1200";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd Z");
        Client client = new Client(id, "Jordan", "Nguyen",sdf.parse(dateOfTheBirth),"13AA00002");
        return client;
    }
}
