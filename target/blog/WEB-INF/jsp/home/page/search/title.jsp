<%--
  Created by IntelliJ IDEA.
  User: ZhiFei
  Date: 2019/7/14
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>知非博客</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="shortcut icon" href="/img/favicon.ico"/>
    <link rel="bookmark" href="/img/favicon.ico"/>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/back.css" type="text/css" rel="stylesheet" charset="utf-8">
</head>
<body>
<%@include file="../../public/part/header.jsp" %>
<section class="container mt-4">
    <div class="row">
        <div class="col-md-9 col-xs-12 ">
            <ul class=" list-group px-0">
                <c:if test="${articleList.size()!=0}">
                    <%--文章列表--%>
                    <%@include file="../../public/part/articlelist.jsp" %>
                    <%--分页按钮--%>
                    <%@include file="../../public/part/pageList-home.jsp" %>
                </c:if>
                <c:if test="${articleList.size()==0}">
                    <li class="text-center text-muted">
                        没有更多了
                    </li>
                </c:if>
            </ul>
        </div>
        <%--侧边栏--%>
        <%@include file="../../public/part/sidebar.jsp" %>
    </div>
</section>

<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="/js/script.js"></script>
</body>
</html>
