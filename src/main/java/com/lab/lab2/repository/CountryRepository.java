package com.lab.lab2.repository;

import com.lab.lab2.domain.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
/*    #Native Query 6
      # Знайти назву усіх країн чиї міста мають платформи з описом не як у міста City*/
    @Query(value = "select cou.name from countries cou where cou.id in ( " +
            "    select c.country_id from cities c where c.id in ( " +
            "        ((select p.city_id from platforms p) " +
            "        except " +
            "        (select p.city_id from platforms p where p.description = :description)) " +
            "    ) " +
            ");", nativeQuery = true)
    List<String> runQuery6(String description);
}
