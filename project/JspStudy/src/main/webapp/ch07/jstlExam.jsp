<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 종합 예제</title>
</head>
<body>
	<h2>JSTL 종합 예제</h2>
	<hr>
	
	<!-- c:set 특정 scope에 값을 저장하는 기능 -->
	<h3>set, out</h3>
	<c:set var="product1" value="<b>애플 아이폰</b>" />
	<c:set var="product1" value="삼성 갤럭시 노트" />
	<c:set var="intArray" value="${[1,2,3,4,5]}" />
	<c:set var="product2" value="구글 픽셀" />
	
	<!-- c:out : 출력을 위한 태그로 대부분은 EL로 대체된다. -->
	<!-- c:set으로 저장된 값은 EL로 출력할 수 있으며 가장 선호 되는 방법이다. -->
	<p>
		<c:out value="${product1}" default="Not registered" escapeXml="true" />
	</p>
	
	<p>product1(el) : ${product1}</p>
	<p>intArray[2] : ${intArray[2]}</p>
	
	<!-- <c:forEach> : JSTL에서 가장 널리 사용되는 태그 -->
	<!-- forEach -->
	<h3>forEach : 배열 출력</h3>
	<ul>
		<c:forEach var="num" varStatus="i" items="${intArray}">
			<li>${i.index} : ${num}</li>
		</c:forEach>
	</ul>
	<hr>
	
	<!-- <c:if> : <c:forEach>와 함께 JSTL에서 가장 널리 사용되는 태그 중 하나 -->
	<h3>if</h3>
	<c:set var="checkout" value="true" />
	<c:if test="${checkout}">
		<p>주문 제품 : ${product2}</p>
	</c:if>
	<c:if test="${!checkout}">
		<p>주문 제품 아님</p>
	</c:if>
	
	<c:if test="${!empty product2}">
		<p>
			<b>${product2} 이미 추가됨!</b>
		</p>
	</c:if>
	
	<!-- choose, when, otherwise -->
	<h3>choose, when, otherwise</h3>
	<c:choose>
		<c:when test="${checkout}">
			<p>주문 제품 : ${product2}</p>
		</c:when>
		<c:otherwise>
			<p>주문 제품 없음</p>
		</c:otherwise>
	</c:choose>
	<hr/>
	
	<!-- forTokens -->
	<h3>forTokens</h3>
	<c:forTokens var="city" items="Seoul|Tokyo|New York|Toranto" delims="|" varStatus="1">
		<c:if test="${i.first}">도시 목록 : </c:if>
		${city}
		<c:if test="${i.last}">,</c:if>
	
	</c:forTokens>
</body>
</html>
