$(function () {
    var hideMenu = function(){
        $('dl.ushowMenu').hide();
    };

    var hideTimer;

    $('.ushow').mouseenter(function () {
        clearTimeout(hideMenu);
        $('dl.ushowMenu').show();
    });

    $('.ushow').mouseleave(function () {
        hideTimer = setTimeout(hideMenu,700);
    });

    $('.ushowMenu').mouseenter(function () {
        // console.log("ushowMenu mouseenter");
        clearTimeout(hideTimer);
    });

    $('.ushowMenu').mouseleave(function () {
        // console.log("ushowMenu mouseleave");
        hideMenu();
    });

    // layui-this
    layui.use('element', function(){
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
    });

    document.getElementsByClassName("logo")[0].innerHTML = document.documentElement.clientWidth || document.body.clientWidth;
});