package kr.ac.kopo.lhn.bookmarket.repository;

import kr.ac.kopo.lhn.bookmarket.domain.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAllBookList();
    Book getBookById(String bookId);

}
