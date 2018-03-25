<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <title>登录</title>
    <style type="text/css">
        #regform {
            padding-top: 50px;
            display: flex;
            flex-flow: column nowrap;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>
<body>

<div class="layui-container">
    <div class="layui-row" id="regform">

        <form class="layui-form layui-col-md6" action="${ctx}/login" id="loginForm" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-block">
                    <input type="email" name="email" lay-verify="required|email" required placeholder="请输入邮箱" autocomplete="on" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input type="password" id="password" lay-verify="required" required class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formLogin">登录</button>
                </div>
            </div>
        </form>

    </div>
</div>

<script src="${ctxStatic}/js/md5.js" type="text/javascript"></script>

<script>
    layui.use('form',function () {
        var form = layui.form;
        form.on("submit(formLogin)",function (data) {
            var password = hex_md5($('#password').val());
            var email = $("input[name='email']").val();
            $.post(data.form.action,
                {email:email,password:password},
                function (data) {
                    var layer;
                    layui.use('layer', function(){
                        layer = layui.layer;
                    });

                    if(data.code == 200){
                        layer.msg("登录成功");

                        var ctx = ${ctx} + '';


                        window.location.href= '${ctx}' + "index";
                    }else{
                        layer.msg(data.msg());
                        $("#loginForm").reset();
                    }
                },
                'json'
            );

            return false;
        });
    });
</script>

</body>
</html>
