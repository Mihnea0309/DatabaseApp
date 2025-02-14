package com.BD.Service_Auto.controller;


import com.BD.Service_Auto.model.ReparatiiAngajati;
import com.BD.Service_Auto.service.ReparatiiAngajatiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.Date;
import java.util.List;

//clasa care realizeaza legatura cu interfata prin cererile HTTP
@RestController
@RequestMapping("/reparatii_angajati")
public class ReparatiiAngajatiController {

    @Autowired
    private ReparatiiAngajatiService reparatiiAngajatiService;

    //metoda care cauta in tabela ReparatiiAngajati
    @GetMapping("/get")
    public ResponseEntity<List<ReparatiiAngajati>> getAllReparatiiAngajati(){
        List<ReparatiiAngajati> reparatiiAngajatii = reparatiiAngajatiService.getAllReparatiiAngajati();
        return ResponseEntity.ok(reparatiiAngajatii);
    }

    //metoda care cauta angajatii in functie de anul in care au terminat reparatia
    @GetMapping("/get/filterYear/{year}")
    public ResponseEntity<List<Object[]>> getEmployeesByFilter(@PathVariable int year) {
        List<Object[]> results = reparatiiAngajatiService.findEmployeesByFilter(year);
        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(results);
        }
        return ResponseEntity.ok(results);
    }

    //metoda care cauta angajatii in functie de numarul de ore lucrate la reparatii
    @GetMapping("/get/filterHours/{hours}")
    public ResponseEntity<List<Object[]>> findTotalHoursWorkedPerEmployee(@PathVariable Integer hours){
        List<Object[]> results = reparatiiAngajatiService.findTotalHoursWorkedPerEmployee(hours);
        return ResponseEntity.ok(results);
    }

}
