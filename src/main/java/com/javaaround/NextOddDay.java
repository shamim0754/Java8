package com.javaaround;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextOddDay implements TemporalAdjuster {
	public Temporal adjustInto(Temporal temporalInput) {
		LocalDate localDate = LocalDate.from(temporalInput);
		int day = localDate.getDayOfMonth();
		if (day % 2 == 0) 
			localDate = localDate.plusDays(1);
		 else 
			localDate = localDate.plusDays(2);

		return temporalInput.with(localDate);
	}
}	