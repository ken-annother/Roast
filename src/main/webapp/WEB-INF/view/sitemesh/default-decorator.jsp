<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
    <title><sitemesh:write property='title'/></title>
    <%@include file="/WEB-INF/view/sitemesh/head.jsp"%>
    <link rel="stylesheet" href="${ctxStatic}/default/default.css"/>
    <sitemesh:write property='head'/>
</head>
<body class="layui-bg-gray">
    <header class="layui-bg-black">
        <div class="layui-container topbanner-container">
            <a class="logo" href="${ctx}/index">神吐槽</a>

            <shiro:user>
                <nav class="userinfonav">
                    <a class="ushow">
                        <span><shiro:principal property="nickname"/></span>
                        <img src="${ctxStatic}/image/avatar.png">
                    </a>

                    <dl class="ushowMenu ">
                        <dt>
                            <a href="#1"><i class="layui-icon">&#xe620;</i><span>设置</span></a>
                        </dt>
                        <dt>
                            <a href="#2"><i class="layui-icon">&#xe60b;</i><span>英语</span></a>
                        </dt>
                        <dt>
                            <a href="${ctx}/logout"><i class="layui-icon">&#xe65c;</i><span>退出</span></a>
                        </dt>
                    </dl>
                </nav>
            </shiro:user>

            <shiro:guest>
                <nav class="layui-nav func-nav">
                    <li class="layui-nav-item"><a href="${ctx}/login">登录</a></li>
                    <li class="layui-nav-item layui-hide-xs"><a href="${ctx}/register">注册</a></li>
                    <li class="layui-nav-item layui-hide-xs"><a href="">语言</a></li>
                </nav>
            </shiro:guest>
        </div>
    </header>

    <div class="layui-container content-container">
        <sitemesh:write property='body'/>
    </div>

    <script src="${ctxStatic}/default/default.js"></script>
</body>
</html>
