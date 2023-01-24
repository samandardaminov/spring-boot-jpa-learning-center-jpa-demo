package uz.java.springbootjpademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.springbootjpademo.payload.ApiResponse;
import uz.java.springbootjpademo.payload.ClientDto;
import uz.java.springbootjpademo.service.ClientService;
import uz.java.springbootjpademo.utils.ApplicationConstants;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    final
    ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/saveOrEdit")
    public HttpEntity<?> saveOrEditClient(@RequestBody ClientDto clientDto) {
        ApiResponse apiResponse = clientService.saveOrEditClient(clientDto);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                        apiResponse.getMessage().equals("Saved") ? 201 : 202 : 409)
                .body(apiResponse);
    }
    @GetMapping("/getAllClients")
    public HttpEntity<?> getAllClients(@RequestParam(value = "page",
            defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER) Integer page,
                                        @RequestParam(value = "size",
                                                defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE) Integer size) {
        return ResponseEntity.ok(clientService.getAllClients(page,size));
    }
    @GetMapping("/byId/{id}")
    public HttpEntity<?> getOneClientById(@PathVariable(value = "id")Integer id){
        ApiResponse response = clientService.getOneStudentById(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> removeClientById(@PathVariable(value = "id")Integer id){
        ApiResponse response = clientService.removeById(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }
}
