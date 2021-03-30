package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.CovidCasesBonus;
import com.app.model.CovidCasesDesc;
import com.app.service.covid.CovidBonusService;
import com.app.service.covid.api.CovidMiningAPITotalCases;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CovidBonusController {
	
	
	private final static String GET_MY_BONUS = "/covid/get/bonus";
	
	private final static String ADD_COVID = "/covid/add/bonus";
	
	private final static String PUT_API = "/covid/put/bonus";
	
	private final static String POST_API = "/covid/post/bonus";
	
	private final static String DELETE_COVID = "/covid/delete/bonus";
	
	private final static String DELETE_COVID_SOAPUI = "/covid/delete/bonus/soap";
	
	@Autowired
	CovidBonusService covidBonusService;
	
	@Autowired
	CovidMiningAPITotalCases covidMiningAPITotalCases;
	
	@GetMapping(GET_MY_BONUS)
	List<CovidCasesBonus> bonus() throws Exception {
		List<CovidCasesBonus> covidCasesBonus = null;
		log.info("bonus() started");

		try {
			covidCasesBonus= covidBonusService.bonus();
			if (covidCasesBonus == null) {
				throw new Exception("No bonus yet");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("bonus() exception " + e.getMessage());
			throw new Exception(e);
		}

		log.info(GET_MY_BONUS + " return = {}" + covidCasesBonus);
		return covidCasesBonus;
	}
	@GetMapping(ADD_COVID)
	CovidCasesBonus addCovid(@RequestParam(required = true) String desc) throws Exception {
		log.info("addCovidBonus() started={}", desc);

		CovidCasesBonus CovidCasesBonus = null;
		try {

		 if (desc == null || desc.equals("undefined") || desc.equals("")) {
		throw new NullPointerException(ADD_COVID + ", desc is null or empty");
		}
		 CovidCasesBonus = covidBonusService.addCovidBonus(desc);
		} 
		catch (Exception e) {
		// TODO Auto-generated catch block
		log.error("add() exception " + e.getMessage());
		throw new Exception(e.getMessage());
		}

		return CovidCasesBonus;
	}
	@DeleteMapping(DELETE_COVID)
	int deleteCovid(@RequestParam(required = true) long id) throws Exception {
		log.info("deleteCovid() started id={}", id);
		
		int num = 0;
	
		try {
			num = covidBonusService.deleteCovid(id);
			if(num==1)
				return num;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("deleteCovid() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		return num;
		
	}
	@PutMapping(PUT_API)
	CovidCasesBonus putCovid(@RequestBody CovidCasesBonus covidCasesBonus) throws RuntimeException {
		log.info("putCovid() started, covidCasesBonus={}", covidCasesBonus);
		
		return covidBonusService.putCovid(covidCasesBonus);
	}
	
	@PostMapping(POST_API)
	CovidCasesBonus postCovid(@RequestBody CovidCasesBonus covidCasesBonus) {
		log.info("postCovid() started, covidCasesBonus={}", covidCasesBonus);
		
		return covidBonusService.postCovid(covidCasesBonus);
		//return should be the saved CovidCasesDesc with values..refer covidService
	}
	
	@DeleteMapping(DELETE_COVID_SOAPUI)

	int deleteCovidBonus(@RequestParam(required = true) String desc) throws Exception {
		log.info("deleteCovidBonus() started desc={}", desc);
		
		log.info("deleteCovidBonus() ended");
		return covidBonusService.deleteCovidBonus(desc);
		//return 0;
	}
	
}
