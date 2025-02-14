package com.BD.Service_Auto.controller;

import com.BD.Service_Auto.model.Angajati;
import com.BD.Service_Auto.model.Clienti;
import com.BD.Service_Auto.service.ClientiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

//clasa care realizeaza legatura cu interfata prin cererile HTTP
@RestController
@Slf4j
@RequestMapping("/Clienti")
public class ClientiController {
    @Autowired
    private ClientiService clientiService;



    //functie de verificare a functionalitatii
    @RequestMapping("/")
    public String index() {
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");
        return "ABC";
    }

    //metoda de adaugare
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody Clienti clienti){
        log.info("A fost primită o cerere POST pe /client/add cu datele: {}", clienti);

        //Validare pentru nume care sa contina numai litere
        if (!clienti.getNume().matches("[a-zA-Z]+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Numele trebuie să conțină doar litere.");
        }

        //Validare pentru prenume care sa contina numai litere
        if (!clienti.getPrenume().matches("[a-zA-Z]+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Prenumele trebuie să conțină doar litere.");
        }

        //Validare pentru număr de telefon: format 07xxxxxxxx
        if (!clienti.getTelefon().matches("07\\d{8}")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Numărul de telefon trebuie să înceapă cu 07 și să aibă 10 cifre.");
        }

        int result = clientiService.saveClienti(clienti.getNume(), clienti.getPrenume(),
                clienti.getAdresa(), clienti.getTelefon(), clienti.getEmail());

        if (result > 0) {
            return ResponseEntity.ok("Clientul a fost adăugat cu succes");
        } else {
            return ResponseEntity.status(400).body("Eroare la inserare");
        }
    }

//    @PostMapping("/test")
//    public String addTest() {
//        Clienti testClient = new Clienti();
//        testClient.setNume("Test");
//        testClient.setPrenume("Client");
//        testClient.setAdresa("Test Address");
//        testClient.setTelefon("1234567890");
//        testClient.setEmail("test@example.com");
//        log.info("A fost primită o cerere POST pe /client/add cu datele: {}", testClient);
//
//        clientiService.saveClienti(testClient);
//        return "Clientul de test a fost adăugat.";
//    }

    //metoda de afisare
    @GetMapping("/get")
    public ResponseEntity<List<Clienti>> getAllClienti(){
        log.info("A fost primită o cerere POST pe /client/add cu datele: {}");
        List<Clienti> client = clientiService.getAllClients();
        return ResponseEntity.ok(client);

    }

    //metoda de afisare in functie de id
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Clienti>> getClientById(@PathVariable Integer id){
        Optional<Clienti> client = clientiService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    //metoda de actualizare
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody Clienti clienti) {

        //Validare pentru nume care sa contina numai litere
        if (!clienti.getNume().matches("[a-zA-Z]+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Numele trebuie să conțină doar litere.");
        }

        //Validare pentru prenume care sa contina numai litere
        if (!clienti.getPrenume().matches("[a-zA-Z]+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Prenumele trebuie să conțină doar litere.");
        }

        //Validare pentru număr de telefon: format 07xxxxxxxx
        if (!clienti.getTelefon().matches("07\\d{8}")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Numărul de telefon trebuie să înceapă cu 07 și să aibă 10 cifre.");
        }

        int updated = clientiService.update(clienti.getNume(), clienti.getPrenume(), clienti.getAdresa(), clienti.getEmail(), clienti.getTelefon(), id);

        if (updated > 0) {
            return ResponseEntity.ok("Client actualizat cu succes");
        } else {
            return ResponseEntity.status(400).body("Nu s-a găsit clientul cu acest CNP");
        }
    }

    //metoda de stergere
    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable Integer id){
        clientiService.delete(id);
    }

    @GetMapping("/get/filterInsurance/{insurance}")
    public ResponseEntity<List<Object[]>> findClientsByInsurance(@PathVariable String insurance){
        List<Object[]> clienti = clientiService.findClientsWithoutInsurance(insurance);
        return ResponseEntity.ok(clienti);
    }

    //metoda de afisare a clientilor in functie de cost
    @GetMapping("/get/filter_cost")
    public ResponseEntity<List<Object[]>> findClientsByCost(){
        List<Object[]> clienti = clientiService.findClientsByCost();
        return ResponseEntity.ok(clienti);
    }

    //metoda care cauta clientii care au platit mai mult decat un cost X
    @GetMapping("/get/filterCost/{cost}")
    public ResponseEntity<List<Object[]>> findClientsByExpensiveCost(@PathVariable Integer cost){
        List<Object[]> clients = clientiService.findClientsByExpensiveCost(cost);
        return ResponseEntity.ok(clients);
    }
}
