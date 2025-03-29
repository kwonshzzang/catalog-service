package kr.co.kwonshzzang.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

/**
 * 도메인 모델은 불가변 객체인 레코드로 구현된다.
 * @param isbn //책을 고유하게 식별
 * @param title
 * @param author
 * @param price
 */
public record Book(
        @Id   // 이 필드를 엔티티에 대한 기본 키로 식별한다.
        Long id,

        @NotBlank(message = "The book ISBN must be defined.")
        // 이 필드는 주어진 정규 표현식의 값과 일치하는 형식을 가져야 한다(표준 ISBN 형식).
        @Pattern(
                regexp = "^([0-9]{10}|[0-9]{13})$",
                message = "The ISBN format must be valid"
        )
        String isbn,

        // 이 필드는 널 값이 되어서는 안되고 화이트스페이스가 아닌 문자를 최소 하나 이상 있어야 한다.
        @NotBlank(message = "The book title must be defined.")
        String title,

        @NotBlank(message = "The book author must be defined.")
        String author,

        @NotNull(message = "The price must be defined.")
        //이 필드는 널 값이 되어서는 안되고 0보다 큰 값을 가져야 한다.
        @Positive(message = "The book price must be greater than zero.")
        Double price,

        String publisher,

        @CreatedDate  //엔티티가 생성된 때
        Instant createdDate,

        @LastModifiedDate  //엔티티가 마지막으로 수정된 때
        Instant lastModifiedDate,

        @Version  // 낙관적인 잠금(optimistic locking)을 위해 사용되는 엔티티 버전번호
        int version
) {
        public static Book of(
                String isbn, String title, String author, Double price, String publisher)
         {
                return new Book(null, isbn, title, author, price, publisher, null, null,0);
                // id가 널이고 버전이 0이면 새로운 엔티티로 인식한다.
        }
}
