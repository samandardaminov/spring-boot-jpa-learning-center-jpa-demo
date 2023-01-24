package uz.java.springbootjpademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.springbootjpademo.payload.ApiResponse;
import uz.java.springbootjpademo.payload.DistrictDto;
import uz.java.springbootjpademo.service.DistrictService;
import uz.java.springbootjpademo.utils.ApplicationConstants;

@RestController
@RequestMapping("/api/district")
public class DistrictController {

    final
    DistrictService districtService;

    @Autowired
    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @PostMapping("/saveOrEditDistrict")
    public HttpEntity<?> saveOrEditDistrict(@RequestBody DistrictDto districtDto){
        ApiResponse response = districtService.saveOrEditDistrict(districtDto);
        return ResponseEntity.status(response.isSuccess()?
                response.getMessage().equals("Saved")?201:202:409)
                .body(response);
    }

    @GetMapping("/getAllDistricts")
    public HttpEntity<?> getAllDistricts(@RequestParam(value = "page",
            defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER)Integer page,
                                         @RequestParam(value = "size",
                                         defaultValue =ApplicationConstants.DEFAULT_PAGE_SIZE)Integer size){
        return ResponseEntity.ok(districtService.getAllDistricts(page,size));
    }

    @GetMapping("/byId/{id}")
    public HttpEntity<?> getOneDistrictById(@PathVariable(value = "id")Integer id){
        ApiResponse response = districtService.getOneDistrictById(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    @DeleteMapping("/deleteDistrict/{id}")
    public HttpEntity<?> removeDistrictById(@PathVariable(value = "id")Integer id){
        ApiResponse response = districtService.removeDistrictById(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }
}
