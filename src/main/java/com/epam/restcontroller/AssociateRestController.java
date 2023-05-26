package com.epam.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.AssociateDto;
import com.epam.service.AssociateService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("associates")
@Slf4j
public class AssociateRestController {
	@Autowired
	AssociateService associateService;
	
	@PostMapping
	public ResponseEntity<AssociateDto> createAssociate(@Valid @RequestBody AssociateDto associateDto)
	{
		log.info("Received request to create associate: {}", associateDto);
		return new ResponseEntity<>(associateService.createAssociate(associateDto),HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<AssociateDto> updateAssociate(@Valid @RequestBody AssociateDto associateDto)
	{
		log.info("Received request to update associate: {}", associateDto);
		return new ResponseEntity<>(associateService.updateAssociate(associateDto),HttpStatus.OK);
	}
	
	@GetMapping("/{gender}")
	public ResponseEntity<List<AssociateDto>> getAssociatesByGender(@PathVariable String gender)
	{
		log.info("Received request to get associates by gender: {}", gender);
		return new ResponseEntity<>(associateService.getAllAssociatesByGender(gender),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteAssociate(@PathVariable int id)
	{
		log.info("Received request to get delete associate with id: {}", id);
		associateService.deleteAssociate(id);
	}
}
