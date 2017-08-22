<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%><!-- 引入Struts2标签 -->
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
	</HEAD>
	
	<body>
		<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/adminProduct_save.action" method="post" enctype="multipart/form-data">
			&nbsp;
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<strong>添加商品</strong>
					</td>
				</tr>

				<tr>
					<td height="80px;" align="center" bgColor="#f5fafe" class="ta_01">
						商品名称：<input type="text" name="pname" value="" class="bg" style="border:1px solid black;"/>
					</td>
					<td  height="80px;" align="center" bgColor="#f5fafe" class="ta_01">
						市场价格：<input type="text" name="shop_price" value="" class="bg" style="border:1px solid black;"/>
					</td>
					<td  height="80px;" align="center" bgColor="#f5fafe" class="ta_01">
						商城价格：<input type="text" name="market_price" value="" class="bg" style="border:1px solid black;"/>
					</td>
					</tr>
					<tr>
					<td  height="80px;" align="center" bgColor="#f5fafe" class="ta_01">
						是否热门：<select name = "is_hot">
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</td>
					<td class="ta_01" bgColor="#f5fafe">
						所属的二级分类：
						<select name="categorySecond.csid">
							<s:iterator value="csList" var= "c">
								<option value="<s:property value="#c.csid"></s:property>"><s:property value="#c.csname"></s:property></option>
							</s:iterator>
						</select>
					</td>
					<td class="ta_01" bgColor="#f5fafe">
					商品图片：<input type="file" name="upload" />
					</td>
					
				</tr>
				<tr>
					<td height="80px;" align="center" bgColor="#f5fafe" class="ta_01" colspan="3">
						商品描述：<textarea name="pdesc" rows="3" cols="20" style="border:1px solid black;"></textarea>
					</td>
				</tr>
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<button type="submit"  value="确定" class="button_ok">
							&#30830;&#23450;
						</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
						<span id="Label1"></span>
					</td>
				</tr>
			</table>
		</form>
	</body>
</HTML>