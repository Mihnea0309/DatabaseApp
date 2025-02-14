package com.BD.Service_Auto.controller;

import com.BD.Service_Auto.model.Piese;
import com.BD.Service_Auto.model.Servicii;
import com.BD.Service_Auto.service.PieseService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//clasa care realizeaza legatura cu interfata prin cererile HTTP
@RestController
@Slf4j
@RequestMapping("/Piese")
public class PieseController {

    @Autowired
    private PieseService pieseService;
    private static final Logger logger = LoggerFactory.getLogger(AngajatiController.class);

    //metoda care cauta piesele
    @GetMapping("/get")
    public ResponseEntity<List<Piese>> getAllPiese(){
        List<Piese> piese = pieseService.getAllPiese();
        return ResponseEntity.ok(piese);
    }

    //metoda care cauta dupa id
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Piese>> getPieseById(@PathVariable Integer id){
        Optional<Piese> piese = pieseService.getPieseById(id);
        return ResponseEntity.ok(piese);
    }

    //metoda care adauga piese
    @RequestMapping("/add")
    public ResponseEntity<String> savePiese(@RequestBody Piese piesa){

        //Validare pentru preț sa fie un float
        try {
            Float.parseFloat(String.valueOf(piesa.getPret()));
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Prețul trebuie să fie un număr valid cu zecimale.");
        }

        //Validare pentru cantitate sa fie un numar intreg
        try {
            Integer.parseInt(String.valueOf(piesa.getStoc()));
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Stocul trebuie să fie un număr întreg valid.");
        }


        int add = pieseService.savePiese(piesa.getNume(), piesa.getPret(), piesa.getStoc());
        return ResponseEntity.ok("Piesa a fost adaugata!");
    }

    //metoda care sterge dupa id
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        pieseService.delete(id);
    }

    //metoda care actualizeaza dupa id
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody Piese piesa, @PathVariable Integer id){
        int update = pieseService.update(piesa.getNume(), piesa.getPret(), piesa.getStoc(), id);
        return ResponseEntity.ok("Piesa a fost actualizata!");
    }
}
