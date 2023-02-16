package com.lab.lab2.repository;

import com.lab.lab2.domain.DTO.TrainSizesDTO;
import com.lab.lab2.domain.entities.Train;
import com.lab.lab2.domain.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Long> {
/*    # Native Query 3
    select tr.name from trains tr
    inner join crews c on tr.id = c.train_id
    where tr.type = 'MILITARY' and c.id in
    (select c.id from crews c where c.rating BETWEEN 2 AND 7);
    */
    @Query("select tr.name from Train tr " +
            "inner join tr.crews c " +
            "where tr.type = :type and c.id in " +
            "(select c.id from Crew c where c.ratingScore BETWEEN :min AND :max)")
    List<String> runQuery3(Type type, Integer min, Integer max);

/*    #Native Query 4
    select MAX(tr.size), MIN(tr.size) from trains tr
    inner join crews c on tr.id = c.train_id
    where c.rating > 5);*/
    @Query("select new com.lab.lab2.domain.DTO.TrainSizesDTO(MAX(tr.size), MIN(tr.size)) from Train tr " +
            "inner join tr.crews c " +
            "where c.ratingScore > :rating")
    TrainSizesDTO runQuery4(Integer rating);
}
