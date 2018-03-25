<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>

<html>
<head>
    <title>注册失败</title>
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
    注册失败。<s:actionerror/>
</div>

<script>
    window.setTimeout("window.location='${ctx}/register'",3000);
</script>

</body>
</html>