<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>

<html>
<head>
    <title>注册成功</title>
    <style type="text/css">
        .info{
            padding-top: 20px;
            font-size: medium;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="info">
    <s:property value="valideCodeAndCache"/>
    恭喜您已经注册成功，请前往邮箱查收。3秒后会自动跳转到登录页...
</div>

<script>
    window.setTimeout("window.location='${ctx}/index'",3000);
</script>

</body>
</html>