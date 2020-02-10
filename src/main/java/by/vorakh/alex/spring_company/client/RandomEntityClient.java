package by.vorakh.alex.spring_company.client;

import java.nio.charset.Charset;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import by.vorakh.alex.spring_company.converter.EmployeeOutsourceToEmployeeConverter;
import by.vorakh.alex.spring_company.model.outsource.EmployeeOutsource;
import by.vorakh.alex.spring_company.model.outsource.JobTitleOutsource;
import by.vorakh.alex.spring_company.repository.EmployeeDAO;
import by.vorakh.alex.spring_company.repository.JobTitleDAO;
import by.vorakh.alex.spring_company.repository.PersonalDataDAO;
import by.vorakh.alex.spring_company.repository.SkillDAO;
import by.vorakh.alex.spring_company.repository.entity.Employee;
import by.vorakh.alex.spring_company.repository.entity.JobTitle;
import by.vorakh.alex.spring_company.repository.entity.PersonalData;
import by.vorakh.alex.spring_company.repository.entity.Skill;
import io.netty.buffer.ByteBuf;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleConverter;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.netty.protocol.http.client.HttpClient;
import io.reactivex.netty.protocol.http.client.HttpClientRequest;
import io.reactivex.netty.protocol.http.client.HttpClientResponse;
import io.reactivex.netty.util.CollectBytes;
import io.reactivex.subjects.SingleSubject;


@Component
public class RandomEntityClient {
    
    private final static String URL_RANDOM_JOB = "/random-job-title";
    private final static String URL_RANDOM_EMPLOYEE = "/random-employee/job";
    
    
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private PersonalDataDAO personalDataDAO;
    @Autowired
    private JobTitleDAO jobTitleDAO;
    @Autowired
    private SkillDAO skillDAO;
    
    @Autowired
    private EmployeeOutsourceToEmployeeConverter extermalSourceConvertor; 
    private Gson gson;
    private HttpClient<ByteBuf, ByteBuf> client;
    
    public RandomEntityClient() {
	this.gson  = new Gson();
	this.client = HttpClient.newClient("127.0.1.1", 8082);
    }
    
    private SingleSubject<JobTitleOutsource> getRandomJobTitleSubject() throws InterruptedException {
	SingleSubject<JobTitleOutsource> jobSubject = SingleSubject.<JobTitleOutsource>create();
	HttpClientRequest<ByteBuf, ByteBuf> request = client.createGet(URL_RANDOM_JOB);
	request.flatMap(HttpClientResponse::getContent)
		.compose(CollectBytes.all())
		.map( bytes -> { 
		    String str = bytes.toString(Charset.defaultCharset());
		    bytes.release();
		    return str;
		})
		.subscribe( json -> {
		    JobTitleOutsource jobTitle = gson.fromJson(json, JobTitleOutsource.class);
		    jobSubject.onSuccess(jobTitle);
		}
		, err -> {
		    throw new ClientException("Client problem (Random JobTitle)", err);
		});
	
	return jobSubject;
    }
    
    
    private SingleSubject<EmployeeOutsource> getRandomEmployeeSubject() throws InterruptedException {
	SingleSubject<EmployeeOutsource> empSubject = SingleSubject.<EmployeeOutsource>create();
	
	HttpClientRequest<ByteBuf, ByteBuf> request = client.createGet(URL_RANDOM_EMPLOYEE);
	request.flatMap(HttpClientResponse::getContent)
		.compose(CollectBytes.all())
		.map( bytes -> { 
		    String str = bytes.toString(Charset.defaultCharset());
		    bytes.release();
		    return str;
		})
		.subscribe( json -> { 
		    EmployeeOutsource employee = gson.fromJson(json, EmployeeOutsource.class);
		    empSubject.onSuccess(employee);
			  }
		, err -> {
		    throw new ClientException("Client problem (Random Employee)", err);
		});
	
	return empSubject;
    }
    
