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
import com.example.BikeRentalSystem.entities.Inventory;
import com.example.BikeRentalSystem.repository.InventoryRepository;
import com.example.BikeRentalSystem.service.SequenceGeneratoryService;

@RestController
public class InventoryRestController {

	@Autowired
	private InventoryRepository inventoryRepository;

	
	@Autowired
	private SequenceGeneratoryService sequenceGeneratoryService;
	
	@PostMapping("inventory")
	public Inventory addInventory(@RequestBody Inventory inventory)
	{
		return inventoryRepository.save(inventory);
	}
	
	@GetMapping("inventory")
	public ResponseEntity<List<Inventory>> getInventory(){
		List<Inventory> inventories=inventoryRepository.findAll();
		if(inventories.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(inventories));
	}
	
	@GetMapping("inventory/{stationId}")
	public ResponseEntity<List<Inventory>> getInventoryById(@PathVariable("stationId") int sid) {
		try {
			List<Inventory> inventories=inventoryRepository.findByStationId(sid);
			if(inventories.size()<=0) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.of(Optional.of(inventories));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
		
	@PutMapping("inventory/{id}")
	public ResponseEntity<Inventory> updateInventory(@RequestBody Inventory inventory,@PathVariable("id") int id) {
		
		try {
			Inventory oldInventory=inventoryRepository.findById(id).get();
			oldInventory.setAvailabilityStatus(inventory.isAvailabilityStatus());
			oldInventory.setQuantity(inventory.getQuantity());
			Inventory newInventory=inventoryRepository.save(oldInventory);
			return ResponseEntity.ok().body(newInventory);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("inventory/{id}")
	public ResponseEntity<Void> deleteInventory(@PathVariable("id") int id) {
		try {
			inventoryRepository.findById(id).get();
			inventoryRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();		
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}		
	
}
		
	

