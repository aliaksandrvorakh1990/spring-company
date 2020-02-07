package by.vorakh.alex.spring_company.client;

import java.nio.charset.Charset;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import by.vorakh.alex.spring_company.model.outsource.EmployeeOutsource;
import by.vorakh.alex.spring_company.model.outsource.JobTitleOutsource;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.client.HttpClient;
import io.reactivex.netty.protocol.http.client.HttpClientRequest;
import io.reactivex.netty.protocol.http.client.HttpClientResponse;
import io.reactivex.netty.util.CollectBytes;
import io.reactivex.subjects.SingleSubject;

@Component
public class RandomEntityClient {
    
    private final static String URL_RANDOM_JOB = "/random-job-title";
    private final static String URL_RANDOM_EMPLOYEE = "/random-employee/job";
    
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
	while (!jobSubject.hasValue()) {
	    Thread.sleep(100);
	}
	
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
	
	while (!empSubject.hasValue()) {
	    Thread.sleep(100);
	}
	
	return empSubject;
    }
    
    public EmployeeOutsource findRandomEmployee() throws InterruptedException {
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
	
	return empWithJobSubject.getValue();
    }

}
