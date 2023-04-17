package com.mirai.importback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirai.importback.controllers.DirectionsController;
import com.mirai.importback.controllers.UsersController;
import com.mirai.importback.entities.Directions;
import com.mirai.importback.entities.Users;
import com.mirai.importback.services.impl.DirectionsServiceImpl;
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

@WebMvcTest(controllers = DirectionsController.class)
@ActiveProfiles("test")
public class DirectionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DirectionsServiceImpl directionsService;
    private List<Directions> directionsList;

    @BeforeEach
    void setUp() {
        directionsList = new ArrayList<>();
        directionsList.add(new Directions(1L, "281423813", "Alejandro", "Ramirez", "Calle Los Jardines, Av. Universitaria", "992331554", "Los Olivos", "Lima", "Lima"));
        directionsList.add(new Directions(1L, "281421555", "Manuel", "Ramirez", "Calle Los Jardines, Av. Universitaria", "992331554", "Los Olivos", "Lima", "Lima"));
        directionsList.add(new Directions(1L, "281423777", "Martin", "Ramirez", "Calle Los Jardines, Av. Universitaria", "992331554", "Los Olivos", "Lima", "Lima"));
    }

    @Test
    void findAllDirections() throws Exception {
        given(directionsService.getAll()).willReturn(directionsList);
        mockMvc.perform(get("/api/directions")).andExpect(status().isOk());
    }

    @Test
    void findDirectionByIdTest() throws Exception {
        Long directionId = 1L;
        Directions direction = new Directions(1L, "281423813", "Alejandro", "Ramirez", "Calle Los Jardines, Av. Universitaria", "992331554", "Los Olivos", "Lima", "Lima");

        given(directionsService.getById(directionId)).willReturn(Optional.of(direction));
        mockMvc.perform(get("/api/directions/{id}", directionId)).andExpect(status().isOk());
    }
}
