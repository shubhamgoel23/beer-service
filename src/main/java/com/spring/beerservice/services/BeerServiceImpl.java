package com.spring.beerservice.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.spring.beerservice.domain.Beer;
import com.spring.beerservice.repositories.BeerRepository;
import com.spring.beerservice.web.controller.NotFoundException;
import com.spring.beerservice.web.mapper.BeerMapper;
import com.spring.beerservice.web.model.BeerDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;

	@Override
	public BeerDto getBeerById(UUID id) {

		return beerMapper.beerToBeerDto(beerRepository.findById(id).orElseThrow(NotFoundException::new));
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {

		return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
	}

	@Override
	public BeerDto updateBeerById(UUID id, BeerDto beerDto) {

		Beer beer = beerRepository.findById(id).orElseThrow(NotFoundException::new);

		beer.setBeerName(beerDto.getBeerName());
		beer.setBeerStyle(beerDto.getBeerStyle().name());
		beer.setPrice(beerDto.getPrice());
		beer.setUpc(beerDto.getUpc());

		return beerMapper.beerToBeerDto(beerRepository.save(beer));
	}

}
