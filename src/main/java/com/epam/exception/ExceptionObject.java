package com.epam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionObject {
	String timeStamp;
	String error;
	String status;
	String path;
}
