<%--
  Created by IntelliJ IDEA.
  User: ZhiFei
  Date: 2019/7/14
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-3 col-xs-12 text-size-14">
    <div class="card mb-2 shadow">
        <img class="card-img-top" src="/img/home.jpg" alt="Card image cap">
        <div class="card-body">
            <p class="card-text indentation">人常常会感受到内心的召唤，如果不去回应它，人就始终不能平静下来，如果去回应它，就意味着必须放弃很多心爱的人和物。<br/><span
                    class="float-right">——吕克·贝松《碧蓝海天》</span></p>
        </div>
    </div>
    <div class="card mb-2 shadow">
        <div class="card-header">
            <i class="fa fa-navicon"></i> 热门文章
        </div>
        <ul class="list-group list-group-flush">
            <c:forEach items="${popularArticle}" var="article">
                <li class="list-group-item over-2"><a
                        href="/getArticleById?articleId=${article.articleId}">${article.title}</a></li>
            </c:forEach>
        </ul>
    </div>
    <div class="card mb-2 shadow">
        <div class="card-header">
            <i class="fa fa-navicon"></i> 最新文章
        </div>
        <ul class="list-group list-group-flush">
            <c:forEach items="${latestArticle}" var="article">
                <li class="list-group-item over-2"><a
                        href="/getArticleById?articleId=${article.articleId}">${article.title}</a></li>
            </c:forEach>
        </ul>
    </div>
    <div class="card mb-2 shadow">
        <div class="card-header">
            <i class="fa fa-navicon"></i> 最新评论
        </div>
        <ul class="list-group list-group-flush">
            <c:forEach items="${latestComment}" var="comment">
                <li class="list-group-item over-2"><a
                        href="/getArticleById?articleId=${comment.articleId}"><strong>${comment.userNickname}</strong>:${comment.commentContent}
                </a></li>
            </c:forEach>
        </ul>
    </div>
</div>

