package com.BD.Service_Auto.controller;


import com.BD.Service_Auto.model.Angajati;
import com.BD.Service_Auto.service.AngajatiService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//clasa care realizeaza legatura cu interfata prin cererile HTTP
@RestController
@Slf4j
@RequestMapping("/Angajati")
public class AngajatiController {

    @Autowired
    private AngajatiService angajatiService;
    private static final Logger logger = LoggerFactory.getLogger(AngajatiController.class);

    //metoda de adaugare
    @RequestMapping("/add")
    public ResponseEntity<String> add(@RequestBody Angajati angajati){
        logger.info("Received request to add employee: {}", angajati);
        logger.info(angajati.getNumarTelefon());

        //validare pentru nume care sa contina numai litere
        if (!angajati.getNume().matches("[a-zA-Z]+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Numele trebuie să conțină doar litere.");
        }

        //validare pentru prenume care sa contina numai litere
        if (!angajati.getPrenume().matches("[a-zA-Z]+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Prenumele trebuie să conțină doar litere.");
        }

        //validare pentru CNP care sa contina 13 cifre
        if (!angajati.getCnp().matches("\\d{13}")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("CNP-ul trebuie să conțină exact 13 cifre.");
        }

        //validare pentru număr de telefon: format 07xxxxxxxx
        if (!angajati.getNumarTelefon().matches("07\\d{8}")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Numărul de telefon trebuie să înceapă cu 07 și să aibă 10 cifre.");
        }


        int result = angajatiService.saveAngajati(angajati.getNume(), angajati.getPrenume(), angajati.getCnp(),
                angajati.getEmail(), angajati.getAdresa(), angajati.getNumarTelefon(),
                angajati.getFunctie(), angajati.getSalariu(), angajati.getParolaSite());

        if (result > 0) {
            return ResponseEntity.ok("Angajatul a fost adăugat cu succes");
        } else {
            return ResponseEntity.status(400).body("Eroare la inserare");
        }
    }

    //metoda de afisare a angajatilor
    @GetMapping("/get")
    public ResponseEntity<List<Angajati>> getAllAngajati(){
        List<Angajati> angajati = angajatiService.getAllAngajati();
        return ResponseEntity.ok(angajati);
    }

    //metoda de cautare a angajatilor in functie de id
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Angajati>> getAngajatById(@PathVariable Integer id) {
        Optional<Angajati> angajat = angajatiService.findById(id);
        return ResponseEntity.ok(angajat);
    }

    //metoda de actualizare
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody Angajati angajat) {
        logger.info("Updating employee with id: {}, new address: {}, phone number: {}", id, angajat.getAdresa(), angajat.getAdresa());

        //Validare pentru nume care sa contina numai litere
        if (!angajat.getNume().matches("[a-zA-Z]+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Numele trebuie să conțină doar litere.");
        }

        //Validare pentru prenume care sa contina numai litere
        if (!angajat.getPrenume().matches("[a-zA-Z]+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Prenumele trebuie să conțină doar litere.");
        }

        //Validare pentru CNP care sa contina 13 cifre
        if (!angajat.getCnp().matches("\\d{13}")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("CNP-ul trebuie să conțină exact 13 cifre.");
        }

        //Validare pentru număr de telefon: format 07xxxxxxxx
        if (!angajat.getNumarTelefon().matches("07\\d{8}")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Numărul de telefon trebuie să înceapă cu 07 și să aibă 10 cifre.");
        }

        // Apelarea serviciului pentru actualizarea datelor
        int updated = angajatiService.update(angajat.getNume(), angajat.getPrenume(), angajat.getCnp(), angajat.getEmail(), angajat.getAdresa(),
                angajat.getFunctie(), angajat.getSalariu(), angajat.getNumarTelefon(), id);

        // Verificarea rezultatelor și răspunsul corespunzător
        if (updated > 0) {
            return ResponseEntity.ok("Angajatul a fost actualizat cu succes.");
        } else {
            return ResponseEntity.status(400).body("Nu s-a găsit angajatul cu acest id.");
        }
    }


    //metoda de stergere
    @DeleteMapping("/delete/{id}")
    public void deleteAngajat(@PathVariable Integer id) {
        angajatiService.delete(id);
    }

    //metoda de cautare a angajatilor care nu lucreaza la reparatii in functie de salariu
    @GetMapping("/get/employees/filterSalary/{salary}")
    public ResponseEntity<List<Object[]>> findEmployeesWithoutRepairs(@PathVariable Integer salary){
        List<Object[]> result = angajatiService.findEmployeesWithoutRepairs(salary);
        if(salary>0)
        {
            logger.info("Salariul exista");
        }
        return ResponseEntity.ok(result);
    }

}
