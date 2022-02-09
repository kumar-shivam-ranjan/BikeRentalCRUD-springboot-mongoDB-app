package com.example.BikeRentalSystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.BikeRentalSystem.entities.Station;
import com.example.BikeRentalSystem.repository.StationRepository;

@Service
public class StationService {
	
	@Autowired
	private StationRepository stationRepository;
	
	@Autowired
	private SequenceGeneratoryService sequenceGeneratoryService;
	
	public List<Station> getAllStation()
	{
		return stationRepository.findAll();
	}
	
	
	public Station addStation(Station station) {
		return stationRepository.save(station);
	}


	public void deleteStation(int id)
	{
		stationRepository.deleteById(id);
	}


	public Station updateStation(Station station, int id) {
		
			Station newStation=stationRepository.findById(id).get();
			newStation.setCity(station.getCity());
			newStation.setHead(station.getHead());
			newStation.setPincode(station.getPincode());
			newStation.setState(station.getState());
			newStation.setStreet(station.getStreet());
			Station newUpdatedStation=stationRepository.save(newStation);
			return newUpdatedStation;
	}
	
	public List<Station> getStationByCity(String city) {
		return this.stationRepository.findByCity(city);
	}

	public Station getStationById(int id) {
		return this.stationRepository.findById(id).get();
	}
	
}
