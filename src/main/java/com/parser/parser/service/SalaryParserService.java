package com.parser.parser.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class SalaryParserService {
	public final static String link = "https://www.work.ua/ru/salary-full+stack+software+engineer/";
	
    public float getSalary() {
        try {
            Document doc = Jsoup.connect(link).get();            
            Elements elements = doc.select("#js-content > section:nth-child(1) > div.card > div.row.sm\\:mt-lg.sm\\:flex > div.col-sm-6.b-wrap-pr.mt-lg.sm\\:flex > div > div");

            return Float.parseFloat(elements.get(0).text().replaceAll("[^\\d.]", ""));
 
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error geting data", e);
        }
    }
}
