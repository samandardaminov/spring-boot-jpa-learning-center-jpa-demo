package uz.java.springbootjpademo.payload;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Data
public class RegionDto {
    private Integer id;
    private String name;

    @Value("#{@districtRepository.allByRegionId(target.id)}")
    private List<DistrictDto> districtDtoList;
}
