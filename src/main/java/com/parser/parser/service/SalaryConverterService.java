package com.parser.parser.service;

import org.springframework.stereotype.Service;

@Service
public class SalaryConverterService {

	public float convert(Float price, float rate) {
		return price / rate;
	}
	
}
