package com.epam.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class BatchDto {
	@Schema(accessMode = AccessMode.READ_ONLY)
	private int id;
	@NotNull(message = "Enter valid name")
	@Size(min=3,max=15,message="Name should contain only 3 to 15 characters")
	private String name;
	@NotNull(message = "Enter valid practice domain")
	private String practice;
	@NotNull(message = "Enter valid Start Date")
	private Date startDate;
	@NotNull(message = "Enter valid End Date")
	private Date endDate;
}
