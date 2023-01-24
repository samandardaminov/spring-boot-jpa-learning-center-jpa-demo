package uz.java.springbootjpademo.payload;

import lombok.Data;

@Data
public class ClientDto {
    private Integer id;
    private String name,phoneNumber;
}
