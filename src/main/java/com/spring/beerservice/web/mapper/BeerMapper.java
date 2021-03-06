package com.spring.beerservice.web.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import com.spring.beerservice.domain.Beer;
import com.spring.common.model.BeerDto;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

	BeerDto beerToBeerDto(Beer beer);

    BeerDto beerToBeerDtoWithInventory(Beer beer);

    Beer beerDtoToBeer(BeerDto dto);
}
