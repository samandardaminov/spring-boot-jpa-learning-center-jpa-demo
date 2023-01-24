package uz.java.springbootjpademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.java.springbootjpademo.entity.Client;

public interface ClientRepository extends JpaRepository<Client,Integer> {
}
