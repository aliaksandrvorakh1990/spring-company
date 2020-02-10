package by.vorakh.alex.spring_company.client;

import java.nio.charset.Charset;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;

import by.vorakh.alex.spring_company.model.external.ExternalEmployee;
import by.vorakh.alex.spring_company.model.external.ExternalJobTitle;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.client.HttpClient;
import io.reactivex.netty.protocol.http.client.HttpClientRequest;
import io.reactivex.netty.protocol.http.client.HttpClientResponse;
import io.reactivex.netty.util.CollectBytes;
import rx.Single;

@Component
public class RandomEntityClient {
    private static final String URL_RANDOM_JOB = "/random-job-title";
    private static final String URL_RANDOM_EMPLOYEE = "/random-employee/job";
    private static final String HOST = "127.0.1.1";
    
    private static final int PORT = 8082;
    

    private HttpClient<ByteBuf, ByteBuf> client;
    private Gson gson;

    public RandomEntityClient() {
        this.gson = new Gson();
        this.client = HttpClient.newClient(HOST, PORT);
    }
  
    private Single<ExternalJobTitle> getRandomJobTitleSingle() {
	HttpClientRequest<ByteBuf, ByteBuf> request = client
                .createGet(URL_RANDOM_JOB);
        return request.flatMap(HttpClientResponse::getContent)
                .compose(CollectBytes.all())
                .map(bytes -> {
                    String json = bytes.toString(Charset.defaultCharset());
                    bytes.release();
                    return gson.fromJson(json, ExternalJobTitle.class);
                })
                .toSingle();
    }
      
    private Single<ExternalEmployee> getRandomEmployeeSingle() {
        HttpClientRequest<ByteBuf, ByteBuf> request = client
                .createGet(URL_RANDOM_EMPLOYEE);
        return request.flatMap(HttpClientResponse::getContent)
                .compose(CollectBytes.all())
                .map(bytes -> {
                    String json = bytes.toString(Charset.defaultCharset());
                    bytes.release();
                    return gson.fromJson(json, ExternalEmployee.class);
                })
                .toSingle();
    }
   
    public Single<ExternalEmployee> findRandomEmployee() {
        return Single.zip(getRandomEmployeeSingle(), getRandomJobTitleSingle(), 
                (employee, jotTitle) -> {
                    employee.setJobTitle(jotTitle);
                    return employee;
                });
    }
}
