package com.spring.beerservice.services;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;

import com.spring.beerservice.web.model.BeerDto;
import com.spring.beerservice.web.model.BeerPagedList;
import com.spring.beerservice.web.model.BeerStyleEnum;

public interface BeerService {

	BeerDto getBeerById(UUID id);

	BeerDto saveNewBeer(BeerDto beerDto);

	BeerDto updateBeerById(UUID id, BeerDto beerDto);

	BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of, Boolean showInventoryOnHand);

}
