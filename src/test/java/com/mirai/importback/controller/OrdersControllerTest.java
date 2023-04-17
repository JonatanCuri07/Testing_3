package com.mirai.importback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirai.importback.controllers.OrdersController;
import com.mirai.importback.entities.Orders;
import com.mirai.importback.services.impl.OrdersServiceImpl;
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

@WebMvcTest(controllers = OrdersController.class)
@ActiveProfiles("test")
public class OrdersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrdersServiceImpl ordersService;
    private List<Orders> ordersList;

    @BeforeEach
    void setUp(){
        ordersList = new ArrayList<>();
        ordersList.add(new Orders(1L,"71234567", "Sebastian",
                "https://www.amazon.com/-/es/Apple-iPhone-12-64GB-azul/dp/B09JFTPQY1/ref=sr_1_1?keywords=iphone+12&qid=1667616243&qu=eyJxc2MiOiI1LjIzIiwicXNhIjoiNS4wMyIsInFzcCI6IjQuNjEifQ%3D%3D&sr=8-1",
                "Please buy an iPhone 12", "1000", "1", "1", "new", "100"));
        ordersList.add(new Orders(1L,"71234568", "Edgar",
                "https://www.amazon.com/-/es/Apple-iPhone-12-64GB-azul/dp/B09JFTPQY1/ref=sr_1_1?keywords=iphone+12&qid=1667616243&qu=eyJxc2MiOiI1LjIzIiwicXNhIjoiNS4wMyIsInFzcCI6IjQuNjEifQ%3D%3D&sr=8-1",
                "Please buy an iPhone 12", "1000", "1", "1", "new", "100"));
        ordersList.add(new Orders(1L,"71234569", "Andre",
                "https://www.amazon.com/-/es/Apple-iPhone-12-64GB-azul/dp/B09JFTPQY1/ref=sr_1_1?keywords=iphone+12&qid=1667616243&qu=eyJxc2MiOiI1LjIzIiwicXNhIjoiNS4wMyIsInFzcCI6IjQuNjEifQ%3D%3D&sr=8-1",
                "Please buy an iPhone 12", "1000", "1", "1", "new", "100"));
        ordersList.add(new Orders(1L,"71234570", "Rafael",
                "https://www.amazon.com/-/es/Apple-iPhone-12-64GB-azul/dp/B09JFTPQY1/ref=sr_1_1?keywords=iphone+12&qid=1667616243&qu=eyJxc2MiOiI1LjIzIiwicXNhIjoiNS4wMyIsInFzcCI6IjQuNjEifQ%3D%3D&sr=8-1",
                "Please buy an iPhone 12", "1000", "1", "1", "new", "100"));
        ordersList.add(new Orders(1L,"71234571", "Gonzalo",
                "https://www.amazon.com/-/es/Apple-iPhone-12-64GB-azul/dp/B09JFTPQY1/ref=sr_1_1?keywords=iphone+12&qid=1667616243&qu=eyJxc2MiOiI1LjIzIiwicXNhIjoiNS4wMyIsInFzcCI6IjQuNjEifQ%3D%3D&sr=8-1",
                "Please buy an iPhone 12", "1000", "1", "1", "new", "100"));
    }

    @Test
    void findAllOrdersTest() throws Exception{
        given(ordersService.getAll()).willReturn(ordersList);
        mockMvc.perform(get("/api/orders")).andExpect(status().isOk());
    }

    @Test
    void findOrdersByIdTest() throws Exception{
        Long orderId = 1L;
        Orders order = new Orders(1L,"71234567", "Sebastian",
                "https://www.amazon.com/-/es/Apple-iPhone-12-64GB-azul/dp/B09JFTPQY1/ref=sr_1_1?keywords=iphone+12&qid=1667616243&qu=eyJxc2MiOiI1LjIzIiwicXNhIjoiNS4wMyIsInFzcCI6IjQuNjEifQ%3D%3D&sr=8-1",
                "Please buy an iPhone 12", "1000", "1", "1", "new", "100");

        given(ordersService.getById(orderId)).willReturn(Optional.of(order));
        mockMvc.perform(get("/api/orders/{id}", orderId)).andExpect(status().isOk());
    }

    @Test
    void insertOrdersTest() throws Exception{
        Orders order = new Orders(1L,"71234567", "Sebastian",
                "https://www.amazon.com/-/es/Apple-iPhone-12-64GB-azul/dp/B09JFTPQY1/ref=sr_1_1?keywords=iphone+12&qid=1667616243&qu=eyJxc2MiOiI1LjIzIiwicXNhIjoiNS4wMyIsInFzcCI6IjQuNjEifQ%3D%3D&sr=8-1",
                "Please buy an iPhone 12", "1000", "1", "1", "new", "100");

        mockMvc.perform(post("/api/orders")
                        .content(asJsonString(order))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    void updateOrderTest() throws Exception{
        Long id = 1L;
        Orders order = new Orders(1L,"71234567", "Sebastian",
                "https://www.amazon.com/-/es/Apple-iPhone-12-64GB-azul/dp/B09JFTPQY1/ref=sr_1_1?keywords=iphone+12&qid=1667616243&qu=eyJxc2MiOiI1LjIzIiwicXNhIjoiNS4wMyIsInFzcCI6IjQuNjEifQ%3D%3D&sr=8-1",
                "Please buy 2 iPhone 12", "1000", "1", "2", "new", "100");

        given(ordersService.getById(id)).willReturn(Optional.of(order));
        mockMvc.perform(put("/api/orders/{id}", id)
                        .content(asJsonString(order))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deleteOrderTest() throws Exception{
        Long id = 1L;
        Orders order = new Orders(1L,"71234567", "Sebastian",
                "https://www.amazon.com/-/es/Apple-iPhone-12-64GB-azul/dp/B09JFTPQY1/ref=sr_1_1?keywords=iphone+12&qid=1667616243&qu=eyJxc2MiOiI1LjIzIiwicXNhIjoiNS4wMyIsInFzcCI6IjQuNjEifQ%3D%3D&sr=8-1",
                "Please buy 2 iPhone 12", "1000", "1", "2", "new", "100");

        given(ordersService.getById(id)).willReturn(Optional.of(order));
        mockMvc.perform(delete("/api/orders/{id}", id))
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
