package com.spring.beerservice.services;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;

import com.spring.common.model.BeerDto;
import com.spring.common.model.BeerPagedList;
import com.spring.common.model.BeerStyleEnum;

public interface BeerService {

	BeerDto getBeerById(UUID id, Boolean showInventoryOnHand);

	BeerDto saveNewBeer(BeerDto beerDto);

	BeerDto updateBeerById(UUID id, BeerDto beerDto);

	BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of, Boolean showInventoryOnHand);
	
	BeerDto getByUpc(String upc);

}
