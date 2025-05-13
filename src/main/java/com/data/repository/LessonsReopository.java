package com.data.repository;

import com.data.entity.Lessons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface LessonsReopository extends JpaRepository<Lessons, Integer> {
}
