package com.spring.beerservice.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.spring.beerservice.domain.Beer;
import com.spring.beerservice.repositories.BeerRepository;

@Component
public class BeerLoader implements CommandLineRunner {

	private final BeerRepository beerRepository;

	public BeerLoader(BeerRepository beerRepository) {
		this.beerRepository = beerRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		loadBeerObjects();

	}

	private void loadBeerObjects() {
		if(beerRepository.count()==0) {
			beerRepository.save(Beer.builder()
					.beerName("Mango")
					.beerStyle("IPA")
					.quantityToBrew(200)
					.upc(337094783L)
					.price(new BigDecimal(12.23))
					.build());
			
			beerRepository.save(Beer.builder()
					.beerName("Galaxy Cat")
					.beerStyle("Pale_Ale")
					.quantityToBrew(200)
					.upc(33709477843L)
					.price(new BigDecimal(11.85))
					.build());
		}
		
		
	}

}
