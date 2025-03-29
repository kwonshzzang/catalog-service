package kr.co.kwonshzzang.catalogservice.web;

import kr.co.kwonshzzang.catalogservice.domain.BookNotFoundException;
import kr.co.kwonshzzang.catalogservice.domain.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
// 스프링 MVC 컴포넌트에 중점을 두고,
// 명시적으로는 BookController 클래스를 타깃으로 하는 테스트 클래스임을 나타낸다.
class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;
    // 모의 환경에서 웹 계층을 테스트하기 위한 유틸리티 클래스

    @MockBean
    private BookService bookService;
    // 스프링 애플리케이션 컨텍스트에 BookService의 모의 객체를 추가한다.

    @Test
    void whenGetBookNotExistingThenShouldReturnNotFound() throws Exception {
        String isbn = "73737313940";
        given(bookService.viewBookDetails(isbn))     //모의 빈이 어떻게 작동할 것인지 규정한다.
            .willThrow(BookNotFoundException.class);
        mockMvc
                .perform(get("/books/" + isbn))
                // MockMVC는 HTTP GET 요청을 수행하고 결과를 확인하기 위해 사용한다.
                .andExpect(status().isNotFound());
                // 응답이 "404 발견되지 않음" 상태를 가질 것으로 예상한다.


    }
}