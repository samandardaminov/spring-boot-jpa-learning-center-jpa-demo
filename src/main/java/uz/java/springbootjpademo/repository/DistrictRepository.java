package uz.java.springbootjpademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.java.springbootjpademo.entity.District;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District,Integer> {

//    @Query(value = "select * from district where region_id=:regionId",nativeQuery = true)
//    List<DistrictDto> getAllByRegionId(Integer regionId);
    @Query(value = "select * from district where region_id=:regId",nativeQuery = true)
    List<District> allByRegionId(@Param(value = "regId") Integer regionId);

    @Query(value = "select region_id from District where district.id=:id",nativeQuery = true)
    Integer findRegionIdByRegion(@Param(value = "id")Integer districtId);
}
