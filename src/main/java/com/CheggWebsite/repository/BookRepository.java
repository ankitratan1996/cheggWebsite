package com.CheggWebsite.repository;

import com.CheggWebsite.model.Authors;
import com.CheggWebsite.model.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Books,Long> {

    Optional<Authors> findByEmail(String email);

    Optional<Books>findByIsbn(String isbn);

    Page<Books> findByCreatedAtLessThan(LocalDateTime now, PageRequest pageRequest);
}
