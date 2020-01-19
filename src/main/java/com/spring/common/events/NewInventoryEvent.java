package com.spring.common.events;

import com.spring.common.model.BeerDto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
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
