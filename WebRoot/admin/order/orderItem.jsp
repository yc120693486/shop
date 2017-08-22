<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%><!-- 引入Struts2标签 -->
<style>
<!--
	td{
		text-align: center;
	}
-->
</style>
<table border="1" width="100%" cellspacing="0">
	<tr>
		<td>详情</td>
		<td>数量</td>
		<td>小计</td>
	</tr>
	<s:iterator var="orderItem" value="list">
	<tr>
		<td><img width="40px" height="40px" src="${pageContext.request.contextPath}/<s:property value="#orderItem.product.image"/>"></td>
		<td><s:property value="#orderItem.count"/></td>
		<td><s:property value="#orderItem.subtotal"/></td>
	</tr>
	</s:iterator>
</table>