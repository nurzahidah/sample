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

import com.app.error.ControllerException;
import com.app.model.CovidCasesBonus;
import com.app.service.covid.CovidBonusService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CovidBonusController {
	
	
	private static final  String GET_MY_BONUS = "/covid/get/bonus";
	
	private static final  String ADD_COVID = "/covid/add/bonus";
	
	private static final  String PUT_API = "/covid/put/bonus";
	
	private static final  String POST_API = "/covid/post/bonus";
	
	private static final  String DELETE_COVID = "/covid/delete/bonus";
	
	private static final  String DELETE_COVID_SOAPUI = "/covid/delete/bonus/soap";
	
	@Autowired
	CovidBonusService covidBonusService;
	
	@GetMapping(GET_MY_BONUS)
	public List<CovidCasesBonus> bonus() throws ControllerException {
		List<CovidCasesBonus> covidCasesBonus = null;
		log.info("bonus() started");

		try {
			covidCasesBonus= covidBonusService.bonus();
			if (covidCasesBonus == null) {
				throw new com.app.error.ControllerException(GET_MY_BONUS, "No bonus yet");
			}
		} catch (Exception e) {
			log.error("bonus() exception " + e.getMessage());
			throw new com.app.error.ControllerException(GET_MY_BONUS, e.getMessage());
		}

		log.info(GET_MY_BONUS + " return = {}" + covidCasesBonus);
		return covidCasesBonus;
	}
	@GetMapping(ADD_COVID)
	public CovidCasesBonus addCovid(@RequestParam(required = true) String desc) throws ControllerException {
		log.info("addCovidBonus() started={}", desc);

		CovidCasesBonus covidCasesBonus = null;
		try {

		 if (desc == null || desc.equals("undefined") || desc.equals("")) {
		throw new NullPointerException(ADD_COVID + ", desc is null or empty");
		}
		 covidCasesBonus = covidBonusService.addCovidBonus(desc);
		} 
		catch (Exception e) {
		log.error("add() exception " + e.getMessage());
		throw new com.app.error.ControllerException(ADD_COVID, e.getMessage());
		}

		return covidCasesBonus;
	}
	@DeleteMapping(DELETE_COVID)
	public int deleteCovid(@RequestParam(required = true) long id) throws ControllerException {
		log.info("deleteCovid() started id={}", id);
		
		int num = 0;
	
		try {
			num = covidBonusService.deleteCovid(id);
			if(num==1)
				return 1;

		} catch (Exception e) {
			log.error("deleteCovid() exception " + e.getMessage());
			throw new com.app.error.ControllerException(DELETE_COVID, e.getMessage());
		}

		return 0;
		
	}
	@PutMapping(PUT_API)
	public CovidCasesBonus putCovid(@RequestBody CovidCasesBonus covidCasesBonus) throws ControllerException {
		log.info("putCovid() started, covidCasesBonus={}", covidCasesBonus);
		
		return covidBonusService.putCovid(covidCasesBonus);
	}
	
	@PostMapping(POST_API)
	public CovidCasesBonus postCovid(@RequestBody CovidCasesBonus covidCasesBonus) {
		log.info("postCovid() started, covidCasesBonus={}", covidCasesBonus);
		
		return covidBonusService.postCovid(covidCasesBonus);
		//return should be the saved CovidCasesDesc with values..refer covidService
	}
	
	@DeleteMapping(DELETE_COVID_SOAPUI)

	public int deleteCovidBonus(@RequestParam(required = true) String desc) throws ControllerException {
		log.info("deleteCovidBonus() started desc={}", desc);
		
		log.info("deleteCovidBonus() ended");
		return covidBonusService.deleteCovidBonus(desc);
	}
	
}
