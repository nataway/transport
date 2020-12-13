package com.ziczac.transport;

import java.time.LocalDate;
import java.time.Month;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TransportApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransportApplication.class, args);
//            LocalDate now = LocalDate.of(2020, Month.JANUARY, 15); // 2015-11-24
//            LocalDate earlier = now.minusMonths(1); // 2015-10-24
//
//            System.out.println(earlier.getMonth()); // java.time.Month = OCTOBER
//            System.out.println(earlier.getMonth().getValue()); // 10
//            System.out.println(earlier.getYear()); // 2015
	}

}
