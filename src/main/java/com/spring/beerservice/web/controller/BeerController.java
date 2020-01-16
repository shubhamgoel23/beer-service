package com.spring.beerservice.web.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.beerservice.services.BeerService;
import com.spring.beerservice.web.model.BeerDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

	private final BeerService beerService;

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID id) {

//		return new ResponseEntity<BeerDto>(BeerDto.builder().build(), HttpStatus.OK);
		return new ResponseEntity<BeerDto>(beerService.getBeerById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BeerDto> saveNewBeer(@Valid @RequestBody BeerDto beerDto) {

		return new ResponseEntity<>(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);
	}

	@PutMapping("/{beerId}")
	public ResponseEntity<BeerDto> updateBeerById(@PathVariable("beerId") UUID id,
			@Valid @RequestBody BeerDto beerDto) {

		return new ResponseEntity<>(beerService.updateBeerById(id, beerDto), HttpStatus.NO_CONTENT);
	}

}
