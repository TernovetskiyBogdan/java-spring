package com.parser.parser.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.parser.parser.model.ExperienceLevel;
import com.parser.parser.model.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VacancyParserService {
    private static final String LINK = "https://jobs.dou.ua/vacancies/?search=";

    public List<Vacancy> parseBySearch(String search, String experience, String city) {
        if (!isExperienceValid(experience)) {
            throw new IllegalArgumentException("The wrong experience is given");
        }

        String preparedSearch = prepareSearch(search);
        String url = concatURL(preparedSearch, experience, city);

        try {
            Document doc = Jsoup.connect(url).get();
            Elements vacancies = doc.select(".l-vacancy");
            
            List<Vacancy> items = new ArrayList<>();
            for (Element vacancy : vacancies) {
                getVacancyFromHTML(vacancy).ifPresent(items::add);
            }
            return items;
        } catch (IOException e) {
            System.err.println("Error while connecting to DOU: " + e.getMessage());
        }
        return List.of(); 
    }

    private Optional<Vacancy> getVacancyFromHTML(Element vacancy) {
        try {
            String date = vacancy.select(".date").text();
            Element linkTag = vacancy.select(".vt").first();
            if (linkTag == null) return Optional.empty();
            
            String reference = linkTag.attr("href");
            String name = linkTag.text();
            String location = vacancy.select(".cities").text();
            String company = vacancy.select(".company").text();
            
            return Optional.of(new Vacancy(name, date, location, company, reference));
        } catch (Exception e) {
            System.err.println("Error parsing vacancy: " + e.getMessage());
            return Optional.empty();
        }
    }

    private String prepareSearch(String search) {
        return search.trim().replace(" ", "+");
    }

    private boolean isExperienceValid(String userExperience) {
        return ExperienceLevel.getByExperience(userExperience).isPresent();
    }

    private String concatURL(String position, String experience, String city) {
        return String.format("%s%s&exp=%s&city=%s", LINK, position, experience, city);
    }
}
