package com.huseyingurel.service;

import com.huseyingurel.dto.UserDto;
import com.huseyingurel.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    //ModelMapper Entity-Dto Dönüşümü
    UserDto EntityToDto(UserEntity userEntity);
    UserEntity DtoToEntity(UserDto userDto);

    //Kullanıcı Kaydı Oluşturma
    UserDto saveUser(UserDto userDto);

    //Kayıtlı Kullanıcıları Listeleme
    List<UserDto> getAllUsers();

    //Kullanıcı Güncelleme
    ResponseEntity<UserDto> updateUser(Long id, UserDto userDto);

    //Kayıtlı Kullanıcıyı ID ye göre Silme
    ResponseEntity<Map<String,Boolean>>deleteUser(Long id);

}
