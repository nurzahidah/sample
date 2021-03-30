package com.app.service.covid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.error.IDNotFoundException;
import com.app.mapper.CovidAreaDescMapper;
import com.app.mapper.CovidCasesAreaMapper;
import com.app.repository.covid.CovidCasesDescRepository;
import com.app.repository.covid.CovidCasesRepository;
import com.app.entity.CovidCasesDescEntity;
import com.app.entity.CovidCasesAreaEntity;
import com.app.model.CovidCasesArea;
import com.app.model.CovidCasesDesc;

import fr.xebia.extras.selma.Selma;

@Service

public class CovidServiceImpl implements CovidService {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CovidServiceImpl.class);

	@Autowired
	CovidCasesRepository covidCasesRepository;
	
	@Autowired
	CovidCasesDescRepository covidCasesDescRepository;

	@Override
	public List<CovidCasesArea> getCovid() {
		log.info("getCovid started");
		CovidCasesAreaMapper mapper = Selma.builder(CovidCasesAreaMapper.class).build();
		List<CovidCasesAreaEntity> covidCaseEntities = covidCasesRepository.findAll();
		List<CovidCasesArea> covidCasesAreaList = new ArrayList<CovidCasesArea>();
		if (covidCaseEntities == null) {
			throw new IDNotFoundException(0L);
		} else {

			for (CovidCasesAreaEntity covidCasesEntity : covidCaseEntities) {
				CovidCasesArea covidCasesArea = mapper.asResource(covidCasesEntity);
				covidCasesAreaList.add(covidCasesArea);
				log.info("covidCasesEntity total Cases={}", covidCasesEntity.getCases());
			}
			log.info(" getCovid() return Size={}", covidCaseEntities.size());
		}

		return covidCasesAreaList;

	}

	@Override
	public List<CovidCasesDesc> getCovidDesc() {
		log.info("getCovidDesc started");
		CovidAreaDescMapper mapper = Selma.builder(CovidAreaDescMapper.class).build();
		List<CovidCasesDescEntity> covidCaseDescEntities = covidCasesDescRepository.findAll();
		List<CovidCasesDesc> covidCasesDescList = new ArrayList<CovidCasesDesc>();
		if (covidCaseDescEntities == null) {
			throw new IDNotFoundException(0L);
		} else {

			for (CovidCasesDescEntity entity : covidCaseDescEntities) {
				CovidCasesDesc model = mapper.asResource(entity);
				covidCasesDescList.add(model);
				log.info("entity total desc={}", entity.getDescription());
			}
			log.info(" getCovidDesc() return Size={}", covidCaseDescEntities.size());
		}

		return covidCasesDescList;

	}
	
	// TODO: Related to Practical 4 (Add)
	@Override
	public CovidCasesDesc addCovid(String desc) {
	log.info("addCovid started");
	CovidCasesDesc covidCasesDesc = null;
	
	//create row data..kena ada entity baru
	CovidCasesDescEntity covidAreaDescEntity = new CovidCasesDescEntity();

	//desc in db
	 covidAreaDescEntity.setDescription(desc);
	 
	 //return savedEntitiy
	 CovidCasesDescEntity savedEntity = covidCasesDescRepository.save(covidAreaDescEntity);

	 CovidAreaDescMapper mapper = Selma.builder(CovidAreaDescMapper.class).build();

	 covidCasesDesc = mapper.asResource(savedEntity);
	return covidCasesDesc;

	 }

	// TODO: Related to Practical 4 (Delete)
	public int deleteCovid(long id) {
		log.info("deleteCovid started");
		
		Optional<CovidCasesDescEntity> entityOptional = covidCasesDescRepository.findById(id);

		log.info("Entity found == " + entityOptional.isPresent());

		if (entityOptional.isPresent()) {
			CovidCasesDescEntity covidAreaDescEntity = entityOptional.get();
			covidCasesDescRepository.delete(covidAreaDescEntity);
			return 1;
		}
		return 0;
	}
	@Override
	public CovidCasesDesc putCovid(CovidCasesDesc covidCasesDesc) {
		log.info("putCovid() started, covidCasesDesc={}", covidCasesDesc);

		CovidAreaDescMapper mapper = Selma.builder(CovidAreaDescMapper.class).build();
		CovidCasesDescEntity covidCasesDescEntity = mapper.asEntity(covidCasesDesc);
		CovidCasesDescEntity savedEntity = covidCasesDescRepository.save(covidCasesDescEntity);
		covidCasesDesc = mapper.asResource(savedEntity);
		
		log.info("putCovid() ends, covidCasesDescSaved={}", covidCasesDesc);
		return covidCasesDesc;
	}
	
	@Override
	public CovidCasesDesc postCovid(CovidCasesDesc covidCasesDesc) {
		log.info("postCovid() started, covidCasesDesc={}", covidCasesDesc);

		CovidAreaDescMapper mapper = Selma.builder(CovidAreaDescMapper.class).build();
		CovidCasesDescEntity covidCasesDescEntity = mapper.asEntity(covidCasesDesc);
		CovidCasesDescEntity savedEntity = covidCasesDescRepository.save(covidCasesDescEntity);
		covidCasesDesc = mapper.asResource(savedEntity);
		
		return covidCasesDesc;
	}

	@Override
	public int deleteCovidSoap(String desc) {
		log.info("deleteCovidSoap() started desc={}", desc);
		
		// complete the implementation below
		int deleted = covidCasesDescRepository.deleteDescWithCondition(desc);
		
		log.info("deleteCovidSoap() ended deleted={}", deleted);
		return deleted;
	}
}
