package by.vorakh.alex.spring_company.client;

import rx.Single;
import rx.Subscriber;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import by.vorakh.alex.spring_company.model.outsource.EmployeeOutsource;
import by.vorakh.alex.spring_company.model.outsource.JobTitleOutsource;

@Component
public class CompanyClient {
    static final String URL_EMPLOYEES = "http://localhost:8082/random-employee/";
    static final String URL_JOB_TITLE = "http://localhost:8082/random-job-title/";
    
    private EmployeeOutsource externalEmployee;
        
    Subscriber<EmployeeOutsource> clientmySubscriber = new Subscriber<EmployeeOutsource>() {
	    @Override
	    public void onNext(EmployeeOutsource s) { 
		externalEmployee = s;
		System.out.print("Set value"); 
	    }

	    @Override
	    public void onCompleted() {
		System.out.print("Finish!"); 
	    }

	    @Override
	    public void onError(Throwable e) {
		System.out.print("Client ERROR!");
	    }
	};
    
    
 
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new ClientException("Some problem with client (Thread)", e);
        }
    }

    private interface Callback<T> {
        void sendResponse(T response);
    }

    
    public static void makeEmployeeRequest(Callback<EmployeeOutsource> callback) {
        new Thread(() -> {
            sleep(6000);
            System.out.println("Employee request finished!");
            callback.sendResponse(new EmployeeOutsource());
        }).start();
    }


    public static void makeJobTitleRequest(Callback<JobTitleOutsource> callback) {
        new Thread(() -> {
            sleep(1000);
            System.out.println("JobTitle request finished!");
            callback.sendResponse(new JobTitleOutsource());
        }).start();
    }

    public static Single<JobTitleOutsource> getJobTitleResponse() {
        return Single.create(e -> makeJobTitleRequest(e::onSuccess));
    }

    public static Single<EmployeeOutsource> getEmployeeResponse() {
        return Single.create(e -> makeEmployeeRequest(e::onSuccess));
    }
    
    public EmployeeOutsource getRxRandomEmployee() {
        Single<JobTitleOutsource> jobTitleResponse = getJobTitleResponse();
        Single<EmployeeOutsource> employeeResponse = getEmployeeResponse();
     
        Single.zip(employeeResponse, jobTitleResponse, (employeeValue, jobTitleValue) -> {
            System.out.println("All single resources completed. Creating final response...");
            
            employeeValue.setJobTitle(jobTitleValue);
            return employeeValue;
        }).subscribe(clientmySubscriber);
        
        return externalEmployee;
    }
    
    
    public EmployeeOutsource getRandomEmployee(String jobTitleName) {
	RestTemplate restTemplate = new RestTemplate();
	return restTemplate.getForObject(URL_EMPLOYEES + jobTitleName, 
		EmployeeOutsource.class);
    }

}
