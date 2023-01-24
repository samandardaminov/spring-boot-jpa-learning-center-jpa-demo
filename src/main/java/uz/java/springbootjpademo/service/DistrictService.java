package uz.java.springbootjpademo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.java.springbootjpademo.entity.District;
import uz.java.springbootjpademo.payload.ApiResponse;
import uz.java.springbootjpademo.payload.DistrictDto;
import uz.java.springbootjpademo.repository.DistrictRepository;
import uz.java.springbootjpademo.repository.RegionRepository;
import uz.java.springbootjpademo.utils.CommonUtils;

import java.util.stream.Collectors;

@Service
public class DistrictService {

    final
    DistrictRepository districtRepository;

    final
    RegionRepository regionRepository;


    @Autowired
    public DistrictService(DistrictRepository districtRepository, RegionRepository regionRepository) {
        this.districtRepository = districtRepository;
        this.regionRepository = regionRepository;
    }

    public DistrictDto getDistrictDtoFromDistrict(District district){
        DistrictDto dto=new DistrictDto();
        dto.setId(district.getId());
        dto.setName(district.getName());
        dto.setRegionId(districtRepository.findRegionIdByRegion(district.getId()));
        return dto;
    }

    public ApiResponse saveOrEditDistrict(DistrictDto districtDto) {
        try {
            District district=new District();
            if (districtDto.getId()!=null){
                district=districtRepository.findById(districtDto.getId())
                        .orElseThrow(() -> new IllegalStateException("District with this Id not found"));
            }
            if (districtDto.getRegionId()!=null){
                district.setRegion(regionRepository.findById(districtDto.getRegionId()).
                        orElseThrow(() -> new IllegalStateException("Region not found")));
            }
            district.setName(districtDto.getName());
            districtRepository.save(district);
            return new ApiResponse(districtDto.getId()!=null?"Edited":"Saved",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    public ApiResponse getAllDistricts(Integer page, Integer size) {
        Page<District> districtPage = districtRepository.findAll(CommonUtils.getPageableByNameAsc(page, size));
        return new ApiResponse("Success",
                true,
                districtPage.getTotalElements(),
                districtPage.getTotalPages(),
                districtPage.getContent().stream().map(this::getDistrictDtoFromDistrict).collect(Collectors.toList()));
    }

    public ApiResponse getOneDistrictById(Integer id) {
        try {
            District district = districtRepository.findById(id).orElseThrow(() -> new IllegalStateException("District with this Id not found"));
            return new ApiResponse("Student found",
                    true,
                    getDistrictDtoFromDistrict(district));
        }catch (Exception e){
            return new ApiResponse("District with this Id not found",false);
        }
    }

    public ApiResponse removeDistrictById(Integer id) {
        try {
            districtRepository.deleteById(id);
            return new ApiResponse("Deleted",true);
        }catch (Exception e){
            return new ApiResponse("Error in deleting process",false);
        }
    }
}
