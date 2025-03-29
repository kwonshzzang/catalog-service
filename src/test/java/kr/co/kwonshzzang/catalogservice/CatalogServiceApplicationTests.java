package kr.co.kwonshzzang.catalogservice;

import kr.co.kwonshzzang.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
// 스프링 부트 애플리케이션을 테스트하기 위한 셋업 제공
// 완전한 스프링 웹 애플리케이션 컨텍스트와 임의의 포트를 듣는 서블릿 컨테이너를 로드한다.
@ActiveProfiles("integration")
// application-integration.yml에서 설정을 로드하기 위해 integration 프로파일을 활성화한다.
class CatalogServiceApplicationTests {

    @Autowired
    private WebTestClient webTestClient; // 테스트를 위해 REST 엔드포인트를 호출할 유틸리티

    @Test
    // 테스트케이스를 식별
    /**
     * 애플리케이션 컨텍스트가 올바르게 로드됐는지 검증하기 위해 사용되는 빈 테스트
     */
    void contextLoads() {
    }

    @Test
    void whenPostRequestThenBookCreated() {
        var expectedBook = Book.of("1231231231", "Title", "Author", 9.90, "Polarsophia");
        webTestClient
                .post()                     // HTTP POST 요청을 보낸다.
                .uri("/books")         // "/books" 엔드포인트로 요청을 보낸다.
                .bodyValue(expectedBook)    // 요청 본문에 Book 객체를 추가한다.
                .exchange()                 // 요청을 전송한다.
                .expectStatus().isCreated() // HTTP 응답이 "201 생성" 상태를 갖는지 확인한다.
                .expectBody(Book.class).value(actualBook -> {
                    assertThat(actualBook).isNotNull();  // HTTP 응답의 본문이 널 값이 아닌지 확인한다.
                    assertThat(actualBook.isbn())
                        .isEqualTo("1231231231"); // 생성된 객체가 예상과 동일한지 확인한다.
                });
    }

}
