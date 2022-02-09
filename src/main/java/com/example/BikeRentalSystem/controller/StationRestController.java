package com.example.BikeRentalSystem.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.BikeRentalSystem.entities.Station;
import com.example.BikeRentalSystem.service.StationService;


@RestController
public class StationRestController {

	
	@Autowired
	private StationService stationService;
	
	@GetMapping("stations")
	public ResponseEntity<List<Station>> getAllStation()
	{
		List<Station> stations=(List<Station>) stationService.getAllStation();
		if(stations.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(stations));
	}

	@GetMapping("station/{id}")
	public ResponseEntity<Station> getStationById(@PathVariable("id") int id){
		try {
			Station station=this.stationService.getStationById(id);
			return ResponseEntity.of(Optional.of(station));
		}
		catch (Exception e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("stations/{city}")
	public ResponseEntity<List<Station>> getStationByCity(@PathVariable("city") String city){
		List<Station> stations=(List<Station>) stationService.getStationByCity(city);
		if(stations.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(stations));
	}
	
	
	@PostMapping("stations")
	public ResponseEntity<Station> addStation(@RequestBody Station station) {
		try {
			
			Station addedStation= stationService.addStation(station);
			return ResponseEntity.of(Optional.of(addedStation));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@DeleteMapping("stations/{id}")
	public ResponseEntity<Void> deleteStation(@PathVariable("id") int id) {
			stationService.deleteStation(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("stations/{id}")
	public ResponseEntity<Station> updateStation(@RequestBody Station station, @PathVariable("id") int id) {
		try {
			Station newStation= stationService.updateStation(station,id);
			return ResponseEntity.of(Optional.of(newStation));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
