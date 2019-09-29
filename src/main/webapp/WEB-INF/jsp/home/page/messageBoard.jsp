<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: ZhiFei
  Date: 2019/7/9
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>知非博客-留言板</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="shortcut icon" href="/img/favicon.ico"/>
    <link rel="bookmark" href="/img/favicon.ico"/>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/back.css" type="text/css" rel="stylesheet" charset="utf-8">
</head>
<body>
<%@include file="../public/part/header.jsp" %>
<section class="container mt-5">
    <div class="row">
        <div class="col-md-3 col-xs-12">
            <div class="card">
                <div class="card-body">
                    <form action="/setMessage" method="post">
                        <div class="form-group">
                            <label class="text-muted" for="nickname">昵称(可不填)</label>
                            <input type="text" name="nickname" class="form-control" id="nickname"
                                   placeholder="昵称">
                        </div>
                        <div class="form-group">
                            <label class="text-muted" for="exampleInputPassword1">想说些什么</label>
                            <textarea class="form-control" name="content" id="exampleInputPassword1" rows="5"
                                      placeholder="想说些什么"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary pull-right">提交</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-9 col-xs-12">
            <div class="row">
                <c:forEach items="${messageList}" var="message">
                    <div class="col-md-4 mb-2">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">${message.nickname}</h5>
                                <p class="card-text">${message.content}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </div>
    </div>
</section>
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
</body>
</html>
