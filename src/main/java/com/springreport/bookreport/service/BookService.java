package com.springreport.bookreport.service;

import com.springreport.bookreport.model.BookModel;
import com.springreport.bookreport.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class BookService {


    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookModel> findBookAll(){
        return bookRepository.findAll();
    }
}
