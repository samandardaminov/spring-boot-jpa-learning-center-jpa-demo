package uz.java.springbootjpademo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.java.springbootjpademo.entity.Region;
import uz.java.springbootjpademo.payload.ApiResponse;
import uz.java.springbootjpademo.payload.RegionDto;
import uz.java.springbootjpademo.repository.DistrictRepository;
import uz.java.springbootjpademo.repository.RegionRepository;
import uz.java.springbootjpademo.utils.CommonUtils;

import java.util.stream.Collectors;

@Service
public class RegionService {

    final
    RegionRepository regionRepository;
    final
    DistrictRepository districtRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository, DistrictRepository districtRepository) {
        this.regionRepository = regionRepository;
        this.districtRepository = districtRepository;
    }

    public RegionDto getRegionDtoFromRegion(Region region){
        RegionDto regionDto=new RegionDto();
        regionDto.setId(region.getId());
        regionDto.setName(region.getName());
        return regionDto;
    }
    public ApiResponse saveOrEditRegion(RegionDto regionDto) {
        try {
            Region region=new Region();
            if (regionDto.getId()!=null){
                region=regionRepository.findById(regionDto.getId())
                        .orElseThrow(() -> new IllegalStateException("Region with this Id not found"));
            }
            region.setName(regionDto.getName());
            regionRepository.save(region);
            return new ApiResponse(regionDto.getId()!=null?"Edited":"Saved",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    public ApiResponse getAllRegion(Integer page, Integer size) {
        Page<Region> regionPage = regionRepository.findAll(CommonUtils.getPageableByNameAsc(page, size));

        return new ApiResponse("Success",
                true,
                regionPage.getTotalElements(),
                regionPage.getTotalPages(),
                regionPage.getContent().stream().map(this::getRegionDtoFromRegion).collect(Collectors.toList()));
    }

    public ApiResponse getOneRegionById(Integer id) {
        try {
            Region region = regionRepository.findById(id).orElseThrow(() -> new IllegalStateException("Region with this Id not found"));
            return new ApiResponse("Student found",
                    true,
                    getRegionDtoFromRegion(region));
        }catch (Exception e){
            return new ApiResponse("Region with this Id not found",false);
        }
    }

    public ApiResponse removeRegionById(Integer id) {
        try {
            regionRepository.deleteById(id);
            return new ApiResponse("Deleted",true);
        }catch (Exception e){
            return new ApiResponse("Error in deleting process",false);
        }
    }
}
