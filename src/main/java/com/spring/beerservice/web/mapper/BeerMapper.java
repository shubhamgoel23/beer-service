package com.spring.beerservice.web.mapper;

import org.mapstruct.Mapper;

import com.spring.beerservice.domain.Beer;
import com.spring.beerservice.web.model.BeerDto;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

	BeerDto beerToBeerDto(Beer beer);

    BeerDto beerToBeerDtoWithInventory(Beer beer);

    Beer beerDtoToBeer(BeerDto dto);
}
