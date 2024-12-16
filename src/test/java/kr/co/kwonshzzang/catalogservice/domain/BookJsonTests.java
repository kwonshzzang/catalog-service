package kr.co.kwonshzzang.catalogservice.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;


import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
// JSON 직렬화에 중점을 둔 테스트 클래스임을 나타낸다.
class BookJsonTests {

    @Autowired
    private JacksonTester<Book> json;  //JSON 직렬화 및 역직렬화를 확인하기 위한 유틸리티 클래스

    @Test
    void testSerialize() throws Exception {
        var book = Book.of("1234567890", "Title", "Author", 9.90, "Polarsophia");
        var jsonContent = json.write(book);
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
                .isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title")
                .isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author")
                .isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
                .isEqualTo(book.price());
        assertThat(jsonContent).extractingJsonPathStringValue("@.publisher")
                .isEqualTo(book.publisher());
    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
        {
            "id": 1,
            "isbn": "1234567890",
            "title": "Title",
            "author": "Author",
            "price": 9.90,
            "publisher": "Polarsophia",
            "created_date":null,
            "last_modified_date":null,
            "versin": 0
        }
        """;

        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(new Book(1L, "1234567890", "Title", "Author",
                        9.90, "Polarsophia", null, null,0));
    }

}