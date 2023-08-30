package com.ecommerce.micrommerce.web.dao;

import com.ecommerce.micrommerce.model.Client;

import java.util.List;

public interface ClientDao {
    List<Client> findAll();
    Client findById(int id);
    Client save(Client client);
    Client modify(int id, Client client);
    Client delete(int id);
}
