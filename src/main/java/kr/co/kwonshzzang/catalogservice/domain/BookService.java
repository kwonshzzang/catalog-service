package kr.co.kwonshzzang.catalogservice.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book) {
        if(bookRepository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistsException(book.isbn());
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public Book editBookDetails(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn)
                .map(existingBook -> {
                    //책을 수정할 때 개체 식별자인 ISBN 코드를 제외한 모든 필드를 수정할 수 있다.
                    var bookToUpdate = new Book(
                            existingBook.id(),  // 기존 책의 식별자를 사용한다.
                            existingBook.isbn(),
                            book.title(),
                            book.author(),
                            book.price(),
                            book.publisher(),
                            existingBook.createdDate(),  // 기존 책 레코드의 생성 날짜 사용
                            existingBook.lastModifiedDate(), // 기존 책 레코드의 마지막 수정 날짜 사용.
                                                             // 업데이트가 성공하면 스프링 데이터에 의해 자동으로 변경된다.
                            existingBook.version()); //기존 책 버전 사용시 업데이트가 성공하면 자동으로 증가한다.
                    return bookRepository.save(bookToUpdate);
                } )
                //카탈로그에 존재하지 않는 책을 수정하려고 하면 새로운 책을 만든다.
                .orElseGet(() -> addBookToCatalog(book));
    }
}
