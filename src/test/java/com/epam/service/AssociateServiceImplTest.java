package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.dto.AssociateDto;
import com.epam.dto.BatchDto;
import com.epam.entity.Associate;
import com.epam.entity.Batch;
import com.epam.exception.AssociateException;
import com.epam.repository.AssociateRepository;
import com.epam.repository.BatchRepository;

@ExtendWith(MockitoExtension.class)
class AssociateServiceImplTest {
	@Mock
	AssociateRepository associateRepository;
	@Mock
	BatchRepository batchRepository;
	@Mock
	ModelMapper modelMapper;
	@InjectMocks
	AssociateServiceImpl associateServiceImpl;

	Associate associate;
	AssociateDto associateDto;
	Batch batch;
	BatchDto batchDto;

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

		associate = new Associate();
		associate.setId(1);
		associate.setName("test");
		associate.setEmail("test@epam.com");
		associate.setCollege("test");
		associate.setGender("Male");
		associate.setStatus("Active");
		associate.setBatch(batch);

		associateDto = new AssociateDto();
		associateDto.setBatch(batch);
		associateDto.setCollege("test");
		associateDto.setEmail("test.com");
		associateDto.setGender("Male");
		associateDto.setId(1);
		associateDto.setName("test");
		associateDto.setStatus("Active");

//		batch = Batch.builder().name("test").endDate(null).startDate(null).practice("test practice").id(1).build();
//		associate = Associate.builder().name("test").email("test@epam.com").college("test").gender("Male")
//				.status("Active").batch(batch).id(1).build();
//		associateDto = modelMapper.map(associate, AssociateDto.class);
//		batchDto = modelMapper.map(batch, BatchDto.class);
	}

	@Test
	void testCreateAssociate() {
		Mockito.when(modelMapper.map(associateDto, Associate.class)).thenReturn(associate);
		Mockito.when(batchRepository.save(batch)).thenReturn(batch);
		Mockito.when(associateRepository.save(associate)).thenReturn(associate);
		Mockito.when(modelMapper.map(associate, AssociateDto.class)).thenReturn(associateDto);
		AssociateDto savedAssociateDto = associateServiceImpl.createAssociate(associateDto);
		assertEquals(savedAssociateDto, associateDto);
		Mockito.verify(modelMapper).map(associateDto, Associate.class);
		Mockito.verify(batchRepository).save(batch);
		Mockito.verify(associateRepository).save(associate);
	}

	@Test
	void testDeleteAssociate() {
		Mockito.doNothing().when(associateRepository).deleteById(1);
		associateServiceImpl.deleteAssociate(1);
		Mockito.verify(associateRepository).deleteById(1);
	}

	@Test
	void testUpdateAssociate() {
		Mockito.when(associateRepository.findById(associateDto.getId())).thenReturn(Optional.of(associate));
		Mockito.when(modelMapper.map(associateDto, Associate.class)).thenReturn(associate);
		Mockito.when(associateRepository.save(associate)).thenReturn(associate);
		Mockito.when(modelMapper.map(associate, AssociateDto.class)).thenReturn(associateDto);
		assertEquals(associateDto, associateServiceImpl.updateAssociate(associateDto));
		Mockito.verify(modelMapper).map(associateDto, Associate.class);
		Mockito.verify(associateRepository).save(associate);
		Mockito.verify(associateRepository).findById(1);
		Mockito.verify(modelMapper).map(associate, AssociateDto.class);

	}

	@Test
	void testUpdateAssociateFails() {
		Mockito.when(associateRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(AssociateException.class, () -> associateServiceImpl.updateAssociate(associateDto));
		Mockito.verify(associateRepository).findById(1);
	}

	@Test
	void testGetAllAssociatesById() {
		Associate associateMale = new Associate();
		associateMale.setBatch(associate.getBatch());
		associateMale.setCollege(associate.getCollege());
		associateMale.setEmail(associate.getEmail());
		associateMale.setGender(associate.getGender());
		associateMale.setId(associate.getId());
		associateMale.setName(associate.getName());
		associateMale.setStatus(associate.getStatus());
		Mockito.when(associateRepository.findAllByGender("Male")).thenReturn(List.of(associate));
		Mockito.when(modelMapper.map(associate, AssociateDto.class)).thenReturn(associateDto);
		assertEquals(List.of(associateDto), associateServiceImpl.getAllAssociatesByGender("Male"));
		Mockito.verify(associateRepository).findAllByGender("Male");
		Mockito.verify(modelMapper).map(associate, AssociateDto.class);
	}
}
