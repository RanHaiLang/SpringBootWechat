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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- 用途说明：分页栏显示生成工具(用于所有需要分页的页面，协助生成分页工具栏)  --%>
<c:set var="url" value="${param.url }" />
<c:set var="query" value="${param.query}" />
<!-- 请求分页地址及请求条件带&不带countPerPage参数 -->
<ul class="pagination pagination-sm">
	<%-- 首页 --%>
	<c:choose>
		<c:when test="${pageInfo.pageNum == 1 }">
			<li class="disabled"><span>首页</span></li>
		</c:when>
		<c:otherwise>
			<li><a
				href="${url }?page=1&rows=${pageInfo.pageSize }${query }">首页</a></li>
		</c:otherwise>
	</c:choose>
	<%-- 上一页 --%>
	<c:choose>
		<c:when test="${pageInfo.pageNum <= 1 }">
			<li class="disabled"><span>上一页</span></li>
		</c:when>
		<c:otherwise>
			<li><a href="${url }?page=${pageInfo.pageNum - 1 }&rows=${pageInfo.pageSize }${query }">上一页</a></li>
		</c:otherwise>
	</c:choose>
	<%-- 分页区块 --%>
	<c:choose>
		<c:when test="${pageInfo.pageNum>3}">
			<li class="disabled"><span>...</span></li>
		</c:when>
	</c:choose>

	<c:choose>
		<c:when test="${pageInfo.pageNum-3>0}">
			<c:forEach var="p" begin="${pageInfo.pageNum-3}"
				end="${pageInfo.pageNum+3}" step="1">
				<c:choose>
					<c:when test="${p==pageInfo.pageNum }">
						<li class="active"><span>${p }</span></li>
					</c:when>
					<c:when test="${p>pageInfo.pages }">
					</c:when>
					<c:when test="${p<=0 }">
					</c:when>
					<c:otherwise>
						<li><a
							href="${url }?page=${p}&rows=${pageInfo.pageSize }${query }">${p }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:when>
		<c:when test="${pageInfo.pageNum-2>0}">
			<c:forEach var="p" begin="${pageInfo.pageNum-2}"
				end="${pageInfo.pageNum+3}" step="1">
				<c:choose>
					<c:when test="${p==pageInfo.pageNum }">
						<li class="active"><span>${p }</span></li>
					</c:when>
					<c:when test="${p>pageInfo.pages }">
					</c:when>
					<c:when test="${p<=0 }">
					</c:when>
					<c:otherwise>
						<li><a href="${url }?page=${p}&rows=${pageInfo.pageSize }${query }">${p }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:when>
		<c:when test="${pageInfo.pageNum-1>0}">
			<c:forEach var="p" begin="${pageInfo.pageNum-1}"
				end="${pageInfo.pageNum+3}" step="1">
				<c:choose>
					<c:when test="${p==pageInfo.pageNum }">
						<li class="active"><span>${p }</span></li>
					</c:when>
					<c:when test="${p>pageInfo.pages }">
					</c:when>
					<c:when test="${p<=0 }">
					</c:when>
					<c:otherwise>
						<li><a href="${url}?page=${p}&rows=${pageInfo.pageSize }${query }">${p }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:forEach var="p" begin="${pageInfo.pageNum}"
				end="${pageInfo.pageNum+3}" step="1">
				<c:choose>
					<c:when test="${p==pageInfo.pageNum }">
						<li class="active"><span>${p }</span></li>
					</c:when>
					<c:when test="${p>pageInfo.pages }">
					</c:when>
					<c:when test="${p<=0 }">
					</c:when>
					<c:otherwise>
                        <li><a
                                href="${url }?page=${p}&rows=${pageInfo.pageSize }${query }">${p }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${pageInfo.pages-pageInfo.pageNum >3}">
			<li class="disabled"><span>...</span></li>
		</c:when>
	</c:choose>

	<%-- 下一页 --%>
	<c:choose>
		<c:when test="${pageInfo.pageNum >= pageInfo.pages}">
			<li class="disabled"><span>下一页</span></li>
		</c:when>
		<c:otherwise>
			<li><a href="${url }?page=${pageInfo.pageNum + 1 }&rows=${pageInfo.pageSize }${query }">下一页</a></li>
		</c:otherwise>
	</c:choose>
	<%-- 尾页 --%>
	<c:choose>
		<c:when test="${pageInfo.pageNum==pageinfo.pages}">
			<li class="disabled"><span>尾页</span></li>
		</c:when>
		<c:otherwise>
			<li><a
				href="${url }?page=${pageInfo.pages }&rows=${pageInfo.pageSize }${query }">尾页</a></li>
		</c:otherwise>
	</c:choose>
</ul>

<ul class="pagination pagination-sm pull-right">
	<li class="disabled"><span>第 ${pageInfo.pageNum } -
			${pageInfo.pages } 页，共${pageInfo.total }条 / 每页
		<c:forEach var="c" items="10,20,50,100">
				<c:choose>
					<c:when test="${pageInfo.pageSize == c }">
	    			${pageInfo.pageSize }
					</c:when>
					<c:otherwise>
						<a href="${url }?page=1&rows=${c }${query}">${c }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach> 条
	</span></li>
</ul>
