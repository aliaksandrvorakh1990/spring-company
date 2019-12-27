package by.vorakh.alex.spring_company.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath*:application.xml"})
public class XmlConfiguration {
}
