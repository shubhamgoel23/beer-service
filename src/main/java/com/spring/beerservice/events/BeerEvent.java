package com.spring.beerservice.events;

import java.io.Serializable;

import com.spring.beerservice.web.model.BeerDto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 26215217518935101L;
	
	private final BeerDto beerDto;
	
	

}
