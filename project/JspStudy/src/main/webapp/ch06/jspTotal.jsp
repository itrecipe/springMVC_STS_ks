<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.lang.String" %> <!-- String 클래스를 인식할 수 있도록 import 지시어 추가 -->

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <h3>
    <!-- HTML 주석 : 화면엔 안보이고 소스보기엔 보인다. -->
    <%-- JSP 주석 : 화면과 소스 보기에서 보이지 않는다. --%>
    </h3>

    <%! 
        // 멤버 변수와 메서드 선언 영역
        // 여기에 선언된 변수와 메서드는 JSP 페이지 내에서 전역적으로 사용 가능
        String[] members = {"김길동", "홍길동", "김사랑", "박사랑"};
        int num1 = 10;

        // 숫자를 더하는 메서드
        int calc(int num2) {
            return num1 + num2;
        }
    %>

    <h3>
        2. calc(10) 메서드 실행 결과:
        <%= calc(10) %> <!-- calc 메서드를 호출하여 결과를 출력 -->
    </h3>
    <hr/>

    <h3>3. include : hello.jsp</h3>
    <%@ include file="../hello.jsp" %> <!-- 다른 JSP 파일을 포함시킴 -->

    <!-- 스크립트릿 : 자바 for문으로 선언했던 members 배열 값 모두 출력 -->
    <h3>4. 스크립트릿(배열 데이터 출력)</h3>
    <ul>
        <%
        // members 배열의 각 요소를 반복하며 li 태그로 출력
        for (String name : members) {
        %>
            <li><%= name %></li>
        <%
        	}
        %>
    </ul>
</body>
</html>
