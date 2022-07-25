package com.huseyingurel;

import com.huseyingurel.entity.UserEntity;
import com.huseyingurel.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Log4j2
class EcodationProjectApplicationTests implements TestService{

    @Autowired
    UserRepository userRepo;

    @Override
    @Test
    public void saveUserTest() {
        UserEntity userEntity = UserEntity.builder()
                .userName("Huseyin123")
                .userSurname("Gurel1")
                .mail("huseyingurel1@gmail.com")
                .password("sifre1")
                .build();

        userRepo.save(userEntity);

        assertNotNull(userRepo.findById(1L).get());

    }

    @Override
    @Test
    public void saveUserTestFailed() {
        UserEntity userEntity = UserEntity.builder()
                .id(100)
                .userName("Huseyin123")
                .userSurname("Gurel1")
                .mail("huseyingurel1@gmail.com")
                .password("sifre1")
                .build();
//      userRepo.save(userEntity);
        assertThrows(AssertionFailedError.class, () -> assertNull(userRepo.findById(100L).get()), "Kayıt Başarısız");

    }


    @Override
    @Test
    public void getListOfUserTest() {
        List<UserEntity> usersList = userRepo.findAll();

        assertThat(usersList).size().isGreaterThan(0);

    }


    @Override
    @Test
    public void updateUserTest() {
        UserEntity userEntity = userRepo.findById(1L).get();
        userEntity.setUserName("Hüseyin1");
        userRepo.save(userEntity);

        assertNotEquals("Huseyin123",userRepo.findById(1L).get().getUserName());
    }


    @Override
    @Test
    public void deleteUserTest() {

        userRepo.deleteById(1L);
        assertThat(userRepo.existsById(1L)).isFalse();
        log.info("Kullanıcı Silindi");

    }


}
