package com.spring.beerservice.services;

import java.util.List;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.spring.beerservice.config.JmsConfig;
import com.spring.beerservice.domain.Beer;
import com.spring.beerservice.events.BrewBeerEvent;
import com.spring.beerservice.repositories.BeerRepository;
import com.spring.beerservice.services.inventory.BeerInventoryService;
import com.spring.beerservice.web.mapper.BeerMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {
	
	private final BeerRepository beerRepository;
	private final BeerInventoryService beerInventoryService;
	private final JmsTemplate jmsTemplate;
	private final BeerMapper beerMapper;
	
	@Scheduled(fixedRate= 5000)//every 5 seconds
	public void CheckForLowInventory() {
		List<Beer> beers = beerRepository.findAll();
		
		beers.forEach(beer->{
			Integer invQOH = beerInventoryService.getOnhandInventory(beer.getId());
			log.debug("min on hand is: "+beer.getMinOnHand());
			log.debug("Inventory is: "+invQOH);
			
			if(beer.getMinOnHand()>=invQOH) {
				jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
			}
		});
	}

}
