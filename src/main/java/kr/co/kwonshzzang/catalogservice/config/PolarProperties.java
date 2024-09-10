package kr.co.kwonshzzang.catalogservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;

@ConfigurationProperties(prefix = "polar")
@Data
//이 클래스는 'polar'로 시작하는 설정 속성에 대한 소스임을 표시한다.
public class PolarProperties {
    /**
     * A message to welcome users.
     */
    //  사용자 정의 속성인 polar.greeting(프리픽스 + 필드명) 속성이 문자열로 인식되는 필드
    private String greeting;
    private URI configServiceUri;
    private String datasourceUrl;
    private String datasourceUsername;
    private String datasourcePassword;


}
