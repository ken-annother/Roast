<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>

<html>
<head>
    <title>发表吐槽</title>
    <style type="text/css">
        .layui-input-block {
            margin-left: 10px;
        }
        .editContainer{
            padding-top: 30px;
        }
    </style>
</head>
<body>

<form class="editContainer layui-form">

    <div class="layui-form-item">
        <div class="layui-input-block">
            <textarea id="postEdit" style="display: none;"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="sendNow">立即提交</button>
        </div>
    </div>
</form>


<script>
    var editObj;
    var layedit;

    layui.use('layedit', function(){
        layedit = layui.layedit;

        layedit.set({
            uploadImage: {
                url: '${ctx}/upload' //接口url
            }
        });

        editObj = layedit.build('postEdit',
            {
                tool:['link','unlink','face','image']
            });
    });

    layui.use('form',function () {
        var form = layui.form;
        form.on("submit(sendNow)",function () {
            var content = layedit.getContent(editObj);
            $.post('${ctx}/poster_add',
                {content:layedit.getContent(editObj)},
                function (data) {
                    if(data.code == 200){
                        layui.use('layer',function () {
                            var layer = layui.layer;
                            layer.msg(data.msg);
                            setTimeout(function () {
                                window.location.href= '${ctx}' + "index";
                            },1000);

                        });
                    }
                    // console.log(data);
                },
                'json');
            return false;
        });
    });

</script>

</body>
</html>