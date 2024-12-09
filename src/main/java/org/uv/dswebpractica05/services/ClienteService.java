package org.uv.dswebpractica05.services;

import java.util.List;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uv.dswebpractica05.dtos.ClienteRegisterDTO;
import org.uv.dswebpractica05.models.Cliente;
import org.uv.dswebpractica05.repositories.ClienteRepository;
import org.uv.dswebpractica05.utils.ResourceNotFoundException;


@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Transactional
    public Cliente crearCliente(ClienteRegisterDTO clienteDTO) {
        if (clienteRepository.findByCorreo(clienteDTO.getCorreo()).isPresent()) {
            throw new ValidationException("El correo ya está en uso.");
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setCorreo(clienteDTO.getCorreo());

        return clienteRepository.save(cliente);
    }

    @Transactional
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Transactional
    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con el ID: " + id));
    }

    @Transactional
    public Cliente actualizarCliente(Long id, ClienteRegisterDTO clienteDTO) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con el ID: " + id));

        if (!clienteExistente.getCorreo().equals(clienteDTO.getCorreo()) &&
            clienteRepository.findByCorreo(clienteDTO.getCorreo()).isPresent()) {
            throw new ValidationException("El correo ya está en uso.");
        }

        clienteExistente.setNombre(clienteDTO.getNombre());
        clienteExistente.setDireccion(clienteDTO.getDireccion());
        clienteExistente.setCorreo(clienteDTO.getCorreo());

        return clienteRepository.save(clienteExistente);
    }

    @Transactional
    public void eliminarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado con el ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
