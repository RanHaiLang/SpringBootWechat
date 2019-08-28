<%--解决apache转发，无法访问静态资源问题--%>
<%--start--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<%--end--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<link rel="SHORTCUT ICON" href="${ctx}/resources/images/favicon.ico"/>
<script src="${ctx}/resources/js/jquery-2.0.3.min.js"></script>

<style type="text/css">
    /*dl{
        width:100%!important;
    }
    p:has(img){
        text-indent:0!important;
    }*/
    img {
        max-width: 100%; /*图片自适应宽度*/
        /*overflow:hidden;*/
       /* height: auto; width: auto\9; width:100%;*/
        /*text-indent:0!important;*/
    }
    div.para{
        text-indent:0!important;/*防止图片自动空两格*/
    }
    body {
        overflow-y: scroll !important;
    }
    .view {
        word-break: break-all;
    }
    .vote_area {
        display: block;
    }
    .vote_iframe {
        background-color: transparent;
        border: 0 none;
        height: 100%;
    }
    #edui1_imagescale{display:none !important;} /*去除点击图片后出现的拉伸边框*/
</style>

<head>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>

        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>公告</title>
        <link rel="SHORTCUT ICON" href="${ctx}/images/favicon.ico"/>
</head>
<body>
    ${ueditorOldValue }
   <%-- <table class="table" id="tableActive">
        <thead>
        <tr>
            <th>相关附件：</th>
        </tr>
        </thead>
        <tbody id="tabletbody" name="tabletbody">
        &lt;%&ndash;<tr>
            <td>121323464964</td>
            <td>152453</td>
            <td>iPhone 7</td>
            <td>13888888888</td>
        </tr>&ndash;%&gt;
        </tbody>
    </table>
    <input type="hidden" name="notidorCode" id="notidorCode" value="${ueditorCode }">--%>
    <%--<a onclick="openinbrowser(附件的code)">附件的名字</a>--%>
    <%--<script>
        $(document).ready(function () {
            $("p").css("color","red!important");
        })

    </script>--%>
</body>
<script type="text/javascript">
   /* $(document).ready(function () {
        var code= $("#notidorCode").val();
        $.ajax({
            url: '<%=request.getContextPath()%>/ueditor/selectDoc',
            data: {"noticeCode": code},
            type: "POST",
            success: function (data) {
                console.log(data)
                    var str = '';
                    for (var i = 0; i < data.length; i++) {
                        str += '<tr><td><a href="#" onclick="openinbrowser(\''+data[i].ND_ID+'\')">'+data[i].ND_OLDNAME+'</a></tr></td>'
                    $("#tabletbody").children().remove();
                    $("#tabletbody").append(str);
                }

            }
        });
    });
    function openinbrowser(downId){
        window.location.href = "<%=request.getContextPath()%>/currency/downloadDoc?&downId=" + downId;
    }*/

    /*$(document).ready(function () {
        var code= $("#notidorCode").val();
        $.ajax({
            url: '<%=request.getContextPath()%>/ueditor/selectDoc',
            data: {"noticeCode": code},
            type: "POST",
            success: function (data) {
                if(data.length==0){
                    $("#tabletbody").children().remove();
                    $("#tabletbody").append("<tr><td>暂无附件下载！</td></tr>");
                }else {
                    var str = '';
                    for (var i = 0; i < data.length; i++) {
                        str += '<tr><td><a href="<%=request.getContextPath()%>/currency/downloadDoc?&downId=' + data[i].ND_ID + '">' + data[i].ND_OLDNAME + '</a></tr></td>'
                        $("#tabletbody").children().remove();
                        $("#tabletbody").append(str);
                    }
                }
            }
        });
    });*/
 </script>
</html>