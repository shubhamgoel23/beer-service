package com.spring.beerservice.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.beerservice.repositories.BeerRepository;
import com.spring.beerservice.services.BeerService;
import com.spring.beerservice.web.model.BeerDto;
import com.spring.beerservice.web.model.BeerStyleEnum;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
public class BeerControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
    BeerRepository beerRepository;
	
	@MockBean
    BeerService beerService;

	@Test
	void getBeerById() throws Exception {
//		mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk());
		
//		given(beerService.getById(any(), anyBoolean())).willReturn(getValidBeerDto());

        mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/beer", pathParameters(
                        parameterWithName("beerId").description("UUID of desired beer to get.")
                )));
        
        //example for query parameter documentation
//        mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
//                .param("iscold", "yes")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(document("v1/beer",
//                        pathParameters (
//                                parameterWithName("beerId").description("UUID of desired beer to get.")
//                        ),
//                        requestParameters(
//                                parameterWithName("iscold").description("Is Beer Cold Query param")
//                        )));
        
        
        //with response
//        mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
//                .param("iscold", "yes")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(document("v1/beer",
//                        pathParameters (
//                                parameterWithName("beerId").description("UUID of desired beer to get.")
//                        ),
//                        requestParameters(
//                                parameterWithName("iscold").description("Is Beer Cold Query param")
//                        ),
//                        responseFields(
//                                fieldWithPath("id").description("Id of Beer"),
//                                fieldWithPath("version").description("Version number"),
//                                fieldWithPath("createdDate").description("Date Created"),
//                                fieldWithPath("modifiedDate").description("Date Updated"),
//                                fieldWithPath("beerName").description("Beer Name"),
//                                fieldWithPath("beerStyle").description("Beer Style"),
//                                fieldWithPath("upc").description("UPC of Beer"),
//                                fieldWithPath("price").description("Price"),
//                                fieldWithPath("quantityOnHand").description("Quantity On hand")
//                        )));

	}
	
	@Test
	void saveNewBeer() throws Exception {
		BeerDto beerDto =  BeerDto.builder()
							.beerName("Mango Punch")
							.beerStyle(BeerStyleEnum.GOSE)
							.upc("2378L")
							.price(new BigDecimal(215.76))
							.build();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
							
		mockMvc.perform(post("/api/v1/beer/").contentType(MediaType.APPLICATION_JSON).content(beerDtoJson))
		.andExpect(status().isCreated());

	}

}
