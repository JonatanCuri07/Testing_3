package com.mirai.importback.service;

import com.mirai.importback.entities.DomesticShipment;
import com.mirai.importback.entities.Users;
import com.mirai.importback.repositories.IDomesticShipmentRepository;
import com.mirai.importback.services.impl.DomesticShipmentServiceImpl;
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
public class DomesticShipmentServiceImplTest {
        @Mock
        private IDomesticShipmentRepository domesticShipmentRepository;

        @InjectMocks
        private DomesticShipmentServiceImpl domesticShipmentService;

        @Test
        public void saveTest(){
            DomesticShipment domesticShipment = new DomesticShipment(1L,
                    "https://img.freepik.com/vector-gratis/dispositivo-telefono-inteligente-realista_52683-29765.jpg?size=338&ext=jpg&ga=GA1.2.459603537.1666060327",
                    "Iphone 13","1","09/09/09","8","09/09/09","olva courier","1289","AWE458",
                    "Chiclayo");

            given(domesticShipmentRepository.save(domesticShipment)).willReturn(domesticShipment);

            DomesticShipment savedDomesticShipment = null;
            try{
                savedDomesticShipment = domesticShipmentService.save(domesticShipment);
            }catch (Exception e){
                e.printStackTrace();
            }
            assertThat(savedDomesticShipment).isNotNull();
            assertEquals(domesticShipment, savedDomesticShipment);
        }

        @Test
        public void deleteTest() throws Exception{
            Long id = 1L;
            domesticShipmentService.delete(id);
            verify(domesticShipmentRepository, times(1)).deleteById(id);
        }

        @Test
        public void getAllTest() throws Exception{
            List<DomesticShipment> list = new ArrayList<>();
            list.add(new DomesticShipment(1L,
                    "https://img.freepik.com/vector-gratis/dispositivo-telefono-inteligente-realista_52683-29765.jpg?size=338&ext=jpg&ga=GA1.2.459603537.1666060327",
                    "Iphone 13","1","09/09/09","8","09/09/09","olva courier","1289","AWE458",
                    "Chiclayo"));
            list.add(new DomesticShipment(2L,
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ9gaYbIm9wrfg43NwHaVCwu7A7mn3HAaj-mw&usqp=CAU",
                    "Armani Perfume","3","09/09/09","12","09/09/09","olva courier","1289","ASD888",
                    "Juliaca"));
            list.add(new DomesticShipment(3L,
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKzFknHBs52FBby5EgUUMnc96vdVt3qL5Ylw&usqp=CAU",
                    "Guess Watch","2","09/09/09","7","09/09/09","olva courier","1289","QQQ965",
                    "Piura"));

            given(domesticShipmentRepository.findAll()).willReturn(list);
            List<DomesticShipment> listExpected = domesticShipmentService.getAll();
            assertEquals(listExpected, list);
        }

        @Test
        public void getByIdTest() throws Exception{
            Long id = 1L;
            DomesticShipment domesticShipment = new DomesticShipment(1L,
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKzFknHBs52FBby5EgUUMnc96vdVt3qL5Ylw&usqp=CAU",
                    "Guess Watch","2","09/09/09","7","09/09/09","olva courier","1289","QQQ965",
                    "Piura");

            given(domesticShipmentRepository.findById(id)).willReturn(Optional.of(domesticShipment));
            Optional<DomesticShipment> customerExpected = domesticShipmentService.getById(id);
            assertThat(customerExpected).isNotNull();
            assertEquals(customerExpected, Optional.of(domesticShipment));
        }

}

