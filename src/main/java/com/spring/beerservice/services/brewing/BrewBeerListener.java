package com.spring.beerservice.services.brewing;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.beerservice.config.JmsConfig;
import com.spring.beerservice.domain.Beer;
import com.spring.beerservice.repositories.BeerRepository;
import com.spring.common.events.BrewBeerEvent;
import com.spring.common.events.NewInventoryEvent;
import com.spring.common.model.BeerDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

	private final BeerRepository beerRepository;
	private final JmsTemplate jmsTemplate;

	@Transactional
	@JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
	public void listen(BrewBeerEvent event) {
		BeerDto beerDto = event.getBeerDto();

		Beer beer = beerRepository.getOne(beerDto.getId());

		beerDto.setQuantityOnHand(beer.getQuantityToBrew());

		NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

		log.debug("Brewed beer " + beer.getMinOnHand() + " : QOH: " + beerDto.getQuantityOnHand());

		jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
	}

}
