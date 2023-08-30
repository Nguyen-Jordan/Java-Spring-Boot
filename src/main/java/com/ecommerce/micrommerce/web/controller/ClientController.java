package com.ecommerce.micrommerce.web.controller;

import com.ecommerce.micrommerce.model.Client;
import com.ecommerce.micrommerce.web.dao.ClientDao;
import com.ecommerce.micrommerce.web.exceptions.ClientNotFoundException;
import com.ecommerce.micrommerce.web.exceptions.DrivingLicenseDoesntExistException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Api("API pour les opérations CRUD sur les produits.")
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientDao clientDao;
    private RestOperations restTemplate = new RestTemplate();

    public ClientController(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @ApiOperation(value = "Récupère tout les clients soit en stock!")
    @GetMapping
    public MappingJacksonValue clientList(){
        List<Client> clients = clientDao.findAll();
        return filterMapping(clients);
    }

    //Récupérer un produit par son Id
    @ApiOperation(value = "Récupère un client grâce à son ID à condition que celui-ci soit existant!")
    @GetMapping(value = "/{id}")
    public MappingJacksonValue showClient(@PathVariable int id) {
        Client findClient = clientDao.findById(id);
        if (findClient == null) {
            throw new ClientNotFoundException("Le client avec l'id " + id + " est INTROUVABLE.");
        }
        return filterMapping(findClient);
    }

    @ApiOperation(value = "Ajoute un client à la liste")
    @PostMapping
    public ResponseEntity<Client> addClient (@RequestBody Client client) {
        textException(client);
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
    @PutMapping("/{id}")
    public MappingJacksonValue modifyClient (@PathVariable int id, @RequestBody Client client){
        textException(client);
        Client updatedClient = clientDao.modify(id, client);
        return filterMapping(updatedClient);
    }

    @ApiOperation(value = "Retire un client existant")
    @DeleteMapping("/{id}")
    public Client deleteClient (@PathVariable int id) {
        return clientDao.delete(id);
    }

    public Boolean isValidDrivingLicense (String drivingLicenseNumb){
        return restTemplate.getForObject("http://localhost:8081/licenses/" + drivingLicenseNumb, Boolean.class);
    }

    public MappingJacksonValue filterMapping(Object client){
        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("id");
        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);
        MappingJacksonValue clientsFiltres = new MappingJacksonValue(client);
        clientsFiltres.setFilters(listDeNosFiltres);
        return clientsFiltres;
    }

    public void textException(Client client){
        String drivingLicenseNumb = client.getDrivingLicenseNumb();
        if (!isValidDrivingLicense(drivingLicenseNumb)){
            throw new DrivingLicenseDoesntExistException("Le numéro de permis " + drivingLicenseNumb  +" enregistré est un permis de conduire volé. Nous avons contacté la police, agenouillez-vous et tourner le dos à la porte avec les mains derrière la tête. La police est en route. Merci d’utiliser nos services. N’oubliez pas de nous recommander! ");
        }
    }
}
