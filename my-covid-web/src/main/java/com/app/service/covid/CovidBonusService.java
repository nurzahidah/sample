package com.app.service.covid;

import java.util.List;

import com.app.model.CovidCasesBonus;
import com.app.model.CovidCasesDesc;

public interface CovidBonusService {
	
	
	//List<CovidCasesBonus> getCovidBonus();

	List<CovidCasesBonus> bonus() throws Exception;

	int deleteCovid(long id);

	CovidCasesBonus putCovid(CovidCasesBonus covidCasesBonus);

	CovidCasesBonus postCovid(CovidCasesBonus covidCasesBonus);

	int deleteCovidBonus(String desc);

	CovidCasesBonus addCovidBonus(String desc);

}
