package com.BD.Service_Auto.controller;


import com.BD.Service_Auto.model.Angajati;
import com.BD.Service_Auto.model.Masini;
import com.BD.Service_Auto.service.MasiniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

//clasa care realizeaza legatura cu interfata prin cererile HTTP
@RestController
@RequestMapping("/Masini")
public class MasiniController {

    @Autowired
    private MasiniService masiniService;

    //meoda pentru afisare
    @GetMapping("/get")
    public ResponseEntity<List<Masini>> getAllMasini(){
        List<Masini> masini = masiniService.getAllMasini();
        return ResponseEntity.ok(masini);
    }

    //metoda pentru cautare dupa id
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Masini>> getCarById(@PathVariable Integer id){
        Optional<Masini> masini = masiniService.getCarById(id);
        return ResponseEntity.ok(masini);
    }

    //metoda pentru stergere in functie de id
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        masiniService.delete(id);
    }

    //metoda pentru actualizare in functie de id
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody Masini masina){
        //Validare pentru marca care sa contina numai litere
        if (!masina.getMarca().matches("[a-zA-Z]+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Marca trebuie să conțină doar litere.");
        }

        //Validare pentru an care sa contina 4 cifre
        if (!masina.getAnFabricatie().matches("\\d{4}")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Anul trebuie să conțină exact 4 cifre.");
        }

        int update = masiniService.update(masina.getMarca(), masina.getModel(), masina.getAnFabricatie(), masina.getNumar_Inmatriculare(),
                masina.getAsigurare(), masina.getSerieSasiu(), masina.getId());

        return ResponseEntity.ok("Masina a fost actualizata");
    }

    //metoda de adaugare
    @RequestMapping("/add")
    public ResponseEntity<String> add(@RequestBody Masini masina, @RequestParam("email") String email){

        //Validare pentru marca care sa contina numai litere
        if (!masina.getMarca().matches("[a-zA-Z]+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Marca trebuie să conțină doar litere.");
        }

        //Validare pentru an care sa contina 4 cifre
        if (!masina.getAnFabricatie().matches("\\d{4}")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Anul trebuie să conțină exact 4 cifre.");
        }

        int add = masiniService.saveMasini(masina.getMarca(), masina.getModel(), masina.getAnFabricatie(), masina.getNumar_Inmatriculare(),
                masina.getAsigurare(), masina.getSerieSasiu(), email);

        return ResponseEntity.ok("Masina a fost adaugata cu succes");
    }

    //metoda care cauta masinile in functie de pretul reparatiei
    @GetMapping("/get/filterPrice/{price}")
    public ResponseEntity<List<Object[]>> getCarsByRepairPrice(@PathVariable("price") Float price){
        List<Object[]> masini = masiniService.findCarsByRepairPrice(price);
        return ResponseEntity.ok(masini);
    }

    //metoda care cauta masinile fara reparatii terminate
    @GetMapping("/get/filter2")
    public ResponseEntity<List<Object[]>> getCarsWithoutRepairs(){
        List<Object[]> results = masiniService.findCarsWithoutRepairs();
        return ResponseEntity.ok(results);
    }

    //metoda care cauta proprietariul masinii dupa nume
    @GetMapping("/get/filterOwner/{nume}/{prenume}")
    public ResponseEntity<List<Object[]>> getOwnerOfCars(@PathVariable("nume") String nume, @PathVariable("prenume") String prenume){
       List<Object[]> results = masiniService.findOwnerOfCars(nume, prenume);
       return ResponseEntity.ok(results);
    }
}
