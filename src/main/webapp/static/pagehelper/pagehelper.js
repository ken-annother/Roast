
var elTotalPageCount = document.getElementById('totalpagecount').innerText;

document.getElementById('pageJumpNumInput').addEventListener('input',function () {
    console.log(this.value);

    console.log(elTotalPageCount);

    if(this.value - elTotalPageCount > 0 || this.value - 1 < 0){
        this.value = elTotalPageCount;
    }

});

//跳转事件
function jumpPage() {
    var inputNum =  document.getElementById('pageJumpNumInput').value;
    if(inputNum > elTotalPageCount || inputNum < 1){
        console.log("输入的数不符合要求");
        return;
    }

    var url = composeUrlForPgae(inputNum);
    // console.log("准备跳转页面啦:" + url);
    location.href = url;
}


/**
 * 组装一个分页的url
 * @param pageNum
 * @returns {string}
 */
function composeUrlForPgae(pageNum) {
    var composePath = location.pathname;
    var search = location.search;

    if(search == ""){
        composePath = composePath + "?page=" + pageNum;
    }else if(search.indexOf("page=") > 0){
        composePath = composePath + search.replace(/page=(\d+)/,"page=" + pageNum);
    }else{
        composePath = composePath + search + "&page=" + pageNum;
    }

    return composePath;
}