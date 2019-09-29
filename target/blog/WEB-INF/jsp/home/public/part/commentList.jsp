<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZhiFei
  Date: 2019/7/14
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--评论列表--%>
<c:forEach items="${commentList}" var="comment">
    <div class="card my-2 text-size-12">
        <div class="card-header p-1">
            <ul class="list-group flex-row">
                <li class="list-group-item border-0 p-0 mx-2">
                        <%--评论作者头像--%>
                    <img class="img-fluid icon-article rounded-circle"
                         src="${comment.userIcon}"
                         alt="头像">
                </li>
                <li class="list-group-item border-0 p-0 mx-2">
                        <%--评论作者昵称--%>
                    <a class="text-muted"
                       href="${comment.userId}">${comment.userNickname}</a>
                </li>
                <li class="list-group-item border-0 p-0 mx-2">
                        <%--创建时间--%>
                        ${comment.commentCreationTime}
                </li>
            </ul>
        </div>
        <div class="card-body py-2 px-3">
            <p>${comment.commentContent}</p>
        </div>
    </div>
</c:forEach>
