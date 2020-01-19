package com.spring.beerservice.events;

import java.io.Serializable;

import com.spring.beerservice.web.model.BeerDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerEvent implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 26215217518935101L;
	
	private  BeerDto beerDto;
	
	

}
