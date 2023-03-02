package com.easytravel.forus.repository;

import com.easytravel.forus.domain.CurrencyInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyInfo,String>{
}