    public SingleSubject<EmployeeOutsource> findRandomEmployee() throws InterruptedException {
	SingleSubject<EmployeeOutsource> empWithJobSubject = SingleSubject.<EmployeeOutsource>create();
	
	SingleSubject.zip(getRandomEmployeeSubject(), getRandomJobTitleSubject(), (employee, jotTitle)->{
	    employee.setJobTitle(jotTitle);
	    return employee;
	}).subscribe( emp -> { 
	    empWithJobSubject.onSuccess(emp);
		  }
	, err -> {
	    throw new ClientException("Client problem (joining Employee and JobTitle)", err);
	});
	
	while (!empWithJobSubject.hasValue()) {
	    Thread.sleep(100);
	}
	
	return empWithJobSubject;
    }
    
    private Single<Employee> convertExternalEmpoyee(Single<EmployeeOutsource> employeeSingleSubject) {
	
	return employeeSingleSubject.map(emp -> {
	    Employee convertedEmployee = extermalSourceConvertor.convert(emp);
	    return convertedEmployee;
	}).cache();
    }
    
    private void addEmployeeElementsToDataBase(Single<Employee> empSingle) {
	
	empSingle.map(emp -> {
	    PersonalData personalData;
	    JobTitle jobTitle;
	    List<Skill> skillList;
	    
	    personalData = emp.getPersonalData();
	   // addPersonalDataToDataBase(personalData).map(persData ->)
	    jobTitle = emp.getJobTitle();
	    skillList = emp.getSkillList();
	    addJobTitleToDataBase(jobTitle);
	    addSkillListToDataBase(skillList);
	  
	    
	    
	    return new Employee();
	});
    }
    
  
    @Transactional
    private Single<PersonalData> addPersonalDataToDataBase(PersonalData personalData) {
	
	Single<PersonalData> personalDataSingle = Single.<PersonalData>just(personalData);
	personalDataSingle.subscribe(persData -> {
	    persData.setId(personalDataDAO.create(persData));    
	}, err -> {
	    throw new ClientException("Client problem (PersonalData does not add to DB)", err);
	});
	
	return personalDataSingle;
    }
    
    @Transactional
    private Single<JobTitle> addJobTitleToDataBase(JobTitle jobTitle) {
	Single<JobTitle> jobTitleSingle = Single.<JobTitle>just(jobTitle);
	
	jobTitleSingle.subscribe(job -> {
	    Integer id = 0;
	    if (jobTitleDAO.isContained(job.getTitle())) {
		id = jobTitleDAO.findExisted(job).getId();
	    } else {
		id = jobTitleDAO.create(job);
	    }
	    
	    job.setId(id);
	}, err -> {
	    throw new ClientException("Client problem (JobTitle does not get (create and get) from DB)", err);
	});
	
	return jobTitleSingle;
    }
    
    
    @Transactional
    private Single<List<Skill> > addSkillListToDataBase(List<Skill> skillList) {
	Single<List<Skill> > skillListSingle = Single.<List<Skill> >just(skillList);
	
	skillListSingle.subscribe(list -> {
	   list.forEach(skill -> {
	      if (skillDAO.isContained(skill)) {
		  skill.setId(skillDAO.findExisted(skill).getId());
	      } else {
		  skill.setId(skillDAO.create(skill));
	      }
	   });
	}, err -> {
	    throw new ClientException("Client problem (SkillList does not get (create and get) from DB)", err);
	});
	
	return skillListSingle;
    }
    
    @Transactional
    public Single<Employee> addEmployeeToDataBase(Employee employee) {
	Single<Employee> employeeSingle = Single.<Employee>just(employee);
	
	employeeSingle.subscribe(emp -> {
	    Integer id = employeeDAO.create(emp);
	    emp.setId(id);
	}, err -> {
	    throw new ClientException("Client problem (Employee does not get (create and get) from DB)", err);
	});
	
	return employeeSingle;
    }
    

}
