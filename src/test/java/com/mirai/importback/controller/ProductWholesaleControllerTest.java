package com.mirai.importback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirai.importback.controllers.ProductWholesaleController;
import com.mirai.importback.controllers.UsersController;
import com.mirai.importback.entities.ProductWholesale;
import com.mirai.importback.entities.Users;
import com.mirai.importback.services.impl.ProductWholesaleServiceImpl;
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

@WebMvcTest(controllers = ProductWholesaleController.class)
@ActiveProfiles("test")
public class ProductWholesaleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductWholesaleServiceImpl productWholesaleService;
    private List<ProductWholesale> productWholesaleList;

    @BeforeEach
    void setUp(){
        productWholesaleList = new ArrayList<>();
        productWholesaleList.add(new ProductWholesale(1L,"Iphone 1","20","350",
                "https://i.blogs.es/35ef0d/iphone-12-pro-max-00-01/1366_2000.jpg",
                "Is an Iphone", "24 horas de batería",
                "Medium size","164GB of storage"));
        productWholesaleList.add(new ProductWholesale(2L,"Iphone 2","20","350",
                "https://i.blogs.es/35ef0d/iphone-12-pro-max-00-01/1366_2000.jpg",
                "Is an Iphone", "24 horas de batería",
                "Medium size","164GB of storage"));
        productWholesaleList.add(new ProductWholesale(3L,"Iphone 3","20","350",
                "https://i.blogs.es/35ef0d/iphone-12-pro-max-00-01/1366_2000.jpg",
                "Is an Iphone", "24 horas de batería",
                "Medium size","164GB of storage"));
        productWholesaleList.add(new ProductWholesale(4L,"Iphone 4","20","350",
                "https://i.blogs.es/35ef0d/iphone-12-pro-max-00-01/1366_2000.jpg",
                "Is an Iphone", "24 horas de batería",
                "Medium size","164GB of storage"));
        productWholesaleList.add(new ProductWholesale(5L,"Iphone 5","20","350",
                "https://i.blogs.es/35ef0d/iphone-12-pro-max-00-01/1366_2000.jpg",
                "Is an Iphone", "24 horas de batería",
                "Medium size","164GB of storage"));

    }

    @Test
    void findAllProductsWholesaleTest() throws Exception{
        given(productWholesaleService.getAll()).willReturn(productWholesaleList);
        mockMvc.perform(get("/api/productWholesale")).andExpect(status().isOk());
    }

    @Test
    void findProductWholesaleByIdTest() throws Exception{
        Long productWholesaleId = 1L;
        ProductWholesale productWholesale = new ProductWholesale(1L,"Iphone 1","20","350",
                "https://i.blogs.es/35ef0d/iphone-12-pro-max-00-01/1366_2000.jpg",
                "Is an Iphone", "24 horas de batería",
                "Medium size","164GB of storage");

        given(productWholesaleService.getById(productWholesaleId)).willReturn(Optional.of(productWholesale));
        mockMvc.perform(get("/api/productWholesale/{id}", productWholesaleId)).andExpect(status().isOk());
    }

    @Test
    void insertUsersTest() throws Exception{
        ProductWholesale productWholesale = new ProductWholesale(1L,"Iphone 1","20","350",
                "https://i.blogs.es/35ef0d/iphone-12-pro-max-00-01/1366_2000.jpg",
                "Is an Iphone", "24 horas de batería",
                "Medium size","164GB of storage");

        mockMvc.perform(post("/api/productWholesale")
                        .content(asJsonString(productWholesale))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    void updateCustomerTest() throws Exception{
        Long id = 1L;
        ProductWholesale productWholesale = new ProductWholesale(1L,"Iphone 20","20","350",
                "https://i.blogs.es/35ef0d/iphone-12-pro-max-00-01/1366_2000.jpg",
                "Is an Iphone 20", "36 horas de batería",
                "Medium size","164GB of storage");

        given(productWholesaleService.getById(id)).willReturn(Optional.of(productWholesale));
        mockMvc.perform(put("/api/productWholesale/{id}", id)
                        .content(asJsonString(productWholesale))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCustomerTest() throws Exception{
        Long id = 1L;
        ProductWholesale productWholesale = new ProductWholesale(1L,"Iphone 20","20","350",
                "https://i.blogs.es/35ef0d/iphone-12-pro-max-00-01/1366_2000.jpg",
                "Is an Iphone 20", "36 horas de batería",
                "Medium size","164GB of storage");

        given(productWholesaleService.getById(id)).willReturn(Optional.of(productWholesale));
        mockMvc.perform(delete("/api/productWholesale/{id}", id))
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
