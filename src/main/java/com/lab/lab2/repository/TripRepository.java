package com.lab.lab2.repository;

import com.lab.lab2.domain.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
/*# Native Query 5
    select tit.notes from trips_info_test tit
    inner join platforms p on tit.platform_arrival_id = p.id
    where tit.platform_arrival_id in (select p.id from platforms p where p.description is not null
            and p.description not like '%Vivamus%');*/
    @Query("select t.notes from Trip t " +
            "inner join t.platformArrival p " +
            "where p.id in (select p.id from Platform p " +
            "where p.description is not null and p.description not like CONCAT('%',:description,'%'))")
    List<String> runQuery5(String description);
}
