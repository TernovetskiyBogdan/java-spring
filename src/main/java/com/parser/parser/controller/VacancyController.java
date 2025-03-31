package com.parser.parser.controller;

import com.parser.parser.model.Vacancy;
import com.parser.parser.service.VacancyParserService;
import com.parser.parser.service.CurrencyApiClient;
import com.parser.parser.service.ExcelManager;
import com.parser.parser.service.SalaryConverterService;
import com.parser.parser.service.SalaryParserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VacancyController {

	@Autowired
	VacancyParserService parserService;
	
	@Autowired
	SalaryParserService salaryParser;
	
	@Autowired
	CurrencyApiClient apiClient;

	@Autowired
	SalaryConverterService converterService;
	
    @GetMapping("/vacancy")
    public List<Vacancy> getVacancy(@RequestParam("search") String search, @RequestParam("city") String city, @RequestParam("exp") String experience) {
        
        List<Vacancy> vacancies = parserService.parseBySearch(search, experience, city);        
        return vacancies;
    }

    @PostMapping("/export")
    public ResponseEntity<byte[]> getExcelFile(@RequestBody List<Vacancy> vacancies) {
        byte[] createdFile = new ExcelManager().createFileFromVacancies(vacancies);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "vacancy.xlsx");
        return  new ResponseEntity<>(createdFile, headers, HttpStatus.OK);
    }
}
