package com.BD.Service_Auto.controller;

import com.BD.Service_Auto.model.Clienti;
import com.BD.Service_Auto.model.Servicii;
import com.BD.Service_Auto.service.ClientiService;
import com.BD.Service_Auto.service.ServiciiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

//clasa care realizeaza legatura cu interfata prin cererile HTTP
@RestController
@Slf4j
@RequestMapping("/Servicii")
public class ServiciiController {

    @Autowired
    private ServiciiService serviciiService;

    //metoda care cauta serviciile
    @GetMapping("/get")
    public ResponseEntity<List<Servicii>> getAllServicii(){
        log.info("A fost primită o cerere POST pe /servicii/add cu datele: {}");
        List<Servicii> servicii = serviciiService.getAllServicii();
        return ResponseEntity.ok(servicii);
    }

    //metoda care cauta serviciile dupa id
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Servicii>> getById(@PathVariable Integer id){
        Optional<Servicii> serviciu = serviciiService.getById(id);
        return ResponseEntity.ok(serviciu);
    }

    //metoda care adauga un serviciu
    @PostMapping("/add")
    public ResponseEntity<String> saveServiciu(@RequestBody Servicii serviciu){

        //Validare pentru tip care sa contina numai litere
        if (!serviciu.getTip().matches("[a-zA-Z ]+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Tipul trebuie să conțină doar litere.");
        }

        if(serviciu.getPret() <= 0)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Pretul trebuie sa fie mai mare decat 0.");
        }

        int result = serviciiService.saveServiciu(serviciu.getTip(), serviciu.getPret(), serviciu.getDurata());

        if (result > 0) {
            return ResponseEntity.ok("Serviciul a fost adăugat cu succes");
        } else {
            return ResponseEntity.status(400).body("Eroare la inserare");
        }
    }

    //metoda care actualizeaza dupa id
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateServicii(@PathVariable Integer id, @RequestBody Servicii serviciu){
        //Validare pentru tip care sa contina numai litere
        if (!serviciu.getTip().matches("[a-zA-Z ]+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Tipul trebuie să conțină doar litere.");
        }

        //Validare pentru pret care trebuie sa fie mai mare decat 0
        if(serviciu.getPret() <= 0)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Pretul trebuie sa fie mai mare decat 0.");
        }

        //Validare pentru durata în formatul HH:MM:SS
        if (!serviciu.getDurata().toString().matches("^([0-1]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Durata trebuie să fie în formatul HH:MM:SS.");
        }

        int updated = serviciiService.update(serviciu.getTip(), serviciu.getPret(), serviciu.getDurata(), id);

        // Verificarea rezultatelor și răspunsul corespunzător
        if (updated > 0) {
            return ResponseEntity.ok("Serviciul a fost actualizat cu succes.");
        } else {
            return ResponseEntity.status(400).body("Nu s-a găsit angajatul cu acest id.");
        }
    }

    //metoda care sterge dupa id
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        serviciiService.delete(id);
    }
}
