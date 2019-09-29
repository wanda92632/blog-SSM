<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: ZhiFei
  Date: 2019/7/14
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="navbar navbar-expand-lg sticky-top bg-white">
    <button class="navbar-toggler nav-button" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <i class="fa fa-bars"></i>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mx-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">主页</a>
            </li>
            <c:forEach items="${categoryList}" var="category">
                <li class="nav-item">
                    <a class="nav-link"
                       href="/getArticleByCategory?categoryName=${category.categoryName}">${category.categoryName}</a>
                </li>
            </c:forEach>
            <li class="nav-item">
                <a class="nav-link" href="/messageBoard">留言板</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="http://47.112.29.149:1314/home.html">知非网盘</a>
            </li>
        </ul>
        <div class="mx-auto">
            <ul class="navbar-nav">
                <li class="nav-item mr-5">
                    <form action="/getArticleByTitle" method="get" class="form-inline my-0 my-lg-0">
                        <div class="input-group mb-0">
                            <div class="input-group-prepend border text-muted">
                                <button class="btn btn-link  my-0 my-sm-0" type="submit"><i class="fa fa-search"></i>
                                </button>
                            </div>
                            <input class="form-control mr-sm-0" name="title" type="search" value="Java">
                        </div>
                    </form>
                </li>
                <shiro:notAuthenticated>
                    <li class="nav-item">
                        <a class="nav-link" href="/registered">注册</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/loginPage">登录</a>
                    </li>
                </shiro:notAuthenticated>
                <shiro:authenticated>
                    <li class="dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                ${user.userNickname}
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="/admin/adminPersonal">个人资料</a>
                            <a class="dropdown-item" href="/admin/index">用户中心</a>
                            <a class="dropdown-item" href="/logout">注销</a>
                        </div>
                    </li>
                </shiro:authenticated>
            </ul>
        </div>
    </div>
</header>
