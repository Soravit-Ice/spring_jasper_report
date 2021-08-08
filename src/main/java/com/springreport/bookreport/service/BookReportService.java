package com.springreport.bookreport.service;

import com.springreport.bookreport.model.BookModel;
import com.springreport.bookreport.repository.BookRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookReportService {

    private final BookRepository bookRepository;

    public BookReportService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String exReport(String reportFormat) throws FileNotFoundException, JRException {

        String path = "D:\\Sourcecode Springboost\\All Project Spring boost\\bookreport";
        List<BookModel> data = bookRepository.findAll();
        File file = ResourceUtils.getFile("classpath:bookreport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
        Map<String , Object> parameters = new HashMap<>();
        parameters.put("createBy","Soravit-Ice");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);

        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\reporthtml.html");
        }else if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\reportpdf.pdf");
        }
        return "report created By Soravit-Ice : "+path;


    }
}
