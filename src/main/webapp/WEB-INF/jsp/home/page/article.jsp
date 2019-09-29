<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: wanda
  Date: 2019/3/27
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${article.title}-知非博客</title>
    <link rel="shortcut icon" href="/img/favicon.ico"/>
    <link rel="bookmark" href="/img/favicon.ico"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/highlight.js/9.15.8/highlight.min.js"></script>
    <link href="/css/back.css" type="text/css" rel="stylesheet" charset="utf-8">
    <link href="https://cdn.bootcss.com/wangEditor/10.0.13/wangEditor.css" rel="stylesheet">
    <link href="/css/dark.css" type="text/css" rel="stylesheet" charset="utf-8">
</head>
<body>
<%@include file="../public/part/header.jsp" %>
<section class="container mt-4 mb-2">
    <div class="row">
        <div class="col-md-9 col-xs-12">
            <%--文章内容--%>
            <div class="card shadow">
                <%--文章标题--%>
                <div class="card-header bg-white">
                    <div class="row mx-1">
                        <h4>${article.title}</h4>
                    </div>
                    <div class="row mx-1 text-muted">
                        <div class="text-size-14">
                            <%--文章作者昵称--%>
                            <ul class="list-group flex-row">
                                <li class="mx-1">
                                    <%--文章作者昵称--%>
                                    <a href="${article.user.userId}">${article.user.userNickname}</a>
                                </li>
                                <li class="mx-1">
                                    <%--阅读数--%>
                                    <i class="fa fa-eye"></i> ${article.viewed}
                                </li>
                                <li class="mx-1">
                                    <%--创建时间--%>
                                    ${article.creationTime}
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <%--文章内容--%>
                <div class="card-body">
                    <div class="text-size-14">
                        ${article.content}
                    </div>
                </div>
            </div>

            <div class="card my-2 text-size-12 px-2">
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
                <%--评论输入框--%>
                <div class="card-body py-2 px-1">
                    <form action="/setComment" method="post">
                        <div class="form-group d-none">
                            <input type="text" name="articleId" class="form-control" id="articleId"
                                   value="${article.articleId}">
                        </div>
                        <div class="form-group mb-2">
                            <textarea name="commentContent" class="border-1 rounded col" id="commentContent"
                                      maxlength="250"
                                      rows="4" placeholder="写评论..."></textarea>
                        </div>
                        <button type="submit" class="btn btn-info float-right mr-1 text-size-12">提交</button>
                    </form>
                </div>
            </div>
            <%--分页按钮--%>
            <%@include file="../public/part/pageList-home.jsp" %>
        </div>

        <%--侧边栏--%>
        <%@include file="../public/part/sidebar.jsp" %>
    </div>
</section>

<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script>hljs.initHighlightingOnLoad();</script>
</body>
</html>
