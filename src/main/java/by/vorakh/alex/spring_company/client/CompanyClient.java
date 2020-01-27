package by.vorakh.alex.spring_company.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import by.vorakh.alex.spring_company.model.outsource.EmployeeOutsource;

@Component
public class CompanyClient {
    static final String URL_EMPLOYEES = "http://localhost:8082/random-employee/";
    
    public EmployeeOutsource getRandomEmployee(String jobTitleName) {
	RestTemplate restTemplate = new RestTemplate();
	return restTemplate.getForObject(URL_EMPLOYEES + jobTitleName, 
		EmployeeOutsource.class);
    }

}
