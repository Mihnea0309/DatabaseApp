package com.BD.Service_Auto.controller;


import com.BD.Service_Auto.model.ReparatiiServicii;
import com.BD.Service_Auto.service.ReparatiiServiciiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//clasa care realizeaza legatura cu interfata prin cererile HTTP
@RestController
@RequestMapping("/reparatii_servicii")
public class ReparatiiServiciiController {

    @Autowired
    private ReparatiiServiciiService reparatiiServiciiService;

    //metoda care cauta in tabela reparatiiservicii
    @GetMapping("/get")
    public ResponseEntity<List<ReparatiiServicii>> getAllReparatiiServicii(){
        List<ReparatiiServicii> reparatiiServicii= reparatiiServiciiService.getAllReparatiiServicii();
        return ResponseEntity.ok(reparatiiServicii);
    }

    //metoda care cauta reparatiile si serviciile efectuate unei masini
    @GetMapping("/get/filter")
    public ResponseEntity<List<Object[]>> getRepairsAndServices(){
        List<Object[]> result = reparatiiServiciiService.getRepairsAndServices();
        return ResponseEntity.ok(result);
    }
}
