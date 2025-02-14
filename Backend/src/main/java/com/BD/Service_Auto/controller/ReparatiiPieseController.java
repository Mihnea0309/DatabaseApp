package com.BD.Service_Auto.controller;

import com.BD.Service_Auto.model.ReparatiiPiese;
import com.BD.Service_Auto.service.ReparatiiPieseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//clasa care realizeaza legatura cu interfata prin cererile HTTP
@RestController
@RequestMapping("/reparatii_piese")
public class ReparatiiPieseController {

    @Autowired
    private ReparatiiPieseService reparatiiPieseService;

    //metoda care cauta in tabela ReparatiiPiese
    @GetMapping("/get")
    public ResponseEntity<List<ReparatiiPiese>> getAllReparatiiPiese(){
        List<ReparatiiPiese> reparatiiPiese = reparatiiPieseService.getAllReparatiiPiese();
        return ResponseEntity.ok(reparatiiPiese);
    }

    //metoda care cauta piesele si cantitatea de piese folosita
    @GetMapping("/get/filter")
    public ResponseEntity<List<Object[]>> getPiecesAndQuantity(){
        List<Object[]> reparatiiPiese = reparatiiPieseService.getPieseAndQuantity();
        return ResponseEntity.ok(reparatiiPiese);
    }
}
