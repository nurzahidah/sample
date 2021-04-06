package com.app.mining.service.covid.api;

import java.util.List;

import com.app.model.CovidCasesArea;

public interface CovidMiningAPITotalCases {

	String doMining();
	
	String getTotalfromDB();
	
	List<CovidCasesArea> getLast2RecordsMY();
	
}
