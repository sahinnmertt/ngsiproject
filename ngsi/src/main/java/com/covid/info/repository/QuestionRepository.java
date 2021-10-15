package com.covid.info.repository;


import com.covid.info.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Transactional
    @Modifying
    //@Query("update Animal p set p.covidInfo = :covidInfo where p.id = :id")
    //void setCovidInfo(@Param("covidInfo") String covidInfo, @Param("id") int id);
    Question deleteById(int id);
    Question findById(int id);
    //Question findByEarring(int earring);
    //Question findByMotherearringAndFatherearring(int motherearring);
}
