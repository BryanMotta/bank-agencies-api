package com.bank.agencies.repository;

import com.bank.agencies.domain.AgencyResponse;
import org.springframework.data.repository.CrudRepository;

public interface AgenciesRepository extends CrudRepository<AgencyResponse,Long> {
}
