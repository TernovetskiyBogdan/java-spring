package com.parser.parser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.parser.parser.model.Vacancy;
import com.parser.parser.service.VacancyParserService;
import com.parser.parser.service.CurrencyApiClient;
import com.parser.parser.service.ExcelManager;
import com.parser.parser.service.SalaryConverterService;
import com.parser.parser.service.SalaryParserService;
import com.parser.parser.model.Vacancy;



@Controller
public class ListController {
	 
	@Autowired
	private VacancyParserService parserService;
	
	@Autowired
	private SalaryParserService salaryParser;
	
	@Autowired
	private CurrencyApiClient apiClient;

	@Autowired
	private SalaryConverterService converterService;

    @GetMapping("/list")
    public String getVacancy(@RequestParam("search") String search,
                             @RequestParam("city") String city,
                             @RequestParam("exp") String experience,
                             Model model) {
        
        List<Vacancy> vacancies = parserService.parseBySearch(search, experience, city);
        model.addAttribute("vacancies", vacancies);
        model.addAttribute("search", search);
        model.addAttribute("city", city);
        model.addAttribute("experience", experience);
        

        float usdBuyRate = apiClient.getUSDBuyRate();
        

        float averageSalaryUAH = salaryParser.getSalary(); 
        float averageSalaryUSD = converterService.convert(averageSalaryUAH, usdBuyRate);
        
        model.addAttribute("averageSalaryUSD", averageSalaryUSD);

        System.out.print(converterService.convert(salaryParser.getSalary(), apiClient.getUSDBuyRate()));
        
        return "vacancy-list";
    }
}
