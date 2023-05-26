package com.epam.restcontroller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.dto.AssociateDto;
import com.epam.dto.BatchDto;
import com.epam.entity.Associate;
import com.epam.entity.Batch;
import com.epam.service.AssociateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AssociateRestController.class)
public class AssociateRestControllerTest {
	@MockBean
	AssociateService associateService;

	@Autowired
	MockMvc mockMvc;

	AssociateDto associateDto;
	BatchDto batchDto;
	Batch batch;

	@BeforeEach
	void setup() {
		batch = new Batch();
		batch.setId(1);
		batch.setName("test");
		batch.setPractice("java");
		batch.setStartDate(new Date());
		batch.setEndDate(new Date());

		batchDto = new BatchDto();
		batchDto.setEndDate(new Date());
		batchDto.setEndDate(new Date());
		batchDto.setId(1);
		batchDto.setName("test");
		batchDto.setPractice("java");

		associateDto = new AssociateDto();
		associateDto.setBatch(batch);
		associateDto.setCollege("test");
		associateDto.setEmail("test.com");
		associateDto.setGender("Male");
		associateDto.setId(1);
		associateDto.setName("test");
		associateDto.setStatus("Active");

	}

	@Test
	void testCreateAsscociate() throws JsonProcessingException, Exception {
		Mockito.when(associateService.createAssociate(associateDto)).thenReturn(associateDto);
		mockMvc.perform(post("/associates").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(associateDto))).andExpect(status().isCreated()).andReturn();
	}
	
	
	@Test
	void testCreateAsscociateThrowsHttpMessageNotReadable() throws JsonProcessingException, Exception {
		Mockito.when(associateService.createAssociate(associateDto)).thenReturn(associateDto);
		mockMvc.perform(post("/associates").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString("{name:"))).andExpect(status().isBadRequest()).andReturn();
	}
	
	@Test
	void testDeleteAsscoiate() throws JsonProcessingException, Exception {
		Mockito.doNothing().when(associateService).deleteAssociate(1);
		mockMvc.perform(delete("/associates/{id}",1)).andExpect(status().isNoContent()).andReturn();
	}
	
	@Test
	void testUpdateAsscoiate() throws JsonProcessingException, Exception {
		Mockito.when(associateService.updateAssociate(associateDto)).thenReturn(associateDto);
		mockMvc.perform(put("/associates").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(associateDto))).andExpect(status().isOk()).andReturn();
	}
	
	@Test
	void testGetAssociatesByGender() throws JsonProcessingException, Exception {
		Mockito.when(associateService.getAllAssociatesByGender("Male")).thenReturn(List.of(associateDto));
		mockMvc.perform(get("/associates/{gender}","Male")).andExpect(status().isOk()).andReturn();
	}
	
	
	
	

}
