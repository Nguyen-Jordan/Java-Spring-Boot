package com.ecommerce.micrommerce.web.controller;

import com.ecommerce.micrommerce.model.Customer;
import com.ecommerce.micrommerce.web.dao.ClientDao;
import com.ecommerce.micrommerce.web.exceptions.DrivingLicenseDoesntExistException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@Api("API pour les opérations CRUD sur les produits.")
@RestController
@RequestMapping("/customers")
public class ClientController {

    @Autowired
    private final ClientDao clientDao;
    private RestOperations restTemplate = new RestTemplate();

    public ClientController(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public MappingJacksonValue clientList(){
        Iterable<Customer> clients = clientDao.findAll();
        return filterMapping(clients);
    }

    //Récupérer un produit par son Id
    @ApiOperation(value = "Récupère un client grâce à son ID à condition que celui-ci soit existant!")
    @GetMapping(value = "/{id}")
    public Customer showClient(@PathVariable int id) {
        return clientDao.findById(id);
    }

    @ApiOperation(value = "Ajoute un client à la liste")
    @PostMapping
    public ResponseEntity<Customer> addClient (@RequestBody Customer customer) {
        textException(customer);
        Customer customerAdded = clientDao.save(customer);
        if (Objects.isNull(customerAdded)) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customerAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Modifie les données d'un client existant")
    @PutMapping
    public void modifyClient (@RequestBody Customer customer){
        textException(customer);
        clientDao.save(customer);
    }

    @ApiOperation(value = "Retire un client existant")
    @DeleteMapping("/{id}")
    public void deleteClient (@PathVariable int id) {
        clientDao.deleteById(id);
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

    public void textException(Customer customer){
        String drivingLicenseNumb = customer.getLicenseId();
        if (!isValidDrivingLicense(drivingLicenseNumb)){
            throw new DrivingLicenseDoesntExistException("Le numéro de permis " + drivingLicenseNumb  +" enregistré est un permis de conduire volé. Nous avons contacté la police, agenouillez-vous et tourner le dos à la porte avec les mains derrière la tête. La police est en route. Merci d’utiliser nos services. N’oubliez pas de nous recommander! ");
        }
    }
}
