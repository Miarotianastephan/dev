package com.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.models.*;;

public interface Codecredit_vMiRep extends JpaRepository<CodecreditMi, Integer> {
    @Query(value = "select * from codecredit_v where code = :code and etats=0" , nativeQuery = true )
    List <Codecredit_vMi> getByCode(int code);
}