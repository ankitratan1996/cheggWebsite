package com.CheggWebsite.sevice;

import com.CheggWebsite.exception.AuthorNotFoundException;
import com.CheggWebsite.exception.InvalidInputException;
import com.CheggWebsite.model.Authors;
import com.CheggWebsite.model.Books;
import com.CheggWebsite.model.enums.BookStatus;
import com.CheggWebsite.model.enums.StatusCode;
import com.CheggWebsite.model.request.CreateBookRequestDto;
import com.CheggWebsite.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Books CreateBook(CreateBookRequestDto createBookRequestDto)
    {
        Optional<Authors> author = bookRepository.findByEmail(createBookRequestDto.getAuthorEmail());
        if(Objects.isNull(author))
        {
            throw new AuthorNotFoundException(StatusCode.AUTHOR_NOT_FOUND);
        }
        Books book = createBookRequestDto.BookBuilder();
        book.setAssociatedAuthor(author.get());
        return saveOrUpdate(book);
    }

    private Books saveOrUpdate(Books book) {
        return bookRepository.save(book);
    }

    public  Books findBooksByIsbn(String isbn)
    {
        Optional<Books> booksOptional = bookRepository.findByIsbn(isbn);
        if(booksOptional.isEmpty())
        {
            throw  new InvalidInputException(StatusCode.INVALID_INPUT_EXCEPTION);
        }
        return booksOptional.get();
    }
    public List<Books> issueBookAndPersist(List<Books>booksList)
    {
        booksList.forEach(books -> {books.setBookStatus(BookStatus.ISSUED);});
        return booksList;
    }


    public void makeBooksAvailable(List<Books> booksList) {
        booksList.forEach(book -> {
            book.setBookStatus(BookStatus.AVAILABLE);
            saveOrUpdate(book);
        });
    }


    public Page<Books> fetchAllBooks(Integer pageNumber) {
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        PageRequest pageRequest = PageRequest.of(pageNumber, 20);
        return bookRepository.findByCreatedAtLessThan(LocalDateTime.now(), pageRequest);
    }
}
