package com.lab.lab2.repository;

import com.lab.lab2.domain.DTO.AvgCrewTrainAnalyticsDTO;
import com.lab.lab2.domain.DTO.CrewMemberDTO;
import com.lab.lab2.domain.entities.CrewMember;
import com.lab.lab2.domain.enums.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {

/*    # Native Query1
    select cm.id, cm.created, cm.updated, age, email, first_name, last_name, phone_number, years_of_experience, crew_id
    from crew_members cm
    inner join crews c on cm.crew_id = c.id
    inner join timetables t on c.timetable_id = t.id
    where t.departure_date > '2023-04-12' and c.rating > 2
    order by cm.age desc limit 1;*/
    @Query(value =
            "select new com.lab.lab2.domain.DTO.CrewMemberDTO(cm.id, " +
                    "       cm.created, " +
                    "       cm.updated," +
                    "       cm.firstName, " +
                    "       cm.lastName," +
                    "       cm.age, " +
                    "       cm.yearsOfExperience," +
                    "       cm.phoneNumber, " +
                    "       cm.email, " +
                    "       cm.crew.id) " +
                    "from CrewMember cm " +
                    "    inner join cm.crew c " +
                    "    inner join c.timetable t " +
                    "where t.departureDate > :time and c.ratingScore > :ratingScore " +
                    "    order by cm.age desc limit 1")
    CrewMemberDTO specialQuery1(LocalDateTime time, Integer ratingScore);

/*     # Query 2 select AVG(cm.years_of_experience), AVG(t.size)
    from crew_members cm
    inner join crews c on cm.crew_id = c.id
    inner join trains t on c.train_id = t.id
    where t.color in ('BLACK', 'GREEN') and t.size < 5;
    */
    @Query("select new com.lab.lab2.domain.DTO.AvgCrewTrainAnalyticsDTO (AVG(cm.yearsOfExperience), AVG(t.size)) " +
            "from CrewMember cm " +
            "inner join cm.crew c " +
            "inner join c.train t " +
            "where t.color in (:colors) and t.size < :limitTrainSize")
    AvgCrewTrainAnalyticsDTO specialQuery2(List<Color> colors, Integer limitTrainSize);
}
