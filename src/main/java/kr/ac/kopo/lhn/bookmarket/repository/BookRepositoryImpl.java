package kr.ac.kopo.lhn.bookmarket.repository;

import kr.ac.kopo.lhn.bookmarket.domain.Book;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final AbstractHandlerMethodAdapter abstractHandlerMethodAdapter;
    private List<Book> listOfBooks = new ArrayList<Book>();

    public BookRepositoryImpl(AbstractHandlerMethodAdapter abstractHandlerMethodAdapter) {
        Book book1 = new Book();
        book1.setBookID("1140711962");
        book1.setName("스프링 부트 완전정복");
        book1.setUnitPrice(new BigDecimal(35000));
        //book1.setUnitPrice(BigDecimal.valueOf(35000));
        book1.setAuthor("송미영");
        book1.setDescription("스프링 부트는 스프링을 기반으로 쉽고 빠르게 웹 애플리케이션을 개발할 수 있는 도구이다." +
                            " 이 책에서는 스프링 부트의 기본 개념을 쉽게 이해하고 다양한 실습 예제로 빠르게 익힐 수 있다." +
                            " 그리고 단계별 실습을 따라 하다 보면 도서 쇼핑몰 구축 프로젝트를 완성할 수 있다." +
                            " 개념-실습-프로젝트의 3단계 학습으로 스프링 부트를 제대로 익힌다면 개발 시간을 단축하고 생산성을 높일 수 있는 개발자로 성장할 수 있다.");
        book1.setPublisher("길벗캠퍼스");
        book1.setCategory("IT 교재");
        book1.setUnitsInStock(1000);
        book1.setReleaseDate("2024/12/31");
        book1.setCondition("전자책");

        Book book2 = new Book();
        book2.setBookID("9791156640219");
        book2.setName("안드로이드 프로그래밍");
        book2.setUnitPrice(new BigDecimal(34000));
        book2.setAuthor("우재남");
        book2.setDescription("이 책은 대학교나 IT 전문학원의 안드로이드 프로그래밍 과목 수강생을 대상으로 합니다. " +
                            "기본적인 프로그래밍을 접해본 독자라면 Java 기초부터 시작하여 안드로이드 앱 개발까지 한번에 학습할 수 있도록 구성되어 있습니다. " +
                            "특별히 이번 9판에서는 버전업을 적용하여 JDK 17, Android 14.0(U), Android Studio Hedgehog에서 실습할 수 있습니다. " +
                            "또한 연습문제 일부를 변경하였으며, 13장에 ‘경기도 맛집 찾기 앱 만들기’ 프로젝트를 추가하였습니다. " +
                            "마지막으로 안드로이드 프로그래밍을 하면서 접할 수 있는 다양한 오류나 실수까지 친절하게 안내하여 시행착오 없이 안드로이드를 빠르게 정복할 수 있을 것입니다.");
        book2.setPublisher("한빛아카데미");
        book2.setCategory("IT 교재");
        book2.setUnitsInStock(800);
        book2.setReleaseDate("2024/01/19");
        book2.setCondition("중고도서");

        Book book3 = new Book();
        book3.setBookID("1161508937");
        book3.setName("시원스쿨 취업영어 면접 표현");
        book3.setUnitPrice(new BigDecimal(6900));
        book3.setAuthor("시원스쿨어학연구소");
        book3.setDescription("최신 영어 면접 경향을 분석하고 반영한 면접 예상 질문 리스트 수록." +
                            " 질문의 핵심을 분석한 답변 포인트를 수록하여 짜임새 있고 논리적인 답변 가능." +
                            " 최신 경향을 반영한 개인별, 직무별 모범 답변을 활용하여 나만의 답변 만들기 가능." +
                            " 면접에서 자주 쓰이는 필수 패턴 및 예상하지 못한 질문을 받았을 때 유연하게 넘기는 팁 공개.");
        book3.setPublisher("시원스쿨닷컴");
        book3.setCategory("영어 교재");
        book3.setUnitsInStock(700);
        book3.setReleaseDate("2024/09/30");
        book3.setCondition("신규도서");

        listOfBooks.add(book1);
        listOfBooks.add(book2);
        listOfBooks.add(book3);
        this.abstractHandlerMethodAdapter = abstractHandlerMethodAdapter;
    }

    @Override
    public List<Book> getAllBookList() {
        return listOfBooks;
    }

    @Override
    public Book getBookById(String bookId) {
        Book bookInfo = null;
        for (Book book : listOfBooks) {
            if (book != null && book.getBookID() != null && book.getBookID().equals(bookId)) {
                bookInfo = book;
                break;
            }
        }

        if(bookInfo == null){
            throw new IllegalArgumentException("도서 번호가 " + bookId + "인 해당 도서를 찾을 수 없습니다.");
        }
        return bookInfo;
    }

    @Override
    public List<Book> getBookListByCategory(String category) {
        List<Book> booksByCategory = new ArrayList<>();
        for (Book book : listOfBooks) {
            if (book.getCategory() != null && book.getCategory().equals(category)) {
                booksByCategory.add(book);
            }
        }
        return booksByCategory;
    }

    @Override
    public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
        Set<Book> booksByPublisher = new HashSet<Book>();
        Set<Book> booksByCategory = new HashSet<Book>();
        Set<String> booksByFilter = filter.keySet();

        if(booksByFilter.contains("publisher")){
            for (int i=0; i<filter.get("publisher").size(); i++){
                String publisherName = filter.get("publisher").get(i);
                for (Book book : listOfBooks) {
                    if(publisherName.equalsIgnoreCase(book.getPublisher())){
                        booksByPublisher.add(book);
                    }
                }
            }
        }
        if (booksByFilter.contains("category")){
            for (int i=0; i<filter.get("category").size(); i++){
                String categoryName = filter.get("category").get(i);
                List<Book> list = getBookListByCategory(categoryName);
                booksByCategory.addAll(list);
            }
        }
//      저장된 요소 중에서 2 Set의 비교하여 같은 값만 남기고 나머지는 제거하는 역할(교집함만 남김)
        booksByCategory.retainAll(booksByPublisher);

        return booksByCategory;
    }

}
