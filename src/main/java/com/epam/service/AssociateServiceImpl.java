package com.epam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.AssociateDto;
import com.epam.entity.Associate;
import com.epam.exception.AssociateException;
import com.epam.repository.AssociateRepository;
import com.epam.repository.BatchRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AssociateServiceImpl implements AssociateService{

	@Autowired
	AssociateRepository associateRepository;
	@Autowired
	BatchRepository batchRepository;
	@Autowired
	ModelMapper modelMapper;
	@Override
	public AssociateDto createAssociate(AssociateDto associateDto) {
		log.info("Entered into AssociateServiceImpl: addBook");
		Associate associate=modelMapper.map(associateDto,Associate.class);
		batchRepository.save(associate.getBatch());
		Associate savedAssociate=associateRepository.save(associate);
		return modelMapper.map(savedAssociate,AssociateDto.class);
	}
	
	@Override
	public AssociateDto updateAssociate(AssociateDto associateDto) {
		log.info("Entered into AssociateServiceImpl: updateAssociate");
		return associateRepository.findById(associateDto.getId()).map(asociate->{
			Associate associate=modelMapper.map(associateDto,Associate.class);
			associate.setId(associateDto.getId());
			Associate updatedAssociate=associateRepository.save(associate);
			return modelMapper.map(updatedAssociate,AssociateDto.class);
		}).orElseThrow(()->new AssociateException("Given Id does not exist"));
	}		
	
	@Override
	public void deleteAssociate(int id) {
		log.info("Entered into AssociateServiceImpl: deleteAssociate");
		associateRepository.deleteById(id);
	}

	@Override
	public List<AssociateDto> getAllAssociatesByGender(String gender) {
		log.info("Entered into AssociateServiceImpl: getAllAssociatesByGender");
		return associateRepository.findAllByGender(gender).stream().map(associate->modelMapper.map(associate,AssociateDto.class)).toList();
	}

}
