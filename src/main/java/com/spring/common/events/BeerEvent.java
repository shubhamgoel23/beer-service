package com.spring.common.events;

import java.io.Serializable;

import com.spring.common.model.BeerDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
