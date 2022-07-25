package com.huseyingurel.service.impl;

import com.huseyingurel.dto.UserDto;
import com.huseyingurel.entity.UserEntity;
import com.huseyingurel.exception.ResourceNotFoundException;
import com.huseyingurel.repository.UserRepository;
import com.huseyingurel.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDto EntityToDto(UserEntity userEntity) {
        UserDto userDto = modelMapper.map(userEntity,UserDto.class);
        return userDto;
    }

    @Override
    public UserEntity DtoToEntity(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto,UserEntity.class);
        return userEntity;
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        UserEntity userEntity = DtoToEntity(userDto);
        userRepo.save(userEntity);
        log.info("Kullanıcı Eklendi");
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> userList = new ArrayList<>();
        Iterable<UserEntity> users = userRepo.findAll();

        for(UserEntity userEntity : users) {
            UserDto userDto = EntityToDto(userEntity);
            userList.add(userDto);
        }
        return userList;
    }



    @Override
    public ResponseEntity<UserDto> updateUser(@PathVariable(name="id") Long id, @RequestBody UserDto userDto) {
        //DtoToEntity
        UserEntity userEntity =DtoToEntity(userDto);
        UserEntity userFind=userRepo.findById(id)
                .orElseThrow( ()->new ResourceNotFoundException("Aranan ID: "+id+ "   Bu ID ye sahip bir kullanıcı bulunamadı"));

        //Var Olan Kullanıcının Bilgilerini Güncelleme
        userFind.setUserName(userEntity.getUserName());
        userFind.setUserSurname(userEntity.getUserSurname());
        userFind.setMail(userEntity.getMail());
        userFind.setPassword(userEntity.getPassword());

        UserEntity userEntity2=  userRepo.save(userFind);

        //EntityToDto
        UserDto userDto2=  EntityToDto(userEntity2);
        return ResponseEntity.ok(userDto2);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable(name="id") Long id) {
        UserEntity userEntity=userRepo.findById(id)
                .orElseThrow( ()->new ResourceNotFoundException("Aranan ID: "+id+ "   Bu ID ye sahip bir kullanıcı bulunamadı"));

        userRepo.delete(userEntity);
        Map<String,Boolean> response=new HashMap<>();
        response.put("silindi",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
