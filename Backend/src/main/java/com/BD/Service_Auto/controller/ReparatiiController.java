package com.BD.Service_Auto.controller;

import com.BD.Service_Auto.model.Reparatii;
import com.BD.Service_Auto.service.ReparatiiService;
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
@RequestMapping("/Reparatii")
public class ReparatiiController {

    private static final Logger logger = LoggerFactory.getLogger(AngajatiController.class);

    @Autowired
    private ReparatiiService reparatiiService;

    //metoda care cauta reparatiile
    @GetMapping("/get")
    public ResponseEntity<List<Reparatii>> getAllReparatii(){
        List<Reparatii> reparatii = reparatiiService.getAllReparatii();
        return ResponseEntity.ok(reparatii);
    }

    //metoda care care cauta reparatiile dupa id
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Reparatii>> getRepairById(@PathVariable Integer id){
        Optional<Reparatii> reparatii = reparatiiService.getRepairById(id);
        return ResponseEntity.ok(reparatii);
    }

    //metoda care adauga reparatiile
    @PostMapping("/add")
    public ResponseEntity<String> saveReparatie(@RequestBody Reparatii reparatie, @RequestParam("nrInmatriculare") String nrInmatriculare){
        //Validare pentru descriere care sa contina numai litere
        if (!reparatie.getDescriere().matches("[a-zA-Z0-9 ]+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Descrierea trebuie să conțină doar litere.");
        }

        //Validare pentru numarul de inmatriculare care sa contina numai litere
        if (!nrInmatriculare.matches("[A-Z0-9]+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Numarul de inmatriculare trebuie să conțină doar litere mari și cifre.");
        }


        //Validare pentru cost
        if(reparatie.getCostManopera() <= 0)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Costul trebuie sa fie mai mare decat 0.");
        }

        //Validare pentru data de inceput/finalizare
        if(reparatie.getDataInceput() == null || reparatie.getDataFinalizare() == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Datile trebuie să existe.");
        }

        int result = reparatiiService.saveReparatie(reparatie.getDataInceput(), reparatie.getDataFinalizare(), reparatie.getDescriere(), reparatie.getCostManopera(), nrInmatriculare);

        if (result > 0) {
            return ResponseEntity.ok("Reparatia a fost adăugat cu succes");
        } else {
            return ResponseEntity.status(400).body("Eroare la inserare");
        }
    }

    //metoda care actualizeaza reparatiile
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody Reparatii reparatie){

        //Validare pentru descriere care sa contina numai litere
        if (!reparatie.getDescriere().matches("[a-zA-Z0-9 ]+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Descrierea trebuie să conțină doar litere.");
        }

        //Validare pentru cost
        if(reparatie.getCostManopera() <= 0)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Costul trebuie sa fie mai mare decat 0.");
        }

        //Validare pentru data de inceput/finalizare
        if(reparatie.getDataInceput() == null || reparatie.getDataFinalizare() == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Datile trebuie să existe.");
        }

        int updated = reparatiiService.update(reparatie.getDescriere(), reparatie.getCostManopera(), reparatie.getDataInceput(), reparatie.getDataFinalizare(), id);

        // Verificarea rezultatelor și răspunsul corespunzător
        if (updated > 0) {
            return ResponseEntity.ok("Reparatia a fost actualizat cu succes.");
        } else {
            return ResponseEntity.status(400).body("Nu s-a găsit angajatul cu acest id.");
        }
    }

    //metoda care sterge reparatiile dupa id
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        reparatiiService.delete(id);
    }

    //metoda care cauta reparatiile facute pe masini fabricate dupa un anumit an
    @GetMapping("/get/filterYear/{year}")
    public ResponseEntity<List<Object[]>> findRepairsOnRecentCars(@PathVariable Integer year){
        List<Object[]> result = reparatiiService.findRepairsOnRecentCars(year);
        return ResponseEntity.ok(result);
    }
}
