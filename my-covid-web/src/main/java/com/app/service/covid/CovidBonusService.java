package com.app.service.covid;

import java.util.List;

import com.app.model.CovidCasesBonus;

public interface CovidBonusService {
	
	
	//List<CovidCasesBonus> getCovidBonus();

	List<CovidCasesBonus> bonus() throws Exception;

}
