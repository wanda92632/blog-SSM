<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZhiFei
  Date: 2019/7/14
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--分页标签--%>
<li class="mb-3">
<div class="col-md-12 mb-5 text-size-12">
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <c:if test="${articleList.size()!=0}">
                <c:if test="${page==1}">
                    <li class="page-item disabled"><a class="page-link"
                                                      href="/?page=${page-1}">上一页</a></li>
                </c:if>
                <c:if test="${page!=1}">
                    <li class="page-item"><a class="page-link"
                                             href="/?page=${page-1}">上一页</a></li>
                </c:if>
                <c:forEach var="i" begin="1" end="${page-1}">
                    <li class="page-item"><a class="page-link"
                                             href="/?page=${i}">${i}</a></li>
                </c:forEach>
                <li class="page-item active"><a class="page-link"
                                                href="/?page=${page}">${page}</a></li>
                <c:forEach var="i" begin="${page+1}" end="${pageCount}">
                    <li class="page-item"><a class="page-link"
                                             href="/?page=${i}">${i}</a></li>
                </c:forEach>
                <c:if test="${page>=pageCount}">
                    <li class="page-item disabled"><a class="page-link"
                                                      href="/?page=${page+1}">下一页</a></li>
                </c:if>
                <c:if test="${page<pageCount}">
                    <li class="page-item"><a class="page-link"
                                             href="/?page=${page+1}">下一页</a></li>
                </c:if>
            </c:if>
        </ul>
    </nav>
</div>
</li>
