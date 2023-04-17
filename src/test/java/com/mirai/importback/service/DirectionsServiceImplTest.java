package com.mirai.importback.service;

import com.mirai.importback.entities.Directions;
import com.mirai.importback.repositories.IDirectionsRepository;
import com.mirai.importback.services.impl.DirectionsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class DirectionsServiceImplTest {

    @Mock
    private IDirectionsRepository directionsRepository;

    @InjectMocks
    private DirectionsServiceImpl directionsService;

    @Test
    public void saveTest() {
        Directions direction = new Directions(1L, "281423813", "Alejandro", "Ramirez", "Calle Los Jardines, Av. Universitaria", "992331554", "Los Olivos", "Lima", "Lima");

        given(directionsRepository.save(direction)).willReturn(direction);

        Directions savedDirection = null;
        try {
            savedDirection = directionsService.save(direction);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(savedDirection).isNotNull();
        assertEquals(direction, savedDirection);
    }

    @Test
    public void deleteTest() throws Exception {
        Long id = 1L;
        directionsService.delete(id);
        verify(directionsRepository, times(1)).deleteById(id);
    }

    @Test
    public void getAllTest() throws Exception {
        List<Directions> directions_list = new ArrayList<>();
        directions_list.add(new Directions(1L, "281423813", "Alejandro", "Ramirez", "Calle Los Jardines, Av. Universitaria", "992331554", "Los Olivos", "Lima", "Lima"));
        directions_list.add(new Directions(1L, "281421555", "Manuel", "Ramirez", "Calle Los Jardines, Av. Universitaria", "992331554", "Los Olivos", "Lima", "Lima"));
        directions_list.add(new Directions(1L, "281423777", "Martin", "Ramirez", "Calle Los Jardines, Av. Universitaria", "992331554", "Los Olivos", "Lima", "Lima"));

        given(directionsRepository.findAll()).willReturn(directions_list);
        List<Directions> listExpected = directionsService.getAll();
        assertEquals(listExpected, directions_list);
    }

    @Test
    public void getByIdTest() throws Exception {
        Long id = 1L;
        Directions direction = new Directions(1L, "281423813", "Alejandro", "Ramirez", "Calle Los Jardines, Av. Universitaria", "992331554", "Los Olivos", "Lima", "Lima");

        given(directionsRepository.findById(id)).willReturn(Optional.of(direction));
        Optional<Directions> directionExpected = directionsService.getById(id);
        assertThat(directionExpected).isNotNull();
        assertEquals(directionExpected, Optional.of(direction));
    }
}