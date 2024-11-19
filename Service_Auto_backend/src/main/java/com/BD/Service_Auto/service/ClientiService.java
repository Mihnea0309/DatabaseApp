package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.Clienti;

import java.util.List;

public interface ClientiService {

    public Clienti saveClienti(Clienti clienti);

    public List<Clienti> getAllClients();
}
