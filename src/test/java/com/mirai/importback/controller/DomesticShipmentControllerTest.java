package com.mirai.importback.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirai.importback.controllers.DomesticShipmentController;
import com.mirai.importback.entities.DomesticShipment;
import com.mirai.importback.entities.Users;
import com.mirai.importback.services.impl.DomesticShipmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DomesticShipmentController.class)
@ActiveProfiles("test")
public class DomesticShipmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DomesticShipmentServiceImpl domesticShipmentService;
    private List<DomesticShipment> domesticShipmentList;

    @BeforeEach
    void setUp() {
        domesticShipmentList = new ArrayList<>();
        domesticShipmentList.add(new DomesticShipment(1L,
                "https://img.freepik.com/vector-gratis/dispositivo-telefono-inteligente-realista_52683-29765.jpg?size=338&ext=jpg&ga=GA1.2.459603537.1666060327",
                "Iphone 13","1","09/09/09","8","09/09/09","olva courier","1289","AWE458",
                "Chiclayo"));
        domesticShipmentList.add(new DomesticShipment(2L,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ9gaYbIm9wrfg43NwHaVCwu7A7mn3HAaj-mw&usqp=CAU",
                "Armani Perfume","3","09/09/09","12","09/09/09","olva courier","1289","ASD888",
                "Juliaca"));
        domesticShipmentList.add(new DomesticShipment(3L,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKzFknHBs52FBby5EgUUMnc96vdVt3qL5Ylw&usqp=CAU",
                "Guess Watch","2","09/09/09","7","09/09/09","olva courier","1289","QQQ965",
                "Piura"));
    }

    @Test
    void findAllDomesticShipmentsTest() throws Exception{
        given(domesticShipmentService.getAll()).willReturn(domesticShipmentList);
        mockMvc.perform(get("/api/domesticShipment")).andExpect(status().isOk());
    }

    @Test
    void findDomesticShipmentsByIdTest() throws Exception{
        Long domesticShipmentId = 1L;
        DomesticShipment domesticShipment = new DomesticShipment(1L,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ9gaYbIm9wrfg43NwHaVCwu7A7mn3HAaj-mw&usqp=CAU",
                "Armani Perfume","3","09/09/09","12","09/09/09","olva courier","1289","ASD888",
                "Juliaca");
        given(domesticShipmentService.getById(domesticShipmentId)).willReturn(Optional.of(domesticShipment));
        mockMvc.perform(get("/api/domesticShipment/{id}", domesticShipmentId)).andExpect(status().isOk());
    }

    @Test
    void insertDomesticShipmentsTest() throws Exception{
        DomesticShipment domesticShipment = new DomesticShipment(1L,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ9gaYbIm9wrfg43NwHaVCwu7A7mn3HAaj-mw&usqp=CAU",
                "Armani Perfume","3","09/09/09","12","09/09/09","olva courier","1289","ASD888",
                "Juliaca");
        mockMvc.perform(post("/api/domesticShipment")
                        .content(asJsonString(domesticShipment))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    void updateDomesticShipmentTest() throws Exception{
        Long id = 1L;
        DomesticShipment domesticShipment = new DomesticShipment(1L,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ9gaYbIm9wrfg43NwHaVCwu7A7mn3HAaj-mw&usqp=CAU",
                "Armani Perfume","3","10/10/10","12","09/09/09","olva courier","1289","ASD888",
                "Cajamarca");

        given(domesticShipmentService.getById(id)).willReturn(Optional.of(domesticShipment));
        mockMvc.perform(put("/api/domesticShipment/{id}", id)
                        .content(asJsonString(domesticShipment))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deleteDomesticShipmentTest() throws Exception{
        Long id = 1L;
        DomesticShipment domesticShipment = new DomesticShipment(1L,
                "https://img.freepik.com/vector-gratis/dispositivo-telefono-inteligente-realista_52683-29765.jpg?size=338&ext=jpg&ga=GA1.2.459603537.1666060327",
                "Iphone 13","1","09/09/09","8","09/09/09","olva courier","1289","AWE458",
                "Chiclayo");

        given(domesticShipmentService.getById(id)).willReturn(Optional.of(domesticShipment));
        mockMvc.perform(delete("/api/domesticShipment/{id}", id))
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
