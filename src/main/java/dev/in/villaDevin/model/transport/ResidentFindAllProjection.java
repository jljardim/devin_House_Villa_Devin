package dev.in.villaDevin.model.transport;

import java.math.BigDecimal;
import java.util.Date;

public interface ResidentFindAllProjection {
	
	String getUuid();
	String getName();
	String getLastName();
	Date getDateNasc();
	BigDecimal getIncome();
	String getCpf();
			
}
