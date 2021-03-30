package com.app.service.covid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.CovidCasesBonusEntity;
import com.app.error.IDNotFoundException;
import com.app.mapper.CovidAreaBonusMapper;
import com.app.model.CovidCasesBonus;
import com.app.repository.covid.CovidCasesBonusRepository;

import fr.xebia.extras.selma.Selma;
import lombok.extern.slf4j.Slf4j;

//complete this as Dependencies Injection Service
@Service
@Slf4j
public class CovidBonusServiceImpl implements CovidBonusService {
	

	@Autowired
	CovidCasesBonusRepository covidCasesBonusRepository;
	
	@Override
	public List<CovidCasesBonus> bonus() throws Exception {
		List<CovidCasesBonus> covidCasesBonus = null;
		log.info("bonus() started"); //log starts
		
		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		List<CovidCasesBonusEntity> covidCaseBonusEntities = covidCasesBonusRepository.findAll();
		
		if (covidCaseBonusEntities == null) {
			throw new IDNotFoundException(0L);
		} else {
			
			covidCasesBonus= new ArrayList<CovidCasesBonus>();
			for (CovidCasesBonusEntity entity : covidCaseBonusEntities) {
				CovidCasesBonus model = mapper.asResource(entity);
				covidCasesBonus.add(model);
				log.info("entity description={}", entity.getDescription());
			}
			log.info(" bonus() return Size={}", covidCaseBonusEntities.size());
		}
		//untuk check debug dalam log.info
		//for (CovidCasesBonus b :covidCasesBonus) {
			//log.info("b--->" + b.getDescription());
			//}
		log.info("bonus() ends"); //log ended
		return covidCasesBonus;
	}

	public int deleteCovid(long id) {
		log.info("deleteCovid started");
		
		Optional<CovidCasesBonusEntity> entityOptional = covidCasesBonusRepository.findById(id);

		log.info("Entity found == " + entityOptional.isPresent());

		if (entityOptional.isPresent()) {
			CovidCasesBonusEntity covidAreaBonusEntity = entityOptional.get();
			covidCasesBonusRepository.delete(covidAreaBonusEntity);
			return 1;
		}
		return 0;
	}
	
	@Override
	public CovidCasesBonus putCovid(CovidCasesBonus covidCasesBonus) {
		log.info("putCovid() started, covidCasesBonus={}", covidCasesBonus);

		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		CovidCasesBonusEntity covidCasesBonusEntity = mapper.asEntity(covidCasesBonus);
		CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidCasesBonusEntity);
		covidCasesBonus = mapper.asResource(savedEntity);
		
		log.info("putCovid() ends, covidCasesBonusSaved={}", covidCasesBonus);
		return covidCasesBonus;
	}
	
	@Override
	public CovidCasesBonus postCovid(CovidCasesBonus covidCasesBonus) {
		log.info("postCovid() started, covidCasesDesc={}", covidCasesBonus);

		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		CovidCasesBonusEntity covidCasesDescEntity = mapper.asEntity(covidCasesBonus);
		CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidCasesDescEntity);
		covidCasesBonus = mapper.asResource(savedEntity);
		
		return covidCasesBonus;
	}
	@Override
	public CovidCasesBonus addCovidBonus(String desc) {
	log.info("addCovidBonus started");
	CovidCasesBonus covidCasesBonus = null;
	
	CovidCasesBonusEntity covidCasesBonusEntity = new CovidCasesBonusEntity();
 
	//desc in db
	covidCasesBonusEntity.setDescription(desc);
	 
	 CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidCasesBonusEntity);

	 CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();

	 covidCasesBonus = mapper.asResource(savedEntity);
	 return covidCasesBonus;

	 }
	
	@Override
	public int deleteCovidBonus(String desc) {
		log.info("deleteCovidBonus() started desc={}", desc);
		
		// complete the implementation below
		int deleted = covidCasesBonusRepository.deleteDescWithCondition(desc);
		
		log.info("deleteCovidBonus() ended deleted={}", deleted);
		return deleted;
	}
}
