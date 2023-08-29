package com.ecommerce.micrommerce.web.dao;

import com.ecommerce.micrommerce.model.Client;
import org.springframework.stereotype.Repository;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao{
    public static List<Client> clients = new ArrayList<>();

    static {
        try {
            clients.add(new Client(1, "Jordan", "Nguyen",birthdate("1989-04-16"),"13AA00002"));
            clients.add(new Client(2, "Lyoubomyr", "Didyk",birthdate("1988-07-10"),"13AA00003"));
            clients.add(new Client(3, "Anne", "Chollet",birthdate("1987-05-24"),"13AA00004"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date birthdate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(date);
    }

    @Override
    public List<Client> findAll() {
        return clients;
    }

    @Override
    public Client findById(int id) {
        for (Client client : clients) {
            if (client.getId() == id){
                return client;
            }
        }
        return null;
    }

    @Override
    public Client save(Client client) {
        clients.add(client);
        return client;
    }
}
