## 1차 요구사항 구현
- [x] 유저가 루트 url로 접속시에 게시글 리스트 페이지(http://주소:포트/article/list)가 나온다.
- [x] 리스트 페이지에서는 등록 버튼이 있고 버튼을 누르면 http://주소:포트/article/create 경로로 이동하고 등록 폼이 나온다.
- [x] 게시글 등록을 하면 http://주소:포트/article/create로 POST 요청을 보내어 DB에 해당 내용을 저장한다.
- [x] 게시글 등록이 되면 해당 게시글 리스트 페이지로 리다이렉트 된다. 페이지 URL 은 http://주소:포트/article/list 이다.
- [x] 리스트 페이지에서 해당 게시글을 클릭하면 상세페이지로 이동한다. 해당 경로는 http://주소:포트/article/detail/{id} 가 된다.
- [x] 게시글 상세 페이지에는 id에 맞는 게시글 데이터와 목록 버튼이 있다. 목록 버튼을 누르면 게시글 리스트 페이지로 이동하게 된다.

- (추가 기능이나 구현기능설명이 필요한 경우 서술)

## 미비사항 or 막힌 부분
- 타임리프 문법
- 교재를 보지 않고 코딩하는데 어려움

## MVC 패턴
- Model : 데이터와 비즈니스 로직을 관리 (Entity, Repository), application이 포함해야할 데이터가 무엇인지를 정의.
  데이터의 상태가 변경되면 뷰에게 알리며 때로 콘트롤러에 알리기도함.
- View : UI, 앱의 데이터를 보여주는 방식 정의(HTTP/CSS)
- Controller : 모델과 뷰로 명령을 전달
  앱의 사용자로부터 입력에 대한 응답, 모델 및 뷰를 업데이트하는 로직을 포함

## 스프링에서 의존성 주입(DI) 방법 3가지 방법
- 생성자 주입
```java
  @Controller 
  public class Controller{
     private Service service;

     @Autowired 
     public Controller(Service service){
       this.service = service; 
     }
  }
```
생성자에 @Autowired를 붙여 의존성을 주입받을 수 있음.
생성자 주입은 인스턴스 생성시 1회 호출되는 것이 보장되기 때문에, 주입받은 객체가 변하지 않거나, 반드시 객체주입이 필요한 경우 강제하기 위해 사용됨.
@Autowired를 사용하지 않고 class에 @RequiredArgsConstructor를 사용하는 방식을 권장함.
- 필드 주입
```java
  @Controller
  public class Controller{
    @Autowired 
    private Service service;
  }
```
코드가 간결하고 편하지만 의존관계를 정확히 파악하기 힘듦. 
  필드 주입 시 final 키워드를 선언할 수 없어 객체가 변할 수 있음. 
  주입이 동시에 일어나 겹치는 경우 순환참조 에러가 남.
- Setter 메서드
```java
  @Controller 
  public class Controller{
     private Service service;

     @Autowired 
     public setService(Service service){
       this.service = service; 
     }
  }
```
setter 혹은 사용자정의 메서드를 통해 의존관계 주입.
setter의 경우 객체가 변경될 필요성이 있을 때만 사용한다.

- 생성자 주입을 권장하는 이유
    1. 객체 불변성 (생성자는 최초 1회만 실행)
    2. 테스트 용이
    3. 순환참조 에러 방지
## JPA의 장점과 단점
- 장점
    - SQL 문법으로 쿼리를 작성하지 않아도 된다
    - 특정 데이터베이스에 종속되지 않는다.
      ex) MySQL, MariaDB, Oracle은 각각 쿼리문이 조금씩 다르지만
      JPA에 어떤 데이터베이스를 사용하는지 알려주면 자동으로 해당 DBMS에 맞도록 설정해준다.
    - 객체지향적 프로그래밍이 가능하다.
    - 생산성, 유지보수가 좋다

- 단점
    - 메서드 호출로 쿼리를 실행한다는 것은 내부동작이 있다는 것이므로 직접 SQL을 호출하는 것보다 성능이 떨어질 수 있음
    - 세밀함이 떨어지고, 객체간의 매핑이 잘못되었을 경우 의도하지 않은 동작을 할 수 있음
    - 복잡한 통계분석의 경우 메서드 호출로 처리하기 힘듬
    - 잘 사용하기 위해서 학습해야 할 것이 많음

## HTTP GET 요청과 POST 요청의 차이
- GET 요청
    - data를 URL에 담아서 보냄 보안에 취약할 수 있음.
    - GET 요청은 브라우저에서 결과를 캐시할 수 있다.
    - 동일한 GET 요청이 여러 번 수행될 때, 브라우저는 캐시된 결과를 사용할 수 있음.
    - URL에는 길이 제한이 있으므로 GET 요청은 제한된 양의 데이터만을 전송할 수 있음.
- POST 요청
    - data를 body에 담아 보내기 때문에 data가 URL에 노출되지 않음. 비교적 보안상 안정적임.
    - POST 요청은 기본적으로 캐시되지 않음. 매번 새로운 요청이 서버로 전송.
    - POST 요청은 제한이 없는 양의 데이터를 전송할 수 있음. 따라서 대량의 데이터를 전송할 때 사용.
- GET은 주로 데이터를 조회할 때 사용되고, POST는 데이터를 서버로 제출하고자 할 때 사용된다.