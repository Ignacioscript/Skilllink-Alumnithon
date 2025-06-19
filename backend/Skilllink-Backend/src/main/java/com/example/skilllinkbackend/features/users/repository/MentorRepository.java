package com.example.skilllinkbackend.features.users.repository;

import com.example.skilllinkbackend.features.users.entities.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {

//    @Query("SELECT m FROM Mentor m JOIN m.skills s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :skillName, '%'))")
//    List<Mentor> findMentorsBySkillName(@Param("skillName") String skillName);

}
