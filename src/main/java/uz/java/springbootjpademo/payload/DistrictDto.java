package uz.java.springbootjpademo.payload;

import lombok.Data;

@Data
public class DistrictDto {
    private Integer id;
    private String name;
    private Integer regionId;
}
