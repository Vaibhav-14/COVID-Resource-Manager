package com.mycompany.entity;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VaccineEntityTest {

	

	@Test
	public void testGetterSetter() {
		Session session = new Session();
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

	@Test
	public void testConstructorr() {
		List<String> slots = new ArrayList<String>();
		slots.add("10:00AM-12:00PM");
		slots.add("12:00PM-02:00PM");
		slots.add("02:00PM-04:00PM");
		slots.add("04:00PM-05:00PM");

		Session session = new Session(724452, "PMC P ASHTANG AYURVED RUGNALAY","PARANJPE CHAUK TILAKROAD SADASHIV PETH PUNR",
				"Maharashtra",
				"Pune", "Haveli", 411030, "10:00:00", "20:00:00",
				18, 73, "Paid", "c54369d5-ed36-4c65-929d-64db57f40a47", "19-08-2021",50,
				29, 21, "780", 18,
				true, "COVISHIELD", slots);
		
		assertEquals(411030, session.getPincode());

	}



}
