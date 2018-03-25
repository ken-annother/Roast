<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>

<html>
<head>
    <title>注册</title>
    <style type="text/css">
        #regform {
            padding-top: 50px;
            display: flex;
            flex-flow: column nowrap;
            justify-content: center;
            align-items: center;
        }

        .layui-input-block {
            margin-left: 170px;
        }

        .layui-form-label {
            width: 120px;
        }

        .pw-strength {
            display: inline-block;
            position: absolute;
            top: 12px;
            right: -200px;
        }

        .pw-bar {
            background: url(${ctxStatic}/image/pwd-1.png) no-repeat;
            height: 14px;
            overflow: hidden;
            width: 179px;
        }

        .pw-bar-on {
            background: url(${ctxStatic}/image/pwd-2.png) no-repeat;
            width: 0px;
            height: 14px;
            position: absolute;
            top: 1px;
            left: 2px;
            transition: width .5s ease-in;
        }

        .pw-txt {
            padding-top: 2px;
            width: 180px;
            overflow: hidden;
        }

        .pw-txt span {
            color: #707070;
            float: left;
            font-size: 12px;
            text-align: center;
            width: 58px;
        }

        div#pwdcontainer {
            position: relative;
        }

        .pw-weak .pw-defule{ width:0px;}
        .pw-weak .pw-bar-on {width: 60px;}
        .pw-medium .pw-bar-on {width: 120px;}
        .pw-strong .pw-bar-on {width: 179px;}
        .pw-txt {padding-top: 2px;width: 180px;overflow: hidden;}
        .pw-txt span {color: #707070;float: left;font-size: 12px;text-align: center;width: 58px;}
    </style>
</head>
<body>


<div class="layui-container">
    <div class="layui-row" id="regform">

        <form class="layui-form layui-col-md6" action="${ctx}/register" method="post">

            <div class="layui-form-item">
                <label class="layui-form-label">昵称</label>
                <div class="layui-input-block">
                    <input type="text" name="nickname" lay-verify="required" required placeholder="请输入昵称" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-inline" style="width:250px;margin-left: 20px">
                    <input type="email" name="email" lay-verify="required|email" required placeholder="请输入邮箱" autocomplete="on" class="layui-input">
                </div>
                <a class="layui-btn  layui-btn-disabled" href="javascript:void(0);" onclick="sends.send(this)" id="btnSendCode">发送验证码</a>
            </div>


            <div class="layui-form-item">
                <label class="layui-form-label">验证码</label>
                <div class="layui-input-block">
                    <input type="text" name="code" lay-verify="required" required class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>

                <div id="pwdcontainer">
                    <div class="layui-input-block">
                        <input type="password" id="password_in" lay-verify="required" required class="layui-input">
                        <input type="hidden" name="password">
                    </div>

                    <div id="level" class="pw-strength">
                        <div class="pw-bar"></div>
                        <div class="pw-bar-on"></div>
                        <div class="pw-txt">
                            <span>弱</span>
                            <span>中</span>
                            <span>强</span>
                        </div>
                    </div>
                </div>
            </div>


            <div class="layui-form-item">
                <label class="layui-form-label">重新输入密码</label>
                <div class="layui-input-block">
                    <input type="password" id="repassword" lay-verify="required" required class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                </div>
            </div>
        </form>

    </div>
</div>



<script src="${ctxStatic}/js/md5.js" type="text/javascript"></script>

<script>
    var sends = {
        checked:0,
        send:function (obj) {
            var thisObj = $(obj);
            if(thisObj.hasClass("layui-btn-normal")){
                var email = $("input[name='email']").val();
                $.post("getCode",{type:"email",content:email},function (result) {
                    // console.log(result);
                    if(result.code == 200){
                        $("#btnSendCode").text("已发送(30s)");
                        $("#btnSendCode").addClass("layui-btn-disabled").removeClass("layui-btn-normal");
                        setEmailCodeTime(30);
                    }else{
                        layui.use('layer', function(){
                            var layer = layui.layer;
                            layer.msg('请求验证码失败，请稍后再试');
                        });
                    }
                },'json');
            }
        }
    };


    /**
     * 倒计时
     * @param seconds
     */
    function setEmailCodeTime(seconds) {
        var fn = function() {
            seconds--;
            if(seconds == 0){
                $("#btnSendCode").text("重新发送");
                $("#btnSendCode").removeClass("layui-btn-disabled").addClass("layui-btn-normal");
            }else{
                $("#btnSendCode").text("已发送(" +  seconds + "s)");
                setTimeout(fn,1000)
            }
        };

        fn();
    }


    $(function () {
        $("input[name='email']").on("input propertychange",function () {
            var email = this.value;
            var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
            if(reg.test(email)){ //正则验证不通过，格式不对
               sends.checked = 1;
               $("#btnSendCode").removeClass("layui-btn-disabled").addClass("layui-btn-normal");
            }else{
                sends.checked = 0;
                $("#btnSendCode").addClass("layui-btn-disabled").removeClass("layui-btn-normal");
            }
        });

        $("input[name='password_in']").keyup(function () {
            var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
            var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
            var enoughRegex = new RegExp("(?=.{6,}).*", "g");

            if (false == enoughRegex.test($(this).val())) {
                $('#level').removeClass('pw-weak');
                $('#level').removeClass('pw-medium');
                $('#level').removeClass('pw-strong');
                $('#level').addClass(' pw-defule');
                //密码小于六位的时候，密码强度图片都为灰色
            }
            else if (strongRegex.test($(this).val())) {
                $('#level').removeClass('pw-weak');
                $('#level').removeClass('pw-medium');
                $('#level').removeClass('pw-strong');
                $('#level').addClass(' pw-strong');
                //密码为八位及以上并且字母数字特殊字符三项都包括,强度最强
            }
            else if (mediumRegex.test($(this).val())) {
                $('#level').removeClass('pw-weak');
                $('#level').removeClass('pw-medium');
                $('#level').removeClass('pw-strong');
                $('#level').addClass(' pw-medium');
                //密码为七位及以上并且字母、数字、特殊字符三项中有两项，强度是中等
            }
            else {
                $('#level').removeClass('pw-weak');
                $('#level').removeClass('pw-medium');
                $('#level').removeClass('pw-strong');
                $('#level').addClass('pw-weak');
                //如果密码为6为及以下，就算字母、数字、特殊字符三项都包括，强度也是弱的
            }
            return true;
        });
    });


    layui.use('form',function () {
        var form = layui.form;
        form.on("submit(formDemo)",function (data) {
            console.log(data.field);
            if($('#password_in').val() != $('#repassword').val()){
                // console.log("密码不一致")
                layui.use('layer', function(){
                    var layer = layui.layer;
                    layer.msg('两次输入的密码不一致！');
                    $('#password_in').val('');
                    $('#repassword').val('');
                });
                return false;
            }

            $("input[name='password']").val(hex_md5($('#password_in').val()));
            // console.log("密码一致，准备提交");
            return true;
        });
    });

</script>

</body>
</html>SMS
