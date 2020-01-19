package com.spring.beerservice.services;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.spring.beerservice.domain.Beer;
import com.spring.beerservice.repositories.BeerRepository;
import com.spring.beerservice.web.controller.NotFoundException;
import com.spring.beerservice.web.mapper.BeerMapper;
import com.spring.common.model.BeerDto;
import com.spring.common.model.BeerPagedList;
import com.spring.common.model.BeerStyleEnum;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;

	@Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false ")
	@Override
	public BeerDto getBeerById(UUID id, Boolean showInventoryOnHand) {

		if (showInventoryOnHand) {
            return beerMapper.beerToBeerDtoWithInventory(
                    beerRepository.findById(id).orElseThrow(NotFoundException::new)
            );
        } else {
            return beerMapper.beerToBeerDto(
                    beerRepository.findById(id).orElseThrow(NotFoundException::new)
            );
        }
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

	@Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false ")
	@Override
	public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest,
			Boolean showInventoryOnHand) {

		System.out.println("im called");
		BeerPagedList beerPagedList;
		Page<Beer> beerPage;

		if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			// search both
			beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
		} else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
			// search beer_service name
			beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
		} else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			// search beer_service style
			beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
		} else {
			beerPage = beerRepository.findAll(pageRequest);
		}

		if (showInventoryOnHand) {
			beerPagedList = new BeerPagedList(
					beerPage.getContent().stream().map(beerMapper::beerToBeerDtoWithInventory)
							.collect(Collectors.toList()),
					PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
					beerPage.getTotalElements());
		} else {
			beerPagedList = new BeerPagedList(
					beerPage.getContent().stream().map(beerMapper::beerToBeerDto).collect(Collectors.toList()),
					PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
					beerPage.getTotalElements());
		}

		return beerPagedList;
	}
	
	@Cacheable(cacheNames = "beerUpcCache")
    @Override
    public BeerDto getByUpc(String upc) {
        return beerMapper.beerToBeerDto(beerRepository.findByUpc(upc));
    }

}
