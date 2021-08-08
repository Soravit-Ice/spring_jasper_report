package com.springreport.bookreport.controller;

import com.springreport.bookreport.model.BookModel;
import com.springreport.bookreport.service.BookReportService;
import com.springreport.bookreport.service.BookService;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;
    private final BookReportService bookReportService;

    public BookController(BookService bookService, BookReportService bookReportService) {
        this.bookService = bookService;
        this.bookReportService = bookReportService;
    }

    @GetMapping("/getBookAll")
    public ResponseEntity<?> findBookAll(){
       List<BookModel> data =  bookService.findBookAll();
       return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/getBookReport")
    public String reportBook(@RequestParam(name = "format") String format) throws FileNotFoundException, JRException {
        if(StringUtils.isNotBlank(format)) {
           return bookReportService.exReport(format);
        }else{
            return "format can't be null";
        }
    }
}
