package com.spring.beerservice.services;

import java.util.UUID;

import com.spring.beerservice.web.model.BeerDto;

public interface BeerService {

	BeerDto getBeerById(UUID id);

	BeerDto saveNewBeer(BeerDto beerDto);

	BeerDto updateBeerById(UUID id, BeerDto beerDto);

}
