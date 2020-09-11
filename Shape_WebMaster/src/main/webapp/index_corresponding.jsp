<%--
  Created by IntelliJ IDEA.
  User: YXX
  Date: 2019/11/11
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>Process Result</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

</head>

<body>
操作成功<br>
</body>

<%--</html>--%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
<%--    <title>显示图片</title>--%>

<%--    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">--%>
<%--    <meta http-equiv="description" content="this is my page">--%>
<%--    <meta http-equiv="content-type" content="text/html; charset=UTF-8">--%>
<%--</head>--%>


<%--<body style="overflow-x: hidden;overflow-y: hidden;margin: 0;padding: 0;">--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
<%--    function request(paras){--%>
<%--        var url = location.href;--%>
<%--        var paraString = url.substring(url.indexOf("?")+1,url.length).split("&");--%>
<%--        var paraObj = {}--%>
<%--        for (i=0; j=paraString[i]; i++){--%>
<%--            paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length);--%>
<%--        }--%>
<%--        var returnValue = paraObj[paras.toLowerCase()];--%>
<%--        if(typeof(returnValue)=="undefined"){--%>
<%--            return "";--%>
<%--        }else{--%>
<%--            return returnValue;--%>
<%--        }--%>
<%--    }--%>

<%--    window.οnlοad=function() {--%>
<%--        var len=document.documentElement.clientWidth-40;--%>
<%--        var hit=document.documentElement.clientHeight-10;--%>
<%--        var addr=request('addr');--%>
<%--        document.writeln("<img src='/ImgServlet?imgPath="+addr+"' border=0 width='"+len+"' height='"+hit+"'/>");--%>
<%--    }--%>