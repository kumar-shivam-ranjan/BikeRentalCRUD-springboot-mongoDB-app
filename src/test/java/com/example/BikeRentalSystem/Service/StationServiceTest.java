package com.example.BikeRentalSystem.Service;

import com.example.BikeRentalSystem.entities.Station;
import com.example.BikeRentalSystem.repository.StationRepository;
import com.example.BikeRentalSystem.service.StationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class StationServiceTest {

    @Autowired
    private StationService stationService;

    @MockBean
    private StationRepository stationRepository;

    @Test
    void getAllStation() {
        when(stationRepository.findAll()).thenReturn(Stream.of(new Station(1,"kumar","8282701270","street","city","state",800811)).collect(Collectors.toList()));
        assertEquals(1,stationService.getAllStation().size());
    }

    @Test
    void getStationByCity(){
        String city="Ranchi";
        when(stationRepository.findByCity(city)).
                thenReturn(Stream.of(new Station(1,"kumar","8282701270","street","city","state",800811),
                        new Station(2,"kumar","8282701270","street","city","state",800811),
                        new Station(3,"shivam","8282701270","street","city","state",800812),
                        new Station(4,"ranjan","8282701270","street","city","state",800813)).collect(Collectors.toList()));
       assertEquals(4,stationService.getStationByCity(city).size());
    }
    @Test
    void deleteStation() {
        Station station = new Station(4, "ranjan", "8282701270", "street", "city", "state", 800813);
        stationService.deleteStation(station.getStationId());
        verify(stationRepository).deleteById(station.getStationId());
    }
}