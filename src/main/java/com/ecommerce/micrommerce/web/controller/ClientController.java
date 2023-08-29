package com.ecommerce.micrommerce.web.controller;

import com.ecommerce.micrommerce.model.Client;
import com.ecommerce.micrommerce.web.dao.ClientDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

@Api("API pour les opérations CRUD sur les produits.")
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientDao clientDao;

    public ClientController(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @ApiOperation(value = "Récupère tout les clients soit en stock!")
    @GetMapping
    public List<Client> clientList(){
        return clientDao.findAll();
    }

    //Récupérer un produit par son Id
    @ApiOperation(value = "Récupère un client grâce à son ID à condition que celui-ci soit existant!")
    @GetMapping(value = "/{id}")
    public Client showClient(@PathVariable int id) {
        return clientDao.findById(id);
    }

    @ApiOperation(value = "Ajoute un client à la liste")
    @PostMapping
    public ResponseEntity<Client> addClient (@RequestBody Client client) {
        Client clientAdded = clientDao.save(client);
        if (Objects.isNull(clientAdded)) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Modifie les données d'un client existant")
    @PutMapping
    public void modifyClient (String firstName, String lastName, String dateOfBirth, String drivingLicense) throws ParseException {

    }

    @ApiOperation(value = "Retire un client existant")
    @DeleteMapping("/{id}")
    public void deleteClient () {

    }
}
