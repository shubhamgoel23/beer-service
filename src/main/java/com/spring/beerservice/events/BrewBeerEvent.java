package com.spring.beerservice.events;

import com.spring.beerservice.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BrewBeerEvent(BeerDto beerDto) {
		super(beerDto);
		// TODO Auto-generated constructor stub
	}

}
