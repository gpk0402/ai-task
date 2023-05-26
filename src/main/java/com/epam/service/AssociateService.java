package com.epam.service;

import java.util.List;

import com.epam.dto.AssociateDto;

public interface AssociateService {
	AssociateDto createAssociate(AssociateDto associateDto);
	AssociateDto updateAssociate(AssociateDto asscoaiteDto);
	void deleteAssociate(int id);
	List<AssociateDto> getAllAssociatesByGender(String gender);
}
