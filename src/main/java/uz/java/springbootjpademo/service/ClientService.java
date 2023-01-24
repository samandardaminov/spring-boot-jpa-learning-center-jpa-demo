package uz.java.springbootjpademo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.java.springbootjpademo.entity.Client;
import uz.java.springbootjpademo.payload.ApiResponse;
import uz.java.springbootjpademo.payload.ClientDto;
import uz.java.springbootjpademo.repository.ClientRepository;
import uz.java.springbootjpademo.utils.CommonUtils;

import java.util.stream.Collectors;

@Service
public class ClientService {

    final
    ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDto getClientDtoFromClient(Client client){
        ClientDto clientDto=new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setPhoneNumber(client.getPhoneNumber());
        return clientDto;
    }

    public ApiResponse saveOrEditClient(ClientDto clientDto) {
        try {
            Client client=new Client();
            if (clientDto.getId()!=null){
                client=clientRepository.findById(clientDto.getId())
                        .orElseThrow(() -> new IllegalStateException("Client with this id not found"));
            }
            client.setName(clientDto.getName());
            client.setPhoneNumber(clientDto.getPhoneNumber());
            clientRepository.save(client);
            return new ApiResponse(clientDto.getId()!=null?"Edited":"Saved",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    public ApiResponse getAllClients(Integer page, Integer size) {
        Page<Client> clientPage = clientRepository.findAll(CommonUtils.getPageableByNameAsc(page, size));
        return new ApiResponse("Success",
                true,
                clientPage.getTotalElements(),
                clientPage.getTotalPages(),
                clientPage.getContent().stream().map(this::getClientDtoFromClient).collect(Collectors.toList()));
    }

    public ApiResponse getOneStudentById(Integer id) {
        try{
            Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalStateException("Client with this Id not found"));
            return new ApiResponse("Client found",
                    true,
                    getClientDtoFromClient(client));
        }catch (Exception e){
            return new ApiResponse("Client with this Id not found",false);
        }
    }

    public ApiResponse removeById(Integer id) {
        try {
            clientRepository.deleteById(id);
            return new ApiResponse("Deleted",true);
        }catch (Exception e){
            return new ApiResponse("Error in deleting process",false);
        }

    }
}
