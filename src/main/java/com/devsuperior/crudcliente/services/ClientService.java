package com.devsuperior.crudcliente.services;

import com.devsuperior.crudcliente.dtos.ClientDTO;
import com.devsuperior.crudcliente.entities.Client;
import com.devsuperior.crudcliente.repositories.ClientRepository;
import com.devsuperior.crudcliente.services.exceptions.ResourceNotFound;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(ClientDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        return clientRepository
                .findById(id)
                .map(ClientDTO::new)
                .orElseThrow(() -> new ResourceNotFound("Cliente não encontrado!"));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client client = new Client();

        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setBirthDate(dto.getBirthDate());
        client.setIncome(dto.getIncome());
        client.setChildren(dto.getChildren());

        return new ClientDTO(clientRepository.save(client));
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        try {
            Client client = clientRepository.getReferenceById(id);

            client.setName(dto.getName());
            client.setCpf(dto.getCpf());
            client.setBirthDate(dto.getBirthDate());
            client.setIncome(dto.getIncome());
            client.setChildren(dto.getChildren());

            return new ClientDTO(clientRepository.save(client));
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFound("Cliente não encontrado!");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFound("Cliente não encontrado!");
        }

        clientRepository.deleteById(id);
    }
}
