package com.mirai.importback.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirai.importback.controllers.UsersController;
import com.mirai.importback.entities.Users;
import com.mirai.importback.services.impl.UsersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(controllers = UsersController.class)
@ActiveProfiles("test")
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersServiceImpl usersService;
    private List<Users> usersList;

    @BeforeEach
    void setUp(){
        usersList = new ArrayList<>();
        usersList.add(new Users(1L,"Rafael","20","23232323",
                "12345678","male","20","11","2000","rafa@gmail.com","999888777","Av. Trujillo"));
        usersList.add(new Users(2L,"Edgar Malca","20","23232323",
                "12345678","male","20","11","2000","malca@gmail.com","999888777","Av. Trujillo"));
        usersList.add(new Users(3L,"Gonzalo","20","23232323",
                "12345678","male","20","11","2000","gonza@gmail.com","999888777","Av. Trujillo"));
        usersList.add(new Users(4L,"Edgar Anco","20","23232323",
                "12345678","male","20","11","2000","anco@gmail.com","999888777","Av. Trujillo"));
        usersList.add(new Users(5L,"Sebastian","20","23232323",
                "12345678","male","20","11","2000","sebas@gmail.com","999888777","Av. Trujillo"));
    }

    @Test
    void findAllUsersTest() throws Exception{
        given(usersService.getAll()).willReturn(usersList);
        mockMvc.perform(get("/api/users")).andExpect(status().isOk());
    }

    @Test
    void findUsersByIdTest() throws Exception{
        Long userId = 1L;
        Users user = new Users(1L,"Rafael","20","23232323",
                "12345678","male","20","11","2000","rafa@gmail.com","999888777","Av. Trujillo");

        given(usersService.getById(userId)).willReturn(Optional.of(user));
        mockMvc.perform(get("/api/users/{id}", userId)).andExpect(status().isOk());
    }

    @Test
    void insertUsersTest() throws Exception{
        Users user = new Users(1L,"Rafael","20","23232323",
                "12345678","male","20","11","2000","rafa@gmail.com","999888777","Av. Trujillo");

        mockMvc.perform(post("/api/users")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    void updateCustomerTest() throws Exception{
        Long id = 1L;
        Users user = new Users(1L,"Rafael","21","23232323",
                "12345678","male","20","11","2000","rafa@gmail.com","999888777","Av. Imperial");

        given(usersService.getById(id)).willReturn(Optional.of(user));
        mockMvc.perform(put("/api/users/{id}", id)
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCustomerTest() throws Exception{
        Long id = 1L;
        Users user = new Users(1L,"Rafael","21","23232323",
                "12345678","male","20","11","2000","rafa@gmail.com","999888777","Av. Imperial");

        given(usersService.getById(id)).willReturn(Optional.of(user));
        mockMvc.perform(delete("/api/users/{id}", id))
                .andExpect(status().isOk());
    }
    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
