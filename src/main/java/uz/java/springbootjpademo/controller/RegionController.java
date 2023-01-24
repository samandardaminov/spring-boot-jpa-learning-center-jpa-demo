package uz.java.springbootjpademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.springbootjpademo.payload.ApiResponse;
import uz.java.springbootjpademo.payload.RegionDto;
import uz.java.springbootjpademo.service.RegionService;
import uz.java.springbootjpademo.utils.ApplicationConstants;

@RestController
@RequestMapping("/api/region")
public class RegionController {
    final
    RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @PostMapping("/saveOrEditRegion")
    public HttpEntity<?> saveOrEditRegion(@RequestBody RegionDto regionDto){
        ApiResponse apiResponse = regionService.saveOrEditRegion(regionDto);
        return ResponseEntity.status(apiResponse.isSuccess()?
                        apiResponse.getMessage().equals("Saved")?201:202:209)
                .body(apiResponse);
    }

    @GetMapping("/getAllRegion")
    public HttpEntity<?> getAllRegions(@RequestParam(value = "page",
            defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER)Integer page,
                                       @RequestParam(value = "size",
                                       defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE)Integer size){
        return ResponseEntity.ok(regionService.getAllRegion(page,size));
    }

    @GetMapping("/byId/{id}")
    public HttpEntity<?> getOneRegionById(@PathVariable(value = "id")Integer id){
        ApiResponse response = regionService.getOneRegionById(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    @DeleteMapping("/deleteRegion/{id}")
    public HttpEntity<?> removeRegionById(@PathVariable(value = "id")Integer id){
        ApiResponse response = regionService.removeRegionById(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }
}
