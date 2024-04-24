package com.CheggWebsite.model.request;

import com.CheggWebsite.model.Authors;
import com.CheggWebsite.model.Books;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequestDto {

    @NotBlank
    String bookName;
    @NotBlank
    String isbn;
    @NotBlank
    Double bookAmount;
    @NotBlank
    String authorName;
    @NotBlank
    String authorEmail;

    public Authors AuthorBuilder(){
        return Authors.builder().name(authorName).email(authorEmail).build();
    }

    public Books BookBuilder()
    {
        return Books.builder().name(bookName).isbn(isbn).amount(bookAmount).associatedAuthor(AuthorBuilder()).build();
    }


}
