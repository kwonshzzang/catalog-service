package kr.co.kwonshzzang.catalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
//스프링 설정 클래스를 정의하고 컴포넌트 스캔과 스프링 부트 자동 설정을 실행한다.
@ConfigurationPropertiesScan
//스프링 컨텍스트에 설정 데이터 빈을 로드한다.
public class CatalogServiceApplication {

    /**
     * 애플리케이션을 시작하는 메소드
     * 현재 클래스를 애플리케이션의 부트스트랩 단계에서 실행하도록 설정한다.
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(CatalogServiceApplication.class, args);
    }

}
