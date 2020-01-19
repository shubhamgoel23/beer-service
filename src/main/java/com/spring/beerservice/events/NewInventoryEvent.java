package com.spring.beerservice.events;

import com.spring.beerservice.web.model.BeerDto;

public class NewInventoryEvent extends BeerEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8725361231193812856L;

	public NewInventoryEvent(BeerDto beerDto) {
		super(beerDto);
		// TODO Auto-generated constructor stub
	}

}
