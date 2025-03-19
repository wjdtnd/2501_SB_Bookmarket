package kr.ac.kopo.lhn.bookmarket.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor 오늘은 안씀
public class Book {
    private String bookID; // 도서 ID
    private String name; // 도서 제목
    private BigDecimal unitPrice; // 가격
    private String author; // 저자
    private String description; // 설명
    private String publisher; // 출판사
    private String category; // 분류
    private long unitsInStock; // 재고수
    private String releaseDate; // 출판일(월/년)
    private String condition; // 신규도서 or 중고도서 or 전자책
}
