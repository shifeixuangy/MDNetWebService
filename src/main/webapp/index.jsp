<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="commonUtil.CommonVariable" %>
<%@ page import="commonUtil.AppConfig" %>
<%@ page import="data.common.AccessType" %>
<%@ page import="init.config.RegisterService" %>
<html>
<head>
    <title>服务端信息</title>
</head>
<body>
    <h2>Jersey RESTful Web Application!</h2>
   <ul>
       <li>服务端版本：<%=CommonVariable.getApiVersion()%></li>
       <li>授权状态：<%=CommonVariable.getIsValid()?"已授权":"未授权"%></li>
       <li>授权到期时间：<%=CommonVariable.getIsValid()?RegisterService.getExpireTime().toString():"未知"%></li>
       <li>项目标记：<%=CommonVariable.getProjToken()%></li>
       <li>开启推送：<%=CommonVariable.is_PUSH_ENABLE()%></li>
       <li>部署地址：<%=CommonVariable.getDeployAddress()%></li>
       <li>数据库版本：<%=CommonVariable.getCurrentDBVersion()%></li>
       <li>测试项目编号：<%=CommonVariable.getTestProjID()%></li>
       <li>Android项目名称：<%=AppConfig.getAppName(AccessType.ANDROID)%></li>
       <li>Ios项目名称：<%=AppConfig.getAppName(AccessType.IOS)%></li>
       <li>Web项目名称：<%=AppConfig.getAppName(AccessType.WEB)%></li>
       <li>Android背景：<%=AppConfig.getBG(AccessType.ANDROID)%></li>
       <li>Ios背景：<%=AppConfig.getBG(AccessType.IOS)%></li>
       <li>Web背景：<%=AppConfig.getBG(AccessType.WEB)%></li>
   </ul>
</body>
</html>
