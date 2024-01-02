## 1차 요구사항 구현
- [✅] 유저가 루트 url로 접속시에 게시글 리스트 페이지(http://주소:포트/article/list)가 나온다.
- [✅] 리스트 페이지에서는 등록 버튼이 있고 버튼을 누르면 http://주소:포트/article/create 경로로 이동하고 등록 폼이 나온다.
- [✅] 게시글 등록을 하면 http://주소:포트/article/create로 POST 요청을 보내어 DB에 해당 내용을 저장한다.
- [✅] 게시글 등록이 되면 해당 게시글 리스트 페이지로 리다이렉트 된다. 페이지 URL 은 http://주소:포트/article/list 이다.
- [✅] 리스트 페이지에서 해당 게시글을 클릭하면 상세페이지로 이동한다. 해당 경로는 http://주소:포트/article/detail/{id} 가 된다.
- [✅] 게시글 상세 페이지에는 id에 맞는 게시글 데이터와 목록 버튼이 있다. 목록 버튼을 누르면 게시글 리스트 페이지로 이동하게 된다.

- (추가 기능이나 구현기능설명이 필요한 경우 서술)

## 미비사항 or 막힌 부분
- html 작성
- 부트스트랩 적용
- detail id 넘겨주기(getArticle 만들기)


## MVC 패턴
- Article-Entity
- ArticleController-@Controller
- ArticleService-@Service
- ArticleRepository-@Repository

## 스프링에서 의존성 주입(DI) 방법 3가지 방법
- 생성자 함수에 객체 생성하여 의존성 주입
- @RequiredArgsConstructor 후 private final 형태로 클래스 변수 생성.
- 상속

## JPA의 장점과 단점
장점: 복잡한 sql 문을 익히지 않고도 데이터베이스를 사용할 수 있다.

단점: 복잡한 메서드를 익혀야 한다.

## HTTP GET 요청과 POST 요청의 차이
GET 요청은 주로 데이터베이스에서 데이터를 출력하는 요청이다.

POST 요청은 주로 데이터베이스에 데이터를 저장하는 요청이다.