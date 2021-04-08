<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/8 0008
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

    <%--
    web路径问题
    不以/开始的是相对路径，找资源是以当前路径为基准，经常容易出问题
    以/开始的是相对路径，找资源以服务器为标准（http://localhost:3306）需要加上项目名才能找到
     /*获取当前项目的路径*/
    例如：APP_PATH/static/bootstrap-3.3.7-dist/css/bootstrap.min.css
    --%>
    <title>员工列表</title>
     <%
         pageContext.setAttribute("APP_PATH", request.getContextPath());
     %>
    <script type="text/javascript" src="${APP_PATH}/static/js/jquery-3.5.1.min.js" >
    </script><link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>
<body>
<%--${pageInfo}--%>

    <div class="container">
        <%--标题--%>
        <div class="row">
            <div class="col-lg-12">
                <h1>SSM_CRUD</h1>
            </div>
        </div>

        <%--按钮--%>
        <div class="row">
            <div class="col-md-4 col-md-offset-8">
                <button class="btn btn-primary">新增</button>
                <button class="btn btn-danger">删除</button>
            </div>

        </div></br>

        <%--表格数据--%>
        <div class="row">
            <div class="col-md-12">
                <table class="table table-hove">
                    <tr>
                        <th>#</th>
                        <th>empName</th>
                        <th>sex</th>
                        <th>email</th>
                        <th>deptName</th>
                        <th>操作</th>
                    </tr>

                    <c:forEach items="${pageInfo.list}" var="emp">
                    <tr>
                        <th>${emp.empId}</th>
                        <th>${emp.empName}</th>
                        <th>${emp.sex=="G"?"女":"男"}</th>
                        <th>${emp.email}</th>
                        <th>${emp.dept.deptName}</th>
                        <th>
                            <button class="btn btn-info btn-sm">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                编辑
                            </button>
                            <button class="btn btn-danger btn-sm">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                删除
                            </button>
                        </th>
                    </tr>

                    </c:forEach>
                </table>
            </div>

        </div>

        <%--分页条信息--%>
        <div class="row">
            <%--分页文字信息--%>
            <div class="col-md-6">
                当前${pageInfo.pageNum}页，总共${pageInfo.pages}页，总${pageInfo.total}条共记录
            </div>
            <%--分页按钮信息--%>
            <div class="col-md-6">
                <nav aria-label="Page navigation">
                    <ul class="pagination">

                        <li><a href="${APP_PATH }/emps/list?pn=1">首页</a></li>

                        <c:if test="${pageInfo.hasPreviousPage}">
                            <li>
                                <a href="${APP_PATH}/emps/list?pn=${pageInfo.pageNum-1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
                            <c:if test="${page_Num == pageInfo.pageNum }">
                                <li class="active"><a href="#">${page_Num }</a></li>
                            </c:if>
                            <c:if test="${page_Num != pageInfo.pageNum }">
                                <li><a href="${APP_PATH }/emps/list?pn=${page_Num }">${page_Num }</a></li>
                            </c:if>
                        </c:forEach>

                        <c:if test="${pageInfo.hasNextPage}">
                            <li>
                                <a href="${APP_PATH}/emps/list?pn=${pageInfo.pageNum+1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <li><a href="${APP_PATH }/emps/list?pn=${pageInfo.pages}">末页</a></li>

                    </ul>
                </nav>
            </div>

        </div>



    </div>







</body>
</html>
<script>


</script>