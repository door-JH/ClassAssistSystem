package info.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

// 프로젝트 작업시 사용할 bean을 정의하는 클래스
@Configuration
@ComponentScan(basePackages = "info.example")
public class RootAppContext {
	@Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/"); // 뷰 파일 위치
        resolver.setSuffix(".jsp");           // 뷰 파일 확장자
        return resolver;
    }
}
