<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>

<html>
<head>
    <title>${fns:getConfig("productName")}</title>
    <style type="text/css">
        #postertool{
            background-color: white;
            height: 60px;
        }

        #postertool .layui-container{
            height: 100%;
            display: flex;
            flex-flow: row nowrap;
            align-items: center;
            justify-content: flex-end;
        }

        .posterItem{
            display: flex;
            flex-flow: row nowrap;
            margin-top: 20px;
        }

        .plInfo{
            padding-right: 20px;
        }

        .plInfo img{
            width: 60px;
            height: 60px;
            border-radius: 30px;
        }

        .plContentContainer{

        }

        .pt-name {
            font-size: 15px;
            font-weight: bold;
            color: black;
        }

        .pt-content {
            padding-top: 12px;
            color: #444;
        }
    </style>
</head>
<body>

<div id="posterList">
    <s:iterator status="poster" value="data.data">


        <div class="posterItem">
            <div class="plInfo">
                <img src="/static/image/avatar.png"/>
            </div>

            <div class="plContentContainer">
                <div class="pt-name">
                    <s:property value="postUser.nickname"/>
                </div>
                <div class="pt-date">
                    <s:property value="postDate"/>
                </div>
                <div class="pt-content">
                    <s:property value="content" escapeHtml="false"/>
                </div>
            </div>
        </div>

        <hr/>
    </s:iterator>

    <s:property value="data" escapeHtml="false"/>

</div>



<script type="text/html" id="postertooltpl">
    <div id="postertool">
        <div class="layui-container">
            <a href="${ctx}/poster_add" class="layui-btn">发表吐槽</a>
        </div>
    </div>
</script>

<script>
    $(function () {
        $('header.layui-bg-black').after($('#postertooltpl').html());

    });
</script>
</body>
</html>
