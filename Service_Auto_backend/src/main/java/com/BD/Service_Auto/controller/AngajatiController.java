package com.BD.Service_Auto.controller;


import com.BD.Service_Auto.model.Angajati;
import com.BD.Service_Auto.service.AngajatiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/angajati")
public class AngajatiController {

    @Autowired
    private AngajatiService angajatiService;


    @RequestMapping("/add")
    public String add(@RequestBody Angajati angajati){
        angajatiService.saveAngajati(angajati);
        return "Angajatul a fost adaugat";

    }
}
