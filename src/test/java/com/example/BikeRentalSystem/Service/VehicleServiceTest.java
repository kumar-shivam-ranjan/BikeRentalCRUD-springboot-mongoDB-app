package com.example.BikeRentalSystem.Service;
import com.example.BikeRentalSystem.entities.Vehicle;
import com.example.BikeRentalSystem.repository.VehicleRepository;
import com.example.BikeRentalSystem.service.VehicleService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
class VehicleServiceTest {

    @MockBean
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleService vehicleService;

    @Test
    void getAllVehicles() {
        when(vehicleRepository.findAll()).thenReturn(Stream.of(new Vehicle(String.valueOf(ObjectId.get()),"Bike","Petrol","109cc",89,"Vehicle Bike","http","yamaha RTR",100,100)).collect(Collectors.toList()));
        assertEquals(1,vehicleService.getAllVehicles().size());
    }
}