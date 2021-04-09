
<%--
<jsp:forward page="emps/list"></jsp:forward>
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>首页</title>
    <%--
    web路径问题
    不以/开始的是相对路径，找资源是以当前路径为基准，经常容易出问题
    以/开始的是相对路径，找资源以服务器为标准（http://localhost:3306）需要加上项目名才能找到
     /*获取当前项目的路径*/
    例如：APP_PATH/static/bootstrap-3.3.7-dist/css/bootstrap.min.css
    --%>
        <%
         pageContext.setAttribute("APP_PATH", request.getContextPath());
     %>
    <script type="text/javascript" src="${APP_PATH}/static/js/jquery-3.5.1.min.js" >
    </script><link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>


</head>
<body>
<!-- Modal -->
/<%--员工添加的模态框--%>
<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">员工添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">empName</label>
                        <div class="col-sm-10">
                            <input type="text" name="empName" class="form-control" id="empName_add_input" placeholder="empName">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">email</label>
                        <div class="col-sm-10">
                            <input type="text" name="email" class="form-control" id="email_add_input" placeholder="email@qq.com">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">sex</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender1_add_input" value="B" checked="checked"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender2_add_input" value="G"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">deptName</label>
                        <div class="col-sm-4">
                            <!-- 部门提交部门id即可 -->
                            <select class="form-control" name="deptId" id="dept_add_select">
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
            </div>
        </div>
    </div>
</div>





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
            <button class="btn btn-success" id="emp_add_model_btn">新增</button>
            <button class="btn btn-danger">删除</button>
        </div>

    </div></br>

    <%--表格数据--%>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hove" id="emps_table">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>empName</th>
                        <th>sex</th>
                        <th>email</th>
                        <th>deptName</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>

            </table>
        </div>

    </div>

    <%--分页条信息--%>
  <div class="row">
       <%--分页文字信息--%>
        <div class="col-md-6" id="page_info_area">

        </div>

        <%--分页条信息--%>
        <div class="col-md-6" id="page_nav_area"></div>
    </div>

</div>

</body>
</html>
<script>
    var totarlRecord;
    //1.加载页面后直接发送ajax拿到分页数据
    $(function(){
        //默认开局直接进首页
        to_page(1);
    });

    //跳转到指定页数
    function to_page(pn) {
        $.ajax({
            url:"${APP_PATH}/emps/ajaxList",
            data:"pn="+pn,
            type:"GET",
            success:function(result){
                //1.解析并显示员工数据
                bulid_emps_table(result);
                //2.解析并显示分页信息
                bulid_page_info(result);
                //3.解析并显示分页条信息（按钮）
                bulid_page_nav(result);
            }
        });
    }

    //构建员工数据表格
    function bulid_emps_table(result) {
        //构建表格之前先清空一遍
        $("#emps_table tbody").empty();
        var emps = result.extend.pageInfo.list;
        $.each(emps,function (index,item) {//index索引,item赋值的数据
            //构建页面
            var empIdTd = $("<td></td>").append(item.empId);
            var empNameTd = $("<td></td>").append(item.empName);
            var genderTd = $("<td></td>").append(item.gender=='B'?"男":"女");
            var emailTd = $("<td></td>").append(item.email);
            var deptNameTd = $("<td></td>").append(item.dept.deptName);
            var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn").append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            var delBtn =  $("<button></button>").addClass("btn btn-danger btn-sm delete_btn").append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
            var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);

            $("<tr></tr>").append(empIdTd)
                .append(empNameTd)
                .append(genderTd)
                .append(emailTd)
                .append(deptNameTd)
                .append(btnTd)
                .appendTo("#emps_table tbody");
        })
    }

    //解析分页文字信息
    function bulid_page_info(result) {
        //构建页面前先清空
        $("#page_info_area").empty();
        $("#page_info_area").append("当前在第"+result.extend.pageInfo.pageNum+"页,总"+result.extend.pageInfo.pages+"页,总"+ result.extend.pageInfo.total+"条记录");
        totarlRecord = result.extend.pageInfo.total;
    }

    //构建分页条按钮信息
    function bulid_page_nav(result) {
        //构建页面前先清空一遍
        $("#page_nav_area").empty();

        //构建上一页，下一页，首页，末页
        var ul = $("<ul></ul>").addClass("pagination");
        var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
        var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;")).css("cursor","pointer");
        var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;")).css("cursor","pointer");
        var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));

        // 判断是否有前一页，和后一页
        if(result.extend.pageInfo.hasPreviousPage == false){
            firstPageLi.addClass("disabled");
            prePageLi.addClass("disabled");
        }
        if(result.extend.pageInfo.hasNextPage == false){
            nextPageLi.addClass("disabled");
            lastPageLi.addClass("disabled");
        }

        //添加点击事件上一页，下一页，首页，末页
        prePageLi.click(function(){
            to_page(result.extend.pageInfo.pageNum-1);
        });
        nextPageLi.click(function(){
            to_page(result.extend.pageInfo.pageNum+1);
        });
        firstPageLi.click(function(){
            to_page(1);
        });
        lastPageLi.click(function(){
            to_page(result.extend.pageInfo.pages);
        });

        ul.append(firstPageLi).append(prePageLi);

        //循环遍历出页码
        $.each(result.extend.pageInfo.navigatepageNums,function(idex,item){//1,2,3,4,5

            var numLi =  $("<li></li>").append($("<a></a>").append(item)).css("cursor","pointer");
            //判断是否是当前页
            if (result.extend.pageInfo.pageNum == item){
                numLi.addClass("active")
            }
            //给页码添加点击事件
            numLi.click(function(){
                to_page(item);
            })
            ul.append(numLi);
        })
        ul.append(nextPageLi).append(lastPageLi);
        var navEle = $("<nav></nav>").append(ul).appendTo("#page_nav_area");
    }


    //点击新增弹出模态框
    $("#emp_add_model_btn").click(function(){
        //发送ajax请求拿数据
        $("#empAddModal select").empty();
        getDepts();
        //弹出模态框
        $("#empAddModal").modal({
            backdrop:"static"
        })
    });

    //点击模态框中的保存，添加员工
    $("#emp_save_btn").click(function(){
        //发送ajax请求添加员工
        $.ajax({
            url: "${APP_PATH}/emps/saveAddEmp",
            /*序列化表格中的数据为字符串可用于ajax*/
            data: $("#empAddModal form").serialize(),
            type:"post",
            success:function(result){//添加部门信息到下拉列表
                //员工保存成功后关闭模态框
                $("#empAddModal").modal('hide');
                //跳转到最后一页查看当前添加的数据
                to_page(totarlRecord);
            }

        });

    })



    //拿到部门信息的方法
    function getDepts(){
        $.ajax({
            url: "${APP_PATH}/depts/getDepts",
            type:"GET",
            success:function(result){//添加部门信息到下拉列表
                $.each(result.extend.depts,function(){
                    var optionEle = $("<option></option>").append(this.deptName).attr("value",this.deptId);
                    optionEle.appendTo("#empAddModal select");
                });
            }
        });
    }



</script>
