package com.app.repository.covid;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.CovidCasesDescEntity;
																//define entity name/long is a unique id
public interface CovidCasesDescRepository  extends JpaRepository<CovidCasesDescEntity, Long>  {

}
