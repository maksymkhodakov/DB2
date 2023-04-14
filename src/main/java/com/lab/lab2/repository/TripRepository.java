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

    @Query(value = "select CONCAT('Id: ', tit.id,' Name: ', tit.name, ' ', 'Notes: ', tit.notes) as result from trips_info_test tit where " +
            "CONCAT('Id: ', tit.id,' Name: ', tit.name, ' ', 'Notes: ', tit.notes) is not null and platform_departure_id in ( " +
            "    ((select p.id from platforms p) " +
            "    EXCEPT " +
            "    (select p.id from platforms p where p.city_id in (select c.id " +
            "                                                      from cities c " +
            "                                                      where c.country_id in (select cou.id from countries cou where cou.name = :countryName)))) " +
            ");", nativeQuery = true)
    List<String> runQuery7(String countryName);


    @Query(value = "select tit.notes from trips_info_test tit " +
            "where tit.notes is not null and tit.name != :tripName " +
            "  and (not exists( " +
            "        (select tra.id from trains tra" +
            "                                inner join train_timetable tt on tra.id = tt.train_id " +
            "                                inner join trips_info_test tri on tri.id = tt.timetable_id " +
            "         where tri.departure_date < :departureDate and tri.arrival_date > :arrivalDate) " +
            "        EXCEPT " +
            "        (select tra.id from trains tra " +
            "         where tra.type = :type)) " +
            "  or exists( " +
            "        (select tra.id from trains tra " +
            "                                inner join train_timetable tt on tra.id = tt.train_id " +
            "                                inner join trips_info_test tri on tri.id = tt.timetable_id " +
            "         where tri.departure_date > :departureDate and tri.arrival_date < :arrivalDate)));", nativeQuery = true)
    List<String> runQuery8(String tripName, String departureDate, String arrivalDate, String type);

    @Query(value = "select CONCAT('Id: ', tit.id,' Name: ', tit.name, ' ', 'Notes: ', tit.notes) as result " +
            "from trips_info_test tit " +
            "where CONCAT('Id: ', tit.id,' Name: ', tit.name, ' ', 'Notes: ', tit.notes) is not null " +
            "  and tit.name <> :tripName and " +
            "      not exists " +
            "          ((select p.id from platforms p inner join trips_info ti on p.id = ti.platform_arrival_id) " +
            "      except " +
            "      (select p.id from platforms p inner join trips_info ti on p.id = ti.platform_arrival_id where ti.name = :tripName)) " +
            "  and not exists( " +
            "    (select p.id from platforms p inner join trips_info ti on p.id = ti.platform_arrival_id where ti.name = :tripName)) " +
            "    except " +
            "    ((select p.id from platforms p inner join trips_info ti on p.id = ti.platform_arrival_id) " +
            ");", nativeQuery = true)
    List<String> runQuery9(String tripName);

    @Query(value = "select CONCAT('Id: ', tit.id,' Name: ', tit.name, ' ', 'Notes: ', tit.notes) as result " +
            "from trips_info_test tit " +
            "where CONCAT('Id: ', tit.id,' Name: ', tit.name, ' ', 'Notes: ', tit.notes) is not null " +
            "  and tit.name = :tripName and " +
            "      not exists " +
            "          ((select platforms.id from platforms) " +
            "          except " +
            "          (select trips_info_test.platform_departure_id from trips_info_test)) " +
            "  and not exists " +
            "      ((select trips_info_test.platform_departure_id from trips_info_test) " +
            "      except " +
            "      (select platforms.id from platforms));", nativeQuery = true)
    List<String> runQuery10(String tripName);

    @Query(value = "select distinct(tit.name) from trips_info_test tit " +
            "                where (tit.platform_arrival_id in " +
            "                      ( " +
            "                          select platform_arrival_id from trips_info_test tit where name = :tripName " +
            "                          UNION " +
            "                          select platform_departure_id from trips_info_test where name = :tripName " +
            "                      ) " +
            "                  or tit.platform_departure_id in " +
            "                      ( " +
            "                          select platform_arrival_id from trips_info_test tit where name = :tripName " +
            "                          UNION " +
            "                          select platform_departure_id from trips_info_test where name = :tripName " +
            "                      ) " +
            "                    ) " +
            "                  and tit.name <> :tripName", nativeQuery = true)
    List<String> runQueryAdditional11(String tripName);

    @Query(value = "select tit.name from trips_info_test tit " +
            "where tit.name <> 'trip_1' and not exists( " +
            "    ( " +
            "        select * from platforms where platforms.trip_id = (select id from trips_info_test where name = :tripName) " +
            "    ) " +
            "    except " +
            "    ( " +
            "        (select * from platforms where platforms.trip_id = tit.platform_departure_id and platforms.trip_id <> (select id from trips_info_test where name = :tripName)) " +
            "    ) " +
            ");", nativeQuery = true)
    List<String> runQueryAdditional12(String tripName);

    @Query(value = "select tit.name from trips_info_test tit " +
            "where tit.name <> 'trip_1' and tit.notes not like '%TRANSITION%' and not exists( " +
            "    ( " +
            "        select * from platforms where platforms.trip_id = (select id from trips_info_test where name = :tripName) " +
            "    ) " +
            "    except " +
            "    ( " +
            "        (select * from platforms where platforms.trip_id = tit.platform_arrival_id and platforms.trip_id in (select id from trips_info_test where name = :tripName)) " +
            "    ) " +
            ") and not exists( " +
            "    ( " +
            "        (select * from platforms where platforms.trip_id = tit.platform_arrival_id and platforms.trip_id in (select id from trips_info_test where name = :tripName)) " +
            "    ) " +
            "    except " +
            "    ( " +
            "        select * from platforms where platforms.trip_id = (select id from trips_info_test where name = :tripName) " +
            "    )" +
            ");", nativeQuery = true)
    List<String> runQueryAdditional13(String tripName);
}
