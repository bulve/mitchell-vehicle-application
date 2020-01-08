package io.github.bulve.vehicle;

import io.github.bulve.vehicle.model.dto.VehicleDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class VehicleControllerTest {

	@Autowired
	private VehicleController vehicleController;

	@Test
	public void shouldSaveVehicle() {
		VehicleDTO savedVehicle = vehicleController.saveVehicle(mockVehicleDTO()).getBody();
		VehicleDTO retrievedVehicle = vehicleController.getVehicles(savedVehicle.getId()).getBody();
		assertThat(retrievedVehicle.getId(), is(savedVehicle.getId()));
		assertThat(retrievedVehicle.getMake(), is(savedVehicle.getMake()));
		assertThat(retrievedVehicle.getModel(), is(savedVehicle.getModel()));
		assertThat(retrievedVehicle.getYear(), is(savedVehicle.getYear()));
	}

	@Test
	public void shouldUpdateVehicle() {
		VehicleDTO savedVehicle = vehicleController
				.saveVehicle(mockVehicleDTO("Corolla", "Toyota", 2001)).getBody();
		savedVehicle.setYear(1993);
		savedVehicle.setModel("Lexus");
		vehicleController.updateVehicle(savedVehicle);
		VehicleDTO updatedVehicle = vehicleController.getVehicles(savedVehicle.getId()).getBody();
		assertThat(updatedVehicle.getId(), is(savedVehicle.getId()));
		assertThat(updatedVehicle.getMake(), is(savedVehicle.getMake()));
		assertThat(updatedVehicle.getModel(), is(savedVehicle.getModel()));
		assertThat(updatedVehicle.getYear(), is(savedVehicle.getYear()));
	}

	@Test
	public void shouldNotFoundNotExitingVehicle() {
		ResponseEntity<VehicleDTO> responseEntity = vehicleController.getVehicles(10);
		assertThat(responseEntity.getBody(), is(nullValue()));
		assertThat(responseEntity.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	}

	@Test
	public void shouldDeleteVehicle() {
		VehicleDTO vehicleDTO = vehicleController.saveVehicle(mockVehicleDTO()).getBody();
		ResponseEntity<String> deleteResponse = vehicleController.deleteVehicle(vehicleDTO.getId());
		assertThat(deleteResponse.getBody(), is(nullValue()));
		assertThat(deleteResponse.getStatusCode(), is(HttpStatus.OK));

		ResponseEntity<VehicleDTO> responseEntity = vehicleController.getVehicles(vehicleDTO.getId());
		assertThat(responseEntity.getBody(), is(nullValue()));
		assertThat(responseEntity.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	}

	@Test
	public void shouldGetAllSavedVehicles() {
		VehicleDTO vehicleDTO1 = vehicleController.saveVehicle(mockVehicleDTO()).getBody();
		VehicleDTO vehicleDTO2 = vehicleController.saveVehicle(mockVehicleDTO()).getBody();
		VehicleDTO vehicleDTO3 = vehicleController.saveVehicle(mockVehicleDTO()).getBody();
		VehicleDTO vehicleDTO4 = vehicleController.saveVehicle(mockVehicleDTO()).getBody();
		Collection<VehicleDTO> allVehicles = vehicleController.getVehicles(null, null, null).getBody();
		assertThat(allVehicles, hasSize(4));
		assertThat(allVehicles, hasItems(
				hasProperty("id", is(vehicleDTO1.getId())),
				hasProperty("id", is(vehicleDTO2.getId())),
				hasProperty("id", is(vehicleDTO3.getId())),
				hasProperty("id", is(vehicleDTO4.getId()))
		));
	}

	@Test
	public void shouldFindVehicleByYear() {
		vehicleController.saveVehicle(mockVehicleDTO(1991)).getBody();
		VehicleDTO vehicleDTO2 = vehicleController.saveVehicle(mockVehicleDTO(2001)).getBody();
		VehicleDTO vehicleDTO3 = vehicleController.saveVehicle(mockVehicleDTO(2001)).getBody();
		vehicleController.saveVehicle(mockVehicleDTO(2003)).getBody();
		Collection<VehicleDTO> allVehicles = vehicleController.getVehicles(null, null, 2001).getBody();
		assertThat(allVehicles, hasSize(2));
		assertThat(allVehicles, hasItems(
				hasProperty("id", is(vehicleDTO2.getId())),
				hasProperty("id", is(vehicleDTO3.getId()))
		));
	}

	@Test
	public void shouldFindVehicleByMakeAndModel() {
		VehicleDTO peugeotVehicle = vehicleController.saveVehicle(mockVehicleDTO("Peugeot", "106")).getBody();
		vehicleController.saveVehicle(mockVehicleDTO("Mazda", "RX7")).getBody();
		vehicleController.saveVehicle(mockVehicleDTO("BMW", "i5")).getBody();
		vehicleController.saveVehicle(mockVehicleDTO("Toyota", "Corolla")).getBody();
		vehicleController.saveVehicle(mockVehicleDTO("Peugeot", "306")).getBody();
		Collection<VehicleDTO> allVehicles = vehicleController.getVehicles("Peugeot", "106", null).getBody();
		assertThat(allVehicles, hasSize(1));
		VehicleDTO foundVehicle = allVehicles.iterator().next();
		assertThat(foundVehicle.getId(), is(peugeotVehicle.getId()));
		assertThat(foundVehicle.getMake(), is("Peugeot"));
		assertThat(foundVehicle.getModel(), is("106"));
		assertThat(foundVehicle.getYear(), is(peugeotVehicle.getYear()));
	}

	private VehicleDTO mockVehicleDTO() {
		return mockVehicleDTO("mock make", "mock model", 1993);
	}

	private VehicleDTO mockVehicleDTO(int year) {
		return mockVehicleDTO("mock make", "mock model", year);
	}

	private VehicleDTO mockVehicleDTO(String make, String model) {
		return mockVehicleDTO(make, model, 1998);
	}

	private VehicleDTO mockVehicleDTO(String make, String model, int year) {
		VehicleDTO vehicleDTO = new VehicleDTO();
		vehicleDTO.setMake(make);
		vehicleDTO.setModel(model);
		vehicleDTO.setYear(year);
		return vehicleDTO;
	}

}
