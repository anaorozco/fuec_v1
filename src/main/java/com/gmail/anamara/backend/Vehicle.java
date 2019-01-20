package com.gmail.anamara.backend;

import java.time.LocalDate;

public class Vehicle extends Review {

	public Vehicle() {
		// TODO Auto-generated constructor stub
	}

	public Vehicle(int score, String name, LocalDate date, Category category, int count) {
		super(score, name, date, category, count);
		// TODO Auto-generated constructor stub
	}

	public Vehicle(Review other) {
		super(other);
		// TODO Auto-generated constructor stub
	}

}
