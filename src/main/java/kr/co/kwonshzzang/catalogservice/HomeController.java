package kr.co.kwonshzzang.catalogservice;

import kr.co.kwonshzzang.catalogservice.config.PolarProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final PolarProperties polarProperties;

    @GetMapping("/")
    public String getGreeting() {
        System.out.println(polarProperties.getConfigServiceUri());
//        return "도서 카탈로그에 오신 것을 환영합니다!";
        return polarProperties.getGreeting();
    }
}
