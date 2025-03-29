package kr.co.kwonshzzang.catalogservice.demo;

import kr.co.kwonshzzang.catalogservice.domain.Book;
import kr.co.kwonshzzang.catalogservice.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("testdata")
// 이 클래스를 testdata 프로파일에 할당한다.
// 이 클래스는 testdata 프로파일이 활성화될 때만 로드된다.
public class BookDataLoader {
    private final BookRepository bookRepository;

    @EventListener(ApplicationReadyEvent.class)
    // ApplicationReadyEvent가 발생하면 테스트 데이터 생성이 시작된다.
    // 이 이벤트는 애플리케이션 시작 단계가 완료되면 발생한다.
    public void loadBookTestData() {
        bookRepository.deleteAll();  // 빈 데이터베이스로 시작하기 위해 기존 책이 있다면 모두 삭제한다.
        var book1 = Book.of("1234567891", "Northern Lights", "Lyra Silverstar", 9.90, "Polarsophia");
        // 프레임워크 내부적으로 식별자와 버전에 대한 할당 값을 처리한다.
        var book2 = Book.of("1234567892", "Polar Journey", "Iorek Polarson", 12.90, "Polarsophia");
        bookRepository.saveAll(List.of(book1, book2));  // 여러 객체를 한꺼번에 저장한다.
    }
}
