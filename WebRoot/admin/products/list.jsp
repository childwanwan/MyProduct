<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/admin/css/Style.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/admin/js/public.js"></script>
<script type="text/javascript">
	function addProduct() {
		window.location.href = "${pageContext.request.contextPath}/admin/products/add.jsp";
	}
	
	//提示用户是否删除
	function delBook(id,name){
		if(confirm("是否确定删除:"+name+"?")){
			location.href="${pageContext.request.contextPath }/delBookServlet?id="+id;
		}
	}
	
	//全选/全不选
	function checkAll(){
		//得到ckAll元素，并得到它的选中状态
		var flag = document.getElementById("ckAll").checked;
		//alert(flag);
		//得到所有ids复选框元素
		var ids = document.getElementsByName("ids");
		//循环给每个复选框赋值
		for(var i =0; i<ids.length;i++){
			ids[i].checked = flag;//把ckAll元素的状态赋值给每一个ids[i]的元素
		} 
		
		//得到所有ids复选框元素
		/* var ids = document.getElementsByName("ids");
		for(var i =0; i<ids.length;i++){
			ids[i].checked = !ids[i].checked;//实现反选
		}  */
	}
	
	//批量删除
	function delAllBooks(){
		/* //得到所有复选框元素
		var ids = document.getElementsByName("ids");
		var str="";
		//循环得到选中的复选框的id
		for(var i=0;i<ids.length;i++){
			if(ids[i].checked){
				str+="ids="+ids[i].value+"&";
			}
		}
		str = str.substring(0, str.length-1); //ids=1001&ids=1002&ids=1003
		//alert(str);
		if(str!="")
			location.href="${pageContext.request.contextPath }/servlet/delAllBooksServlet?"+str; */
		//var form2 = document.getElementById("form2");
		var form2 = document.forms[1];//同上。得到页面中第二个表单
		//给当前表单中的action属性赋值
		//form2.action="${pageContext.request.contextPath }/servlet/delAllBooksServlet";
		form2.submit();//相当于点击submit按钮
		
	}
</script>
</HEAD>
<body>
	<br>
	
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>查
							询 条 件</strong>
					</td>
				</tr>
				<tr>
					<td>
				<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/searchBooksServlet"
		method="post">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									商品编号</td>
								<td class="ta_01" bgColor="#ffffff"><input type="text"
									name="id" size="15" value="" id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									类别：</td>
								<td class="ta_01" bgColor="#ffffff"><select name="category"
									id="category">
										<option value="" selected="selected">--选择商品类加--</option>
										<option value="文学">文学</option>
										<option value="生活">生活</option>
										<option value="计算机">计算机</option>
										<option value="外语">外语</option>
										<option value="经营">经营</option>
										<option value="励志">励志</option>
										<option value="社科">社科</option>
										<option value="学术">学术</option>
										<option value="少儿">少儿</option>
										<option value="艺术">艺术</option>
										<option value="原版">原版</option>
										<option value="科技">科技</option>
										<option value="考试">考试</option>
										<option value="生活百科">生活百科</option>
								</select></td>
							</tr>

							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									商品名称：</td>
								<td class="ta_01" bgColor="#ffffff"><input type="text"
									name="name" size="15" value="" id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									价格区间(元)：</td>
								<td class="ta_01" bgColor="#ffffff"><input type="text"
									name="minprice" size="10" value="" />- <input type="text"
									name="maxprice" size="10" value="" /></td>
							</tr>

							<tr>
								<td width="100" height="22" align="center" bgColor="#f5fafe"
									class="ta_01"></td>
								<td class="ta_01" bgColor="#ffffff"><font face="宋体"
									color="red"> &nbsp;</font>
								</td>
								<td align="right" bgColor="#ffffff" class="ta_01"><br>
									<br></td>
								<td align="right" bgColor="#ffffff" class="ta_01">
									<button type="submit" id="search" name="search"
										value="&#26597;&#35810;" class="button_view">
										&#26597;&#35810;</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
									type="reset" name="reset" value="&#37325;&#32622;"
									class="button_view" />
								</td>
							</tr>
						</table>
			</form>
					</td>

				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>商品列表</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="right">
						<button type="button" id="add" name="add" value="批量删除"
							class="button_add" onclick="delAllBooks()">批量删除
						</button>
						<button type="button" id="add" name="add" value="&#28155;&#21152;"
							class="button_add" onclick="addProduct()">&#28155;&#21152;
						</button>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
				<form action="${pageContext.request.contextPath }/delAllBooksServlet" method="post">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="10%"><input type="checkbox" id="ckAll" onclick="checkAll()"/>全选/全不选</td>
								<td align="center" width="24%">	商品编号</td>
								<td align="center" width="18%">商品名称</td>
								<td align="center" width="9%">商品价格</td>
								<td align="center" width="9%">商品数量</td>
								<td width="8%" align="center">商品类别</td>
								<td width="8%" align="center">编辑</td>

								<td width="8%" align="center">删除</td>
							</tr>

			<c:forEach items="${books }" var="b">							
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="23"><input type="checkbox" name="ids" value="${b.id }"/></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="23">${b.id }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%">${b.name }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">${b.price }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">${b.pnum }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">${b.category }</td>
									<td align="center" style="HEIGHT: 22px" width="7%"><a
										href="${pageContext.request.contextPath}/findBookByIdServlet?id=${b.id }">
											<img
											src="${pageContext.request.contextPath}/admin/images/i_edit.gif"
											border="0" style="CURSOR: hand"> </a>
									</td>

									<td align="center" style="HEIGHT: 22px" width="7%"><a
										href="javascript:delBook('${b.id }','${b.name }')">
											<img
											src="${pageContext.request.contextPath}/admin/images/i_del.gif"
											width="16" height="16" border="0" style="CURSOR: hand">
									</a>
									</td>
								</tr>
					</c:forEach>	
						</table>
				</form>
					</td>
				</tr>
			</TBODY>
		</table>
	
</body>
</HTML>

