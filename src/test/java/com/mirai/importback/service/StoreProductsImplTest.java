package com.mirai.importback.service;

import com.mirai.importback.entities.StoreProducts;
import com.mirai.importback.repositories.IStoreProductsRepository;
import com.mirai.importback.services.impl.StoreProductsImpl;
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
public class StoreProductsImplTest {

    @Mock
    private IStoreProductsRepository storeProductsRepository;

    @InjectMocks
    private StoreProductsImpl storeProductsService;

    @Test
    public void saveTest(){
        StoreProducts storeProducts = new StoreProducts(1L,"KIDS",List.of());

        given(storeProductsRepository.save(storeProducts)).willReturn(storeProducts);

        StoreProducts savedStoreProducts = null;
        try{
            savedStoreProducts = storeProductsService.save(storeProducts);
        }catch (Exception e){
            e.printStackTrace();
        }
        assertThat(savedStoreProducts).isNotNull();
        assertEquals(storeProducts, savedStoreProducts);
    }

    @Test
    public void deleteTest() throws Exception{
        Long id = 1L;
        storeProductsService.delete(id);
        verify(storeProductsRepository, times(1)).deleteById(id);
    }

    @Test
    public void getAllTest() throws Exception{
        List<StoreProducts> list = new ArrayList<>();
        list.add(new StoreProducts(1L,"Technology",List.of()));
        list.add(new StoreProducts(2L,"CLOTHING",List.of()));
        list.add(new StoreProducts(3L,"MUSEUM",List.of()));
        list.add(new StoreProducts(4L,"HOME",List.of()));
        list.add(new StoreProducts(5L,"KIDS",List.of()));

        given(storeProductsRepository.findAll()).willReturn(list);
        List<StoreProducts> listExpected = storeProductsService.getAll();
        assertEquals(listExpected, list);
    }

    @Test
    public void getByIdTest() throws Exception{
        Long id = 1L;
        StoreProducts customer = new StoreProducts(1L,"Rafael",List.of());

        given(storeProductsRepository.findById(id)).willReturn(Optional.of(customer));
        Optional<StoreProducts> customerExpected = storeProductsService.getById(id);
        assertThat(customerExpected).isNotNull();
        assertEquals(customerExpected, Optional.of(customer));
    }



}
