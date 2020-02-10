package by.vorakh.alex.spring_company.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import by.vorakh.alex.spring_company.model.view_model.CompanyViewModel;
import by.vorakh.alex.spring_company.model.view_model.EmployeeViewModel;
import by.vorakh.alex.spring_company.repository.entity.Company;

@Component
public final class CompanyToCompanyViewModelConverter implements Converter<Company, CompanyViewModel> {
    @Autowired
    private EmployeeToEmployeeViewModelConverter employeeConverter;
    
    @Override
    public CompanyViewModel convert(Company source) {
	List<EmployeeViewModel> employeeList = new ArrayList<>();
	
	source.getEmployees().forEach(employee -> 
		employeeList.add(employeeConverter.convert(employee)));
	
	return new CompanyViewModel(source.getId(), source.getName(), employeeList);
    }
}
