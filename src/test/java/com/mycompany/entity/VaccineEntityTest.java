package com.mycompany.entity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VaccineEntityTest {
	
	Session session = new Session();
	
	@Test
	public void testGetterSetter() {
		assertDoesNotThrow(() -> session.setCenterId(session.getCenterId()));
		assertDoesNotThrow(() -> session.setAddress(session.getAddress()));
		assertDoesNotThrow(() -> session.setAllowAllAge(session.isAllowAllAge()));
		assertDoesNotThrow(() -> session.setAvailableCapacity(session.getAvailableCapacity()));
		assertDoesNotThrow(() -> session.setAvailableCapacityDose1(session.getAvailableCapacityDose1()));
		assertDoesNotThrow(() -> session.setAvailableCapacityDose2(session.getAvailableCapacityDose2()));
		assertDoesNotThrow(() -> session.setCenterId(session.getCenterId()));
		assertDoesNotThrow(() -> session.setBlockName(session.getBlockName()));
		assertDoesNotThrow(() -> session.setDate(session.getDate()));
		assertDoesNotThrow(() -> session.setDistrictName(session.getDistrictName()));
		assertDoesNotThrow(() -> session.setFee(session.getFee()));
		assertDoesNotThrow(() -> session.setFeeType(session.getFeeType()));
		assertDoesNotThrow(() -> session.setFrom(session.getFrom()));
		assertDoesNotThrow(() -> session.setLat(session.getLat()));
		assertDoesNotThrow(() -> session.setLong(session.getLong()));
		assertDoesNotThrow(() -> session.setMinAgeLimit(session.getMinAgeLimit()));
		assertDoesNotThrow(() -> session.setName(session.getName()));
		assertDoesNotThrow(() -> session.setPincode(session.getPincode()));
		assertDoesNotThrow(() -> session.setSessionId(session.getSessionId()));
		assertDoesNotThrow(() -> session.setSlots(session.getSlots()));
		assertDoesNotThrow(() -> session.setStateName(session.getStateName()));
		assertDoesNotThrow(() -> session.setVaccine(session.getVaccine()));
		assertDoesNotThrow(() -> session.setTo(session.getTo()));

	}

}
