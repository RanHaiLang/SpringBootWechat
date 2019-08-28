
<%@page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%--解决apache转发，无法访问静态资源问题--%>
<c:set var="ctxurl" value="${pageContext.request.getAttribute('jinmaourl')}"></c:set>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>公告栏</title>
    <link rel="SHORTCUT ICON" href="${ctxurl}/resources/images/favicon.ico"/>
    <link href="${ctxurl}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctxurl}/resources/css/styleAdmin.css" rel="stylesheet">
    <link href="${ctxurl}/resources/font/iconfont.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="${ctxurl}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${ctxurl}/resources/css/bootstrap-multiselect.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxurl}/resources/css/bootstrap-datetimepicker.min.css">
    <script src="${ctxurl}/resources/js/jquery-2.0.3.min.js"></script>
    <script src="${ctxurl}/resources/js/bootstrap.min.js"></script>
    <script src="${ctxurl}/resources/js/bootstrap-datetimepicker.js"></script>
    <script src="${ctxurl}/resources/js/Common.js"></script>
    <script src="${ctxurl}/resources/js/bootstrap-multiselect.js"></script>
</head>
<body>
<div class="container-fluid">
    <ul class="nav-tab">
        <li class="active">
            <a href="javascript:void(0)">公告栏</a>
        </li>
    </ul>
    <div class="tab-main row">
        <div class="tab-pane col-lg-12 col-sm-12 col-xs-12" style="">
            <div class="project-title">
                <a class="x-form-btn form-btn-default" href="#" data-toggle="modal" data-target="#excelOut"
                   title="创建公告" onclick="selectClass()">
                    <i class="iconfont">&#xe60b;</i>新增公告
                </a>
                <%--<a class="x-form-btn form-btn-default" href="#" onclick="noticeUpdate()" data-toggle="modal" data-target="#noticeUpdate"
                   title="编辑滚动条">
                    <i class="iconfont">&lt;%&ndash;&#xe643;&ndash;%&gt;</i>编辑滚动条
                </a>--%>
                <input id="updateFile" name="updateFile" class="x-form-text" type="hidden" value="${result}">
                <%--<button class="btn" data-toggle="modal" data-target="#excelOut" />编辑</button>&emsp;--%>
            </div>
            <div class="process-body">
                <div class="table-wrap" style="height:auto;">
                    <table class="table" id="user-list">
                        <thead>
                        <tr>
                            <th width="10%">公告编号</th>
                            <th width="30%">公告主题</th>
                            <th width="15%">公告类别</th>
                            <th width="17%">发布时间</th>
                            <th width="30%">操作</th>
                        </tr>
                        </thead>
                        <c:if test="${page!=null}">
                            <tbody>
                            <c:forEach items="${pageInfo.list}" var="list">
                                <tr>
                                    <td>${list.NOT_CODE}</td>
                                    <td>${list.NOT_DESC}</td>
                                    <td>${list.ENCDESC}</td>
                                    <td><fmt:formatDate value="${list.NOT_DATE}" type="both"/></td>
                                    <td class="handle">
                                        <button class="btn" onclick="updateUeditor('${list.NOT_CODE}')"/>
                                        编辑</button>&emsp;
                                        <button class="btn" data-toggle="modal" data-target="#UploadDocument"
                                                onclick="lookDoc('${list.NOT_CODE}')"/>附件</button>&emsp;
                                        <button class="btn" data-toggle="modal" data-target="#Uploadimage"
                                                onclick="uploadimage('${list.NOT_CODE}')"/>主题图片</button>&emsp;
                                            <%-- <a href="" onclick="updateUeditor('${list.NOT_CODE}')" target="_blank" >编辑</a>--%>
                                        <a href="${ctxurl}/ueditor/lookNotice?&ueditorCode=${list.NOT_CODE}"
                                           style="font-size: 15px" target="_blank">查看公告</a>
                                            <%--<input type="button" value="查看" onclick="lookUeditor('${list.NOT_CODE}')">--%>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </c:if>
                    </table>
                    <jsp:include page="page.jsp">
                        <jsp:param name="url"
                                   value="${ctxurl}/ueditor/homePage"></jsp:param>
                        <jsp:param name="query"
                                   value=""></jsp:param>
                    </jsp:include>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="UploadDocument" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width:600px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">上传附件</h4>
            </div>
            <div class="modal-body">
                <div class="project-title">
                </div>
                <div class="process-body">
                    <form id="UploadDocumentForm" name="UploadDocumentForm" action="${ctxurl}/ueditor/FileUplod"
                          method="post"
                          enctype="multipart/form-data"
                          class="form-inline">
                        <div class="process-body">
                            <div class="table-wrap" style="height:240px;">
                                <table class="table" id="tableActive">
                                    <thead>
                                    <tr>
                                        <th>附件名称</th>
                                        <th>附件描述</th>
                                        <th>删除附件</th>
                                    </tr>
                                    </thead>
                                    <tbody id="tabletbody" name="tabletbody">
                                    <%--<tr>
                                        <td>121323464964</td>
                                        <td>152453</td>
                                        <td>iPhone 7</td>
                                        <td>13888888888</td>
                                    </tr>--%>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                        <div class="select-wrap">
                            <input id="noticeCode" name="noticeCode" class="x-form-text" type="hidden" value="">
                            <span>附件描述：</span>
                            <div class="red-state"></div>
                            <div class="select-inner">
                                <input class="x-form-text" type="text" name="DocumentDesc" id="DocumentDesc">
                            </div>
                        </div>
                        </br>
                        <div class="select-wrap">
                            <span>请选择文件：</span>
                            <div class="red-state"></div>
                            <div class="project-item">
                                <div class="input-wrap">
                                    <div class="input-inner">
                                        <input type="file" class="x-form-text" name="myfiles" id="myfiles">
                                    </div>
                                </div>
                                </br>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="submitDocument()">上传
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" id="excelOut" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width:600px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">创建公告主题</h4>
            </div>
            <div class="modal-body">
                <div class="project-title">
                </div>
                <div class="process-body">
                    <form id="addForm" name="addForm" action="${ctxurl}/ueditor/addUeditor" method="post"
                          enctype="multipart/form-data"
                          class="form-inline">
                        <%--<div class="select-wrap">
                            <span>公告编号：</span>
                            <div class="red-state"></div>
                            <div class="select-inner">
                                <input class="x-form-text" type="text" name="ueditorCode" id="ueditorCode">
                            </div>
                        </div>
                        </br>--%>
                        <div class="select-wrap">
                            <span>公告主题：</span>
                            <div class="red-state"></div>
                            <div class="select-inner">
                                <input class="x-form-text" type="text" name="ueditorDesc" id="ueditorDesc">
                            </div>
                        </div><br/>
                            <div class="select-wrap">
                            <span>公告类别：</span>
                            <div class="red-state"></div>
                            <div class="select-inner">
                                <select id="classNoctice"  name="classNoctice" class="classNoctice"  >
                                </select>
                            </div>
                        </div>
                        </br>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="submitFile()">创建</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" id="Uploadimage" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width:600px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">上传主题图片</h4>
            </div>
            <div class="modal-body">
                <div class="project-title">
                </div>
                <div class="process-body">
                    <form id="UploadimageForm" name="UploadimageForm" action="${ctxurl}/ueditor/FileUplodimage"
                          method="post"
                          enctype="multipart/form-data"
                          class="form-inline">
                        <div class="process-body">
                            <div class="table-wrap" style="height:240px;">
                                <table class="table" id="tableimage">
                                    <thead>
                                    <tr>
                                        <th>附件名称</th>
                                        <th>附件描述</th>
                                        <th>删除附件</th>
                                    </tr>
                                    </thead>
                                    <tbody id="tabletbodyimage" name="tabletbodyimage">
                                    <%--<tr>
                                        <td>121323464964</td>
                                        <td>152453</td>
                                        <td>iPhone 7</td>
                                        <td>13888888888</td>
                                    </tr>--%>
                                    </tbody>
                                </table>

                            </div>
                        </div><input id="noticeCodeT" name="noticeCodeT" class="x-form-text" type="hidden" value="">
                        <%--<div class="select-wrap">
                            <span>附件描述：</span>
                            <div class="red-state"></div>
                            <div class="select-inner">
                                <input class="x-form-text" type="text" name="DocumentDesc" id="DocumentDesc">
                            </div>
                        </div>
                        </br>--%>
                        <div class="select-wrap">
                            <span>请选择图片：</span>
                            <div class="red-state"></div>
                            <div class="project-item">
                                <div class="input-wrap">
                                    <div class="input-inner">
                                        <input type="file" class="x-form-text" name="myfilesimage" id="myfilesimage">
                                    </div>
                                </div>
                                </br>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="imageSubmit()">上传
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" id="noticeUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width:600px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">编辑滚动条</h4>
            </div>
            <div class="modal-body">
                    <form id="noticeUpdateForm" name="noticeUpdateForm" action="${ctxurl}/ueditor/saveNoticeUpdate" method="post" enctype="multipart/form-data" class="form-inline">
                        <div class="process-body">
                            <input type="text" id="noticeUpdateAll" name="noticeUpdateAll" style="width: 550px;height: 26px;" maxlength="150"/>
                        </div>
                    </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="noticeUpdateSubmit()">保存
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script>
    // 标签页切换
    $(function () {
        $(".tab-main > .tab-pane").first().show().siblings().hide();
        $(".nav-tab > li").click(function () {
            var $index = $(this).index();
            $(this).addClass('active').siblings().removeClass('active');
            $(".tab-main > .tab-pane").eq($index).show().siblings().hide();
        })
    })
    //移动用户列表点击事件
    $(function () {
        $("#user-list>tbody>tr").click(function () {
            $(this).attr({'data-toggle': 'modal', 'data-target': '#user-information'});
            $(this).addClass("active").siblings().removeClass("active");
        })
    })
    function stopPropagation() {
        event.stopPropagation();

    }
    //隐患整改落实部门处理单选框选中
    $(function () {
        $(".check-no").click(function () {
            if ($(".check-no[value='no']").is(":checked")) {
                $(this).parents(".project-item").next(".project-textarea-item").show();
            } else {
                $(this).parents(".project-item").next(".project-textarea-item").hide();
            }
        })
    })
