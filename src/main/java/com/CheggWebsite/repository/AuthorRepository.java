package com.CheggWebsite.repository;

import com.CheggWebsite.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Authors,Long> {

    Optional<Authors> findByEmail(String email);
}
