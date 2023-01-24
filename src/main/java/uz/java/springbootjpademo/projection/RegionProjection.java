package uz.java.springbootjpademo.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.java.springbootjpademo.entity.Region;

@Projection(name = "regionProjection",types = Region.class)
public interface RegionProjection {
    Integer getId();
    String getName();

//    @Value("#{@districtRepository.allByRegionId(target.id)}")
//    List<District> getAllByRegionId();
}