</script>
<!-- 日期js -->
<script type="text/javascript" src="${ctxurl}/resources/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript">
    /*window.location.reload();*/
    $("#datetimepicker,#start-time,#end-time,#app-date,#hand-start,#hand-end,#over-time").datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        autoclose: true,
        todayBtn: true,
        pickerPosition: "bottom-left",
        startDate: "2000-11-21",
        minuteStep: 30,
        minView: 2,
    });
    function submitFile() {
        /*var ueditorCode = $("#ueditorCode").val();*/
        var ueditorDesc = $("#ueditorDesc").val();
        var classNoctice = $("#classNoctice").val();
        /* if (ueditorCode == "" || ueditorCode == null) {
         alert("公告编号不可为空！")
         } else {*/
        if (ueditorDesc == "" || ueditorDesc == null) {
            alert("公告主题不可为空！")
        } else {
            if (classNoctice == "" || classNoctice == null) {
                alert("公告类别不可为空！")
            } else {
            /* $.ajax({
             url: '
            ${ctx}/ueditor/manyCode',
             data: {"ueditorCode": ueditorCode},
             type: "POST",
             success: function (data) {
             if (data == 0) {*/
            $('#addForm').submit();
            /*    } else {
             alert("该编号已存在，请更换！");
             }
             }
             });*/
            }
        }


        /*}*/
    }
    function updateUeditor(code) {
        window.location.href = "${ctxurl}/ueditor/editorPage?&ueditorCode=" + code;
    }
    function lookUeditor(code) {
        window.location.href = "${ctxurl}/ueditor/lookNotice?&ueditorCode=" + code;
    }
    function submitDocument() {
        var DocumentDesc = $("#DocumentDesc").val();
        var myfiles = $("#myfiles").val();
        var desc = $("#desc").val();
        if (DocumentDesc == "" || DocumentDesc == null) {
            alert("附件描述不可为空！")
        } else {
            if (myfiles == "" || myfiles == null) {
                alert("请选择上传文件！")
            } else {
                $('#UploadDocumentForm').submit();
            }
        }
    }
    function onclickUpdate(code) {
        $('#noticeCode').val(code);
    }
    $(document).ready(function () {
        var msg = $("#updateFile").val();
        if (msg != "" && msg != null) {
            if (msg == 0 && msg == 0) {
                alert("文件上传失败！");
            } else {
                alert("文件上传成功！");
            }
        }
    });
    function lookDoc(code) {
        /* var id = $("#inputThree").val();
         $.get("
        ${ctx}/appAdmin/selectEamName2", function (data) {
         for (var i = 0; i < data.length; i++) {
         str += '<tr>' +
         '<td>' + data[i].DN_OLDNAME + '</td>' +
         '<td>' + data[i].DN_DESC + '</td>' +
         '<button class="btn" onclick="deleteDoc('+ '\'' +data[i].DN_NEWNAME+ '\'' +')" />删除</button>' +
         '</tr>'
         }
         $("#tabletbody").children().remove();
         $("#tabletbody").append(str);
         });*/
        $("#noticeCode").val(code);
        $.ajax({
            url: '${ctxurl}/ueditor/selectDoc',
            data: {"noticeCode": code},
            type: "POST",
            success: function (data) {
                if(data.length==0){
                    $("#tabletbody").children().remove();
                    $("#tabletbody").append("<tr><td>暂无附件，请上传！</td><td></td><td></td></tr>");
                }else {
                    var str = '';
                    for (var i = 0; i < data.length; i++) {
                        str += '<tr><td>' + data[i].ND_OLDNAME + '</td><td>' + data[i].ND_DESC + '</td><td><a onclick="deleteDoc(' + '\'' + data[i].ND_ID + '\'' + ',' + '\'' + data[i].NOT_ND_CODE + '\'' + ')" >删除</a>' +
                                '&emsp;<a onclick="downFile(' + '\'' + data[i].ND_ID + '\''+ ')" >下载</a></td>' +
                                '</tr>'
                    }
                    $("#tabletbody").children().remove();
                    $("#tabletbody").append(str);
                }
            }
        });
    }
    function deleteDoc(docId, code) {
        if(del()==true){
            $.ajax({
                url: '${ctxurl}/ueditor/deleteDoc',
                data: {"docId": docId},
                type: "POST",
                success: function (data) {
                    if (data == 0) {
                        alert("系统繁忙！");
                    } else {
                        alert("删除成功！");
                        lookDoc(code);
                    }
                }
            });
        }
    }
    function downFile(downId) {
        window.location.href = "${ctxurl}/currency/downloadDoc?&downId=" + downId;
    }

    function del() {
        var msg = "您确定要删除该附件吗？";
        if (confirm(msg)==true){
            return true;
        }else{
            return false;
        }
    }
    function uploadimage(code) {

        $("#noticeCodeT").val(code);
        $.ajax({
            url: '${ctxurl}/ueditor/imageNews',
            data: {"noticeCodeT": code},
            type: "POST",
            success: function (data) {
                if(data.length==0){
                    $("#tabletbodyimage").children().remove();
                    $("#tabletbodyimage").append("<tr><td>暂无图片，请上传！</td><td></td><td></td></tr>");
                }else {
                    var str = '';
                        str += '<tr><td>' + data.NOTUDFCHAR01 + '</td><td>' + data.NOTUDFCHAR02 + '</td>'+
                                '<td>&emsp;<a onclick="downFileimage(' + '\'' + data.NOTUDFCHAR02 + '\''+ ')" >下载</a></td>' +
                                '</tr>'
                    $("#tabletbodyimage").children().remove();
                    $("#tabletbodyimage").append(str);
                }

            }
        });
    }
    function downFileimage(filename) {
        window.location.href = "${ctxurl}/currency/downloadimage?&filename=" + filename;
    }
    function imageSubmit() {
         var myfilesimage = $("#myfilesimage").val();
         var filesImage = myfilesimage.split(".");
         var strFormat = filesImage[filesImage.length-1];
        $.ajax({
            url: '${ctxurl}/ueditor/imageFormat',
            data: {"strFormat": strFormat},
            type: "POST",
            success: function (data) {
                if (data == true) {

/*


                   图片上传js控制压缩图片



*/
                    var fileObj = document.getElementById("myfilesimage").files[0];
                    var url = "${ctxurl}/ueditor/FileUplodimage";
                    var form = new FormData();

                    if(fileObj.size/1024 > 1025) { //大于1M，进行压缩上传
                        photoCompress(fileObj, {
                            quality: 0.2
                        }, function(base64Codes){
                            //console.log("压缩后：" + base.length / 1024 + " " + base);
                            var bl = convertBase64UrlToBlob(base64Codes);
                            form.append("myfilesimage", bl,$("#myfilesimage").val());/*Date.parse(new Date())+".jpg"*/
                            form.append("noticeCodeT", $("#noticeCodeT").val());
                            xhr = new XMLHttpRequest();  // XMLHttpRequest 对象
                            xhr.open("post", url, true); //post方式，url为服务器请求地址，true 该参数规定请求是否异步处理。
                            xhr.onload = uploadComplete; //请求完成
                            xhr.onerror =  uploadFailed; //请求失败
                            xhr.send(form); //开始上传，发送form数据
                            /*$("#myfilesimage").val(bl);*/

                        });
                    }else{ //小于等于1M 原图上传
                        form.append("myfilesimage", fileObj); // 文件对象
                        form.append("noticeCodeT", $("#noticeCodeT").val());
                        xhr = new XMLHttpRequest();  // XMLHttpRequest 对象
                        xhr.open("post", url, true); //post方式，url为服务器请求地址，true 该参数规定请求是否异步处理。
                        xhr.onload = uploadComplete; //请求完成
                        xhr.onerror =  uploadFailed; //请求失败
                        xhr.send(form); //开始上传，发送form数据
                    }
                   /* $('#UploadimageForm').submit();*/
                } else {
                    alert("图片格式不正确，请重新选择！");
                }
            }
        });
    }
 function noticeUpdate() {
     $.ajax({
         url: '${ctxurl}/ueditor/selectNoticeUpdate',
         type: "GET",
         success: function (data) {
             if (data.notice !=""&&data.notice!=null) {
                 $('#noticeUpdateAll').val(data.notice);
             }
         }
     });
 }
    function noticeUpdateSubmit() {
        $('#noticeUpdateForm').submit();
 }
    function selectClass() {
        $.ajax({
            url: '${ctxurl}/ueditor/selectClass',
            type: "GET",
            success: function (data) {
                    var str = '';
                    for (var i = 0; i < data.length; i++) {
                        str += '<option value="' + data[i].ENCENTITY + '">' + data[i].ENCDESC + '</option>';
                    }
                $("#classNoctice").children().remove();
                $('#classNoctice').append(str);
                }

        });
 }

