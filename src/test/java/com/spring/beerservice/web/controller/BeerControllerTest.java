package com.spring.beerservice.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.beerservice.web.model.BeerDto;
import com.spring.beerservice.web.model.BeerStyleEnum;

@WebMvcTest(BeerController.class)
public class BeerControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;

	@Test
	void getBeerById() throws Exception {
		mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}
	
	@Test
	void saveNewBeer() throws Exception {
		BeerDto beerDto =  BeerDto.builder()
							.beerName("Mango Punch")
							.beerStyle(BeerStyleEnum.ALE)
							.upc(2378L)
							.price(new BigDecimal(215.76))
							.build();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
							
		mockMvc.perform(post("/api/v1/beer/").contentType(MediaType.APPLICATION_JSON).content(beerDtoJson))
		.andExpect(status().isCreated());

	}

}
