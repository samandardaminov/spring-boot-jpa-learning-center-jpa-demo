package uz.java.springbootjpademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.java.springbootjpademo.entity.Region;

//@RepositoryRestResource(path = "region",collectionResourceRel = "list",excerptProjection = RegionProjection.class)
public interface RegionRepository extends JpaRepository<Region, Integer> {
}
