# 편의점 발주 시스템 관리 웹페이지

편의점에 단순한 발주시스템 적용 & 지점별 원활한 소통장 마련

### Used techniques

```
back-end : java, oracle 11g database, apache tomcat server, maven, mybatis
front-end : html5, jsp, jstl, javascript(jQuery, ajax), bootstrap, tiles
```

## Built With

* [spring](https://spring.io/) - The web framework used
* [maven](https://maven.apache.org/) - Dependency Management
* [svn](https://subversion.apache.org/) - Version(Configuration) Management

## Authors

* **김성훈** [git](https://github.com/seonghun127)   외 6명
## Roles

* 요구사항분석, WBS 관리, 데이터베이스 구축, 화면 설계
* 지점별 발주 신청 / 처리 현황 페이지 & 지점별 발주 신청 상세 페이지 구축
* 건의 사항 게시판 페이지 구축 (게시글 CRUD & 댓글 CRUD)

## Implements

1. [요구사항 및 설계 단계](https://github.com/seonghun127/spring-framework/tree/master/doc)

<img width="600" alt="2019-01-27 6 44 56" src="https://user-images.githubusercontent.com/30451129/51799350-d27c9f00-2263-11e9-9e52-79dcead3a745.png">

2. 지점별 발주 이벤트 처리 

      [[back-end]](https://github.com/seonghun127/spring-framework/tree/master/src/main/java/com/sist/retail/order)  [[front-end]](https://github.com/seonghun127/spring-framework/tree/master/src/main/webapp/master)
      
<img width="600" alt="masterorder" src="https://user-images.githubusercontent.com/30451129/51799276-e542a400-2262-11e9-8750-9fc140cdffaf.png"><img width="600" alt="masterorderdetail" src="https://user-images.githubusercontent.com/30451129/51799281-eb388500-2262-11e9-843b-9022a3fe3f11.png">

3. 건의사항 

      [[back-end]](https://github.com/seonghun127/spring-framework/tree/master/src/main/java/com/sist/retail/qna)  [[front-end]](https://github.com/seonghun127/spring-framework/tree/master/src/main/webapp/notice)
      
<img width="600" alt="noticeqna" src="https://user-images.githubusercontent.com/30451129/51799304-508c7600-2263-11e9-9767-2b574ee6f47f.png">
<img width="600" alt="noticeqnadetail" src="https://user-images.githubusercontent.com/30451129/51799307-571aed80-2263-11e9-8640-5ce5cbdc5ac4.png">
<img width="600" alt="noticeqnaupdate" src="https://user-images.githubusercontent.com/30451129/51799308-57b38400-2263-11e9-9e66-1eaee7cba84f.png">
<img width="600" alt="noticeqnainsert" src="https://user-images.githubusercontent.com/30451129/51799309-57b38400-2263-11e9-82d3-83e312cc58af.png">


4. 댓글 

      [[back-end]](https://github.com/seonghun127/spring-framework/tree/master/src/main/java/com/sist/retail/ans)  [[front-end]](https://github.com/seonghun127/spring-framework/tree/master/src/main/webapp/notice)

