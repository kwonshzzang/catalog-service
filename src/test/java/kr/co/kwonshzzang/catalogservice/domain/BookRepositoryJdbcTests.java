package kr.co.kwonshzzang.catalogservice.domain;

import kr.co.kwonshzzang.catalogservice.config.DataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest // 스프링 데이터 JDBC 컴포넌트를 집중적으로 테스트하는 클래스임을 나타낸다.
@Import(DataConfig.class) // 데이터 설정을 임포트한다(감사를 활성화하기 위해 필요)
@AutoConfigureTestDatabase( // 테스트컨테이너를 이용하기 때문에 내장 테스트 데이터베이스 사용을 비활성화한다.
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@ActiveProfiles("integration") // application-integration.yml에서 설정을 로드하기 위해 integration 프로파일을 활성화한다.
class BookRepositoryJdbcTests {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate; //데이터베이스와 상호작용하기 위한 하위 수준의 객체

    @Test
    void findBookByIsbnWhenExisting() {
        //given
        var bookIsbn ="1234561237";
        var book = Book.of(bookIsbn, "Title", "Author", 12.90, "Polarsophia");
        jdbcAggregateTemplate.insert(book); //jdbcAggregateTemplate은 테스트에 필요한 데이터를 준비하는 데 사용한다.
        //when
        Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);
        //then
        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().isbn()).isEqualTo(bookIsbn);

    }
}
