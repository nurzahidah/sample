package com.app.service.covid;

import java.util.List;

import com.app.error.GeneralException;
import com.app.model.CovidCasesBonus;

public interface CovidBonusService {
	
	List<CovidCasesBonus> bonus() throws GeneralException;

	int deleteCovid(long id);

	CovidCasesBonus putCovid(CovidCasesBonus covidCasesBonus);

	CovidCasesBonus postCovid(CovidCasesBonus covidCasesBonus);

	int deleteCovidBonus(String desc);

	CovidCasesBonus addCovidBonus(String desc);

}
