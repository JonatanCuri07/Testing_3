package com.mirai.importback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirai.importback.controllers.ProductListController;
import com.mirai.importback.controllers.StoreProductsController;
import com.mirai.importback.entities.ProductList;
import com.mirai.importback.entities.StoreProducts;
import com.mirai.importback.services.impl.ProductListImpl;
import com.mirai.importback.services.impl.StoreProductsImpl;
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


@WebMvcTest(controllers = StoreProductsController.class)
@ActiveProfiles("test")
public class StoreProductsControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreProductsImpl storeProductsService;
    private List<StoreProducts> storeProductsList;

    @BeforeEach
    void setUp(){
        storeProductsList = new ArrayList<>();
        storeProductsList.add(new StoreProducts(1L,"Technology",List.of()));
        storeProductsList.add(new StoreProducts(2L,"CLOTHING",List.of()));
        storeProductsList.add(new StoreProducts(3L,"MUSEUM",List.of()));
        storeProductsList.add(new StoreProducts(4L,"HOME",List.of()));
        storeProductsList.add(new StoreProducts(5L,"KIDS",List.of()));
    }

    @Test
    void findAllStoreProductsTest() throws Exception{
        given(storeProductsService.getAll()).willReturn(storeProductsList);
        mockMvc.perform(get("/api/storeProducts")).andExpect(status().isOk());
    }

    @Test
    void findStoreProductsByIdTest() throws Exception{
        Long storeProductsId = 1L;
        StoreProducts storeProducts = new StoreProducts(5L,"KIDS",List.of());

        given(storeProductsService.getById(storeProductsId)).willReturn(Optional.of(storeProducts));
        mockMvc.perform(get("/api/storeProducts/{id}", storeProductsId)).andExpect(status().isOk());
    }

    @Test
    void insertStoreProductsTest() throws Exception{
        StoreProducts storeProducts = new StoreProducts(5L,"KIDS",List.of());

        mockMvc.perform(post("/api/storeProducts")
                        .content(asJsonString(storeProducts))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    void updateCustomerTest() throws Exception{
        Long id = 1L;
        StoreProducts storeProducts = new StoreProducts(5L,"KIDS",List.of());

        given(storeProductsService.getById(id)).willReturn(Optional.of(storeProducts));
        mockMvc.perform(put("/api/storeProducts/{id}", id)
                        .content(asJsonString(storeProducts))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCustomerTest() throws Exception{
        Long id = 1L;
        StoreProducts storeProducts = new StoreProducts(1L,"Rafael",List.of());

        given(storeProductsService.getById(id)).willReturn(Optional.of(storeProducts));
        mockMvc.perform(delete("/api/storeProducts/{id}", id))
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
