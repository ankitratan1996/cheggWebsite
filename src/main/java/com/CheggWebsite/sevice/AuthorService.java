package com.CheggWebsite.sevice;

import com.CheggWebsite.model.Authors;
import com.CheggWebsite.model.request.CreateBookRequestDto;
import com.CheggWebsite.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Authors createAuthor(CreateBookRequestDto createBookRequestDto)
    {
        Authors authors=createBookRequestDto.AuthorBuilder();
        Optional<Authors>optionalAuthors= authorRepository.findByEmail(createBookRequestDto.getAuthorEmail());
        if(optionalAuthors.isEmpty())
        {
            return saveOrUpdate(authors);
        }
        return optionalAuthors.get();
    }

    private Authors saveOrUpdate(Authors authors) {
        return authorRepository.save(authors);
    }
}
