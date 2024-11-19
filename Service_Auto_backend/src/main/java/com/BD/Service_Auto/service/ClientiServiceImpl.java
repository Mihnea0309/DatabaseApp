package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.Clienti;
import com.BD.Service_Auto.repository.ClientiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientiServiceImpl implements ClientiService{

    @Autowired
    private ClientiRepository clientiRepository;
    @Override
    @Transactional
    public Clienti saveClienti(Clienti clienti) {
        System.out.println("Date primite: " + clienti);
        Clienti savedClient = clientiRepository.save(clienti);
        System.out.println("Date salvate: " + savedClient);
        return savedClient;
    }

    @Override
    public List<Clienti> getAllClients() {
        return clientiRepository.findAll();
    }
}
