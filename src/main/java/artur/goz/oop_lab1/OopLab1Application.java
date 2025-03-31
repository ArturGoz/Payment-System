package artur.goz.oop_lab1;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ServletComponentScan(basePackages = {"artur.goz.oop_lab1.controllers", "artur.goz.oop_lab1.configs"})
public class OopLab1Application {

    public static void main(String[] args) {
        SpringApplication.run(OopLab1Application.class, args);
    }

}
