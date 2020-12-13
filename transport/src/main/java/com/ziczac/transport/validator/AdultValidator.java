package com.ziczac.transport.validator;

import org.springframework.stereotype.Component;

import com.ziczac.transport.validator.annotations.Adult;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

@Component
public class AdultValidator implements ConstraintValidator<Adult, Date> {
	private static final int ADULT_AGE = 18;

	@Override
	public boolean isValid(Date dob, ConstraintValidatorContext constraintValidatorContext) {
		return dob != null && LocalDate.now().minusYears(ADULT_AGE).isAfter(dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	}
}