</script>


<%--



图片压缩




--%>
<script type="text/javascript">
    /*
     三个参数
     file：一个是文件(类型是图片格式)，
     w：一个是文件压缩的后宽度，宽度越小，字节越小
     objDiv：一个是容器或者回调函数
     photoCompress()
     */
    function photoCompress(file,w,objDiv){
        var ready=new FileReader();
        /*开始读取指定的Blob对象或File对象中的内容. 当读取操作完成时,readyState属性的值会成为DONE,如果设置了onloadend事件处理程序,则调用之.同时,result属性中将包含一个data: URL格式的字符串以表示所读取文件的内容.*/
        ready.readAsDataURL(file);
        ready.onload=function(){
            var re=this.result;
            canvasDataURL(re,w,objDiv)
        }
    }
    function canvasDataURL(path, obj, callback){
        var img = new Image();
        img.src = path;
        img.onload = function(){
            var that = this;
            // 默认按比例压缩
            var w = that.width,
                    h = that.height,
                    scale = w / h;
            w = obj.width || w;
            h = obj.height || (w / scale);
            var quality = 0.7;  // 默认图片质量为0.7
            //生成canvas
            var canvas = document.createElement('canvas');
            var ctx = canvas.getContext('2d');
            // 创建属性节点
            var anw = document.createAttribute("width");
            anw.nodeValue = w;
            var anh = document.createAttribute("height");
            anh.nodeValue = h;
            canvas.setAttributeNode(anw);
            canvas.setAttributeNode(anh);
            ctx.drawImage(that, 0, 0, w, h);
            // 图像质量
            if(obj.quality && obj.quality <= 1 && obj.quality > 0){
                quality = obj.quality;
            }
            // quality值越小，所绘制出的图像越模糊
            var base64 = canvas.toDataURL('image/jpeg', quality);
            // 回调函数返回base64的值
            callback(base64);
        }
    }
    /**
     * 将以base64的图片url数据转换为Blob
     * @param urlData
     *            用url方式表示的base64图片数据
     */
    function convertBase64UrlToBlob(urlData){
        var arr = urlData.split(','), mime = arr[0].match(/:(.*?);/)[1],
                bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
        while(n--){
            u8arr[n] = bstr.charCodeAt(n);
        }
        return new Blob([u8arr], {type:mime});
    }


    var xhr;
    //上传文件方法
   /* function UpladFile() {
        var fileObj = document.getElementById("file").files[0]; // js 获取文件对象
        var url = "后台图片上传接口"; // 接收上传文件的后台地址

        var form = new FormData(); // FormData 对象

        if(fileObj.size/1024 > 1025) { //大于1M，进行压缩上传
            photoCompress(fileObj, {
                quality: 0.2
            }, function(base64Codes){
                //console.log("压缩后：" + base.length / 1024 + " " + base);
                var bl = convertBase64UrlToBlob(base64Codes);
                form.append("file", bl, "file_"+Date.parse(new Date())+".jpg"); // 文件对象
                xhr = new XMLHttpRequest();  // XMLHttpRequest 对象
                xhr.open("post", url, true); //post方式，url为服务器请求地址，true 该参数规定请求是否异步处理。
                xhr.onload = uploadComplete; //请求完成
                xhr.onerror =  uploadFailed; //请求失败
                xhr.send(form); //开始上传，发送form数据
            });
        }else{ //小于等于1M 原图上传
             form.append("file", fileObj); // 文件对象
            xhr = new XMLHttpRequest();  // XMLHttpRequest 对象
            xhr.open("post", url, true); //post方式，url为服务器请求地址，true 该参数规定请求是否异步处理。
            xhr.onload = uploadComplete; //请求完成
            xhr.onerror =  uploadFailed; //请求失败
            xhr.send(form); //开始上传，发送form数据
        }
    }*/

    //上传成功响应
    function uploadComplete(evt) {
        //服务断接收完文件返回的结果
        var data = JSON.parse(evt.target.responseText);
        /*alert(data);"${ctx}/ueditor/homePage"*/
        window.location.href = "${ctxurl}/ueditor/homePage?&updateFile=" + data;
        /*if(data.success) {
            alert("上传成功！");
        }else{
            alert("上传失败！");
        }*/

    }
    //上传失败
    function uploadFailed(evt) {
        alert("上传失败！");
    }
    //取消上传
    function cancleUploadFile(){
        xhr.abort();
    }
</script>
</body>
</html>
