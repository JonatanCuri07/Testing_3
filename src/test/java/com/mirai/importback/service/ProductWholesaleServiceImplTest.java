package com.mirai.importback.service;

import com.mirai.importback.entities.ProductWholesale;
import com.mirai.importback.repositories.IProductWholesaleRepository;
import com.mirai.importback.services.impl.ProductWholesaleServiceImpl;
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
public class ProductWholesaleServiceImplTest {
    @Mock
    private IProductWholesaleRepository productWholesaleRepository;

    @InjectMocks
    private ProductWholesaleServiceImpl productWholesaleService;

    @Test
    public void saveTest(){
        ProductWholesale productWholesale = new ProductWholesale(1L,"Iphone","20","350",
                "https://i.blogs.es/35ef0d/iphone-12-pro-max-00-01/1366_2000.jpg",
                "Is an Iphone", "24 horas de batería",
                "Medium size","164GB of storage");

        given(productWholesaleRepository.save(productWholesale)).willReturn(productWholesale);

        ProductWholesale savedProductWholesale = null;
        try{
            savedProductWholesale = productWholesaleService.save(productWholesale);
        }catch (Exception e){
            e.printStackTrace();
        }
        assertThat(savedProductWholesale).isNotNull();
        assertEquals(productWholesale, savedProductWholesale);
    }

    @Test
    public void deleteTest() throws Exception{
        Long id = 1L;
        productWholesaleService.delete(id);
        verify(productWholesaleRepository, times(1)).deleteById(id);
    }

    @Test
    public void getAllTest() throws Exception{
        List<ProductWholesale> list = new ArrayList<>();
        list.add(new ProductWholesale(1L,"Iphone 1","20","350",
                "https://i.blogs.es/35ef0d/iphone-12-pro-max-00-01/1366_2000.jpg",
                "Is an Iphone", "24 horas de batería",
                "Medium size","164GB of storage"));
        list.add(new ProductWholesale(2L,"Iphone 2","20","350",
                "https://i.blogs.es/35ef0d/iphone-12-pro-max-00-01/1366_2000.jpg",
                "Is an Iphone", "24 horas de batería",
                "Medium size","164GB of storage"));
        list.add(new ProductWholesale(3L,"Iphone 3","20","350",
                "https://i.blogs.es/35ef0d/iphone-12-pro-max-00-01/1366_2000.jpg",
                "Is an Iphone", "24 horas de batería",
                "Medium size","164GB of storage"));
        list.add(new ProductWholesale(4L,"Iphone 4","20","350",
                "https://i.blogs.es/35ef0d/iphone-12-pro-max-00-01/1366_2000.jpg",
                "Is an Iphone", "24 horas de batería",
                "Medium size","164GB of storage"));
        list.add(new ProductWholesale(5L,"Iphone 5","20","350",
                "https://i.blogs.es/35ef0d/iphone-12-pro-max-00-01/1366_2000.jpg",
                "Is an Iphone", "24 horas de batería",
                "Medium size","164GB of storage"));


        given(productWholesaleRepository.findAll()).willReturn(list);
        List<ProductWholesale> listExpected = productWholesaleService.getAll();
        assertEquals(listExpected, list);
    }

    @Test
    public void getByIdTest() throws Exception{
        Long id = 1L;
        ProductWholesale productWholesale = new ProductWholesale(1L,"Iphone 1","20","350",
                "https://i.blogs.es/35ef0d/iphone-12-pro-max-00-01/1366_2000.jpg",
                "Is an Iphone", "24 horas de batería",
                "Medium size","164GB of storage");

        given(productWholesaleRepository.findById(id)).willReturn(Optional.of(productWholesale));
        Optional<ProductWholesale> productWholesaleExpected = productWholesaleService.getById(id);
        assertThat(productWholesaleExpected).isNotNull();
        assertEquals(productWholesaleExpected, Optional.of(productWholesale));
    }

}
