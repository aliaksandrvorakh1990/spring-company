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
import rx.Single;


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
    
    private Single<JobTitleOutsource> getRandomJobTitleSubject() throws InterruptedException {
	HttpClientRequest<ByteBuf, ByteBuf> request = client.createGet(URL_RANDOM_JOB);
	return request.flatMap(HttpClientResponse::getContent)
		.compose(CollectBytes.all())
		.map( bytes -> { 
		    String json = bytes.toString(Charset.defaultCharset());
		    bytes.release();
		    JobTitleOutsource jobTitle = gson.fromJson(json, JobTitleOutsource.class);
		    return jobTitle;
		}).toSingle();
    }
    
    
    private Single<EmployeeOutsource> getRandomEmployeeSubject() throws InterruptedException {
	HttpClientRequest<ByteBuf, ByteBuf> request = client.createGet(URL_RANDOM_EMPLOYEE);
	return request.flatMap(HttpClientResponse::getContent)
		.compose(CollectBytes.all())
		.map( bytes -> { 
		    String json = bytes.toString(Charset.defaultCharset());
		    bytes.release();
		    EmployeeOutsource employee = gson.fromJson(json, EmployeeOutsource.class);
		    return employee;
		}).toSingle();
    }
    
    public Single<EmployeeOutsource> findRandomEmployee() throws InterruptedException {
	Single<EmployeeOutsource> empWithJobSubject = Single.zip(getRandomEmployeeSubject(), getRandomJobTitleSubject(), (employee, jotTitle)->{
	    employee.setJobTitle(jotTitle);
	    return employee;
	});
	
	return empWithJobSubject;
    }
    
}
