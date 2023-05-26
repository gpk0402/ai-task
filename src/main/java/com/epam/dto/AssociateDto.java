package com.epam.dto;

import com.epam.entity.Batch;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AssociateDto {
	@Schema(accessMode = AccessMode.READ_ONLY)
	private int id;
	@NotNull(message = "Enter valid name")
	private String name;
	@NotNull(message = "Enter valid email")
	@Email(message= "Email must be in the form of abc@gmail.com")
	private String email;
	@NotNull(message="Enter valid gender")
	@Pattern(regexp = "^(?)(Male|Female)$",message = "Gender should be either male or Female" )
	private String gender;
	@NotNull(message="Enter valid college name")
	private String college;
	@NotNull(message="Enter valid status")
	@Pattern(regexp ="^(?)(Active|InActive)$",message="Status should be either active or inactive")
	private String status;
	@Valid
	private Batch batch;
}
