package com.BD.Service_Auto.controller;

import com.BD.Service_Auto.model.Clienti;
import com.BD.Service_Auto.service.ClientiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@Slf4j
@RequestMapping("/client")
public class ClientiController {
    @Autowired
    private ClientiService clientiService;



    @RequestMapping("/")
    public String index() {
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");
        return "Howdy! Check out the Logs to see the output...";
    }

    @PostMapping("/add")
    public String add(@RequestBody Clienti clienti){
        log.info("A fost primită o cerere POST pe /client/add cu datele: {}", clienti);
        Clienti clientSalvat = clientiService.saveClienti(clienti);
        return String.valueOf(new ResponseEntity<>(clientSalvat, HttpStatus.CREATED));
    }

    @PostMapping("/test")
    public String addTest() {
        Clienti testClient = new Clienti();
        testClient.setNume("Test");
        testClient.setPrenume("Client");
        testClient.setAdresa("Test Address");
        testClient.setTelefon("1234567890");
        testClient.setEmail("test@example.com");
        log.info("A fost primită o cerere POST pe /client/add cu datele: {}", testClient);

        clientiService.saveClienti(testClient);
        return "Clientul de test a fost adăugat.";
    }

    @GetMapping("/get")
    public List<Clienti> getAllClienti(){
        log.info("A fost primită o cerere POST pe /client/add cu datele: {}");
        return clientiService.getAllClients();

    }
}
