package com.mirai.importback.service;

import com.mirai.importback.entities.Orders;
import com.mirai.importback.repositories.IOrdersRepository;
import com.mirai.importback.services.impl.OrdersServiceImpl;
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
public class OrdersServiceImplTest {

    @Mock
    private IOrdersRepository ordersRepository;

    @InjectMocks
    private OrdersServiceImpl ordersService;

    @Test
    public void saveTest(){
        Orders orders = new Orders(1L,"71234567", "Sebastian",
                "https://www.amazon.com/-/es/Apple-iPhone-12-64GB-azul/dp/B09JFTPQY1/ref=sr_1_1?keywords=iphone+12&qid=1667616243&qu=eyJxc2MiOiI1LjIzIiwicXNhIjoiNS4wMyIsInFzcCI6IjQuNjEifQ%3D%3D&sr=8-1",
                "Please buy an iPhone 12", "1000", "1", "1", "new", "100");

        given(ordersRepository.save(orders)).willReturn(orders);

        Orders savedOrder = null;
        try{
            savedOrder = ordersService.save(orders);
        }catch (Exception e){
            e.printStackTrace();
        }
        assertThat(savedOrder).isNotNull();
        assertEquals(orders, savedOrder);
    }

    @Test
    public void deleteTest() throws Exception{
        Long id = 1L;
        ordersService.delete(id);
        verify(ordersRepository, times(1)).deleteById(id);
    }

    @Test
    public void getAllTest() throws Exception{
        List<Orders> list = new ArrayList<>();
        list.add(new Orders(1L,"71234567", "Sebastian",
                "https://www.amazon.com/-/es/Apple-iPhone-12-64GB-azul/dp/B09JFTPQY1/ref=sr_1_1?keywords=iphone+12&qid=1667616243&qu=eyJxc2MiOiI1LjIzIiwicXNhIjoiNS4wMyIsInFzcCI6IjQuNjEifQ%3D%3D&sr=8-1",
                "Please buy an iPhone 12", "1000", "1", "1", "new", "100"));
        list.add(new Orders(1L,"71234568", "Edgar",
                "https://www.amazon.com/-/es/Apple-iPhone-12-64GB-azul/dp/B09JFTPQY1/ref=sr_1_1?keywords=iphone+12&qid=1667616243&qu=eyJxc2MiOiI1LjIzIiwicXNhIjoiNS4wMyIsInFzcCI6IjQuNjEifQ%3D%3D&sr=8-1",
                "Please buy an iPhone 12", "1000", "1", "1", "new", "100"));
        list.add(new Orders(1L,"71234569", "Andre",
                "https://www.amazon.com/-/es/Apple-iPhone-12-64GB-azul/dp/B09JFTPQY1/ref=sr_1_1?keywords=iphone+12&qid=1667616243&qu=eyJxc2MiOiI1LjIzIiwicXNhIjoiNS4wMyIsInFzcCI6IjQuNjEifQ%3D%3D&sr=8-1",
                "Please buy an iPhone 12", "1000", "1", "1", "new", "100"));
        list.add(new Orders(1L,"71234570", "Rafael",
                "https://www.amazon.com/-/es/Apple-iPhone-12-64GB-azul/dp/B09JFTPQY1/ref=sr_1_1?keywords=iphone+12&qid=1667616243&qu=eyJxc2MiOiI1LjIzIiwicXNhIjoiNS4wMyIsInFzcCI6IjQuNjEifQ%3D%3D&sr=8-1",
                "Please buy an iPhone 12", "1000", "1", "1", "new", "100"));
        list.add(new Orders(1L,"71234571", "Gonzalo",
                "https://www.amazon.com/-/es/Apple-iPhone-12-64GB-azul/dp/B09JFTPQY1/ref=sr_1_1?keywords=iphone+12&qid=1667616243&qu=eyJxc2MiOiI1LjIzIiwicXNhIjoiNS4wMyIsInFzcCI6IjQuNjEifQ%3D%3D&sr=8-1",
                "Please buy an iPhone 12", "1000", "1", "1", "new", "100"));

        given(ordersRepository.findAll()).willReturn(list);
        List<Orders> listExpected = ordersService.getAll();
        assertEquals(listExpected, list);
    }

    @Test
    public void getByIdTest() throws Exception{
        Long id = 1L;
        Orders order = new Orders(1L,"71234567", "Sebastian",
                "https://www.amazon.com/-/es/Apple-iPhone-12-64GB-azul/dp/B09JFTPQY1/ref=sr_1_1?keywords=iphone+12&qid=1667616243&qu=eyJxc2MiOiI1LjIzIiwicXNhIjoiNS4wMyIsInFzcCI6IjQuNjEifQ%3D%3D&sr=8-1",
                "Please buy an iPhone 12", "1000", "1", "1", "new", "100");

        given(ordersRepository.findById(id)).willReturn(Optional.of(order));
        Optional<Orders> orderExpected = ordersService.getById(id);
        assertThat(orderExpected).isNotNull();
        assertEquals(orderExpected, Optional.of(order));
    }



}
