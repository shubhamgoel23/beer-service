package com.spring.beerservice.services.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.beerservice.bootstrap.BeerLoader;

@Disabled // utility for manual testing
@SpringBootTest
public class BeerInventoryServiceRestTemplateImplTest {

	@Autowired
	BeerInventoryService beerInventoryService;

	@BeforeEach
	void setUp() {

	}

	@Test
	void getOnhandInventory() {

		// todo evolve to use UPC
//		Integer qoh = beerInventoryService.getOnhandInventory(BeerLoader.BEER_1_UUID);
//
//		System.out.println(qoh);

	}

}
