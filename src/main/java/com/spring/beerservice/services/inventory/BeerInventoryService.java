package com.spring.beerservice.services.inventory;

import java.util.UUID;

public interface BeerInventoryService {

	Integer getOnhandInventory(UUID beerId);

}
