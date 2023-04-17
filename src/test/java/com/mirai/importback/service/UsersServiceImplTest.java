package com.mirai.importback.service;

import com.mirai.importback.entities.Users;
import com.mirai.importback.repositories.IUsersRepository;
import com.mirai.importback.services.impl.UsersServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UsersServiceImplTest {

    @Mock
    private IUsersRepository usersRepository;

    @InjectMocks
    private UsersServiceImpl usersService;

    @Test
    public void saveTest(){
        Users users = new Users(1L,"Rafael","20","23232323",
                "12345678","male","20","11","2000","rafa@gmail.com","999888777","Av. Trujillo");

        given(usersRepository.save(users)).willReturn(users);

        Users savedUser = null;
        try{
            savedUser = usersService.save(users);
        }catch (Exception e){
            e.printStackTrace();
        }
        assertThat(savedUser).isNotNull();
        assertEquals(users, savedUser);
    }

    @Test
    public void deleteTest() throws Exception{
        Long id = 1L;
        usersService.delete(id);
        verify(usersRepository, times(1)).deleteById(id);
    }

    @Test
    public void getAllTest() throws Exception{
        List<Users> list = new ArrayList<>();
        list.add(new Users(1L,"Rafael","20","23232323",
                "12345678","male","20","11","2000","rafa@gmail.com","999888777","Av. Trujillo"));
        list.add(new Users(2L,"Edgar Malca","20","23232323",
                "12345678","male","20","11","2000","malca@gmail.com","999888777","Av. Trujillo"));
        list.add(new Users(3L,"Gonzalo","20","23232323",
                "12345678","male","20","11","2000","gonza@gmail.com","999888777","Av. Trujillo"));
        list.add(new Users(4L,"Edgar Anco","20","23232323",
                "12345678","male","20","11","2000","anco@gmail.com","999888777","Av. Trujillo"));
        list.add(new Users(5L,"Sebastian","20","23232323",
                "12345678","male","20","11","2000","sebas@gmail.com","999888777","Av. Trujillo"));

        given(usersRepository.findAll()).willReturn(list);
        List<Users> listExpected = usersService.getAll();
        assertEquals(listExpected, list);
    }

    @Test
    public void getByIdTest() throws Exception{
        Long id = 1L;
        Users customer = new Users(1L,"Rafael","20","23232323",
                "12345678","male","20","11","2000","rafa@gmail.com","999888777","Av. Trujillo");

        given(usersRepository.findById(id)).willReturn(Optional.of(customer));
        Optional<Users> customerExpected = usersService.getById(id);
        assertThat(customerExpected).isNotNull();
        assertEquals(customerExpected, Optional.of(customer));
    }



}
