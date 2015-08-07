<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="com.muktalabs.em.controller.CompanyController"%>
<%@page import="org.springframework.web.context.support.SpringBeanAutowiringSupport"%>
<%@page import="com.muktalabs.em.model.Company"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@include file="header.jsp" %>
<div id="IT" class="pad-top-botm">
	<div class="container">
		<div class="row text-center ">
			<div
				class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2">
				<h2 data-wow-delay="0.3s" class="wow rollIn animated">
					<strong>COMPANY LIST</strong>
				</h2>
				<p class="sub-head"></p>

			</div>
		</div>

<%!
	public void jspInit() {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext());
	}
	
	@Autowired
	CompanyController companyController;
%>

<%
	List<Company> companies = companyController.getAllCompanys(0, 20, session);
%>
<div>
<div>
<h3>"${message}"</h3>
</div>

<form action="/em/ws/company/editordelete" method="post">
<table>
<thead>
	<tr>
		<td>#</td>
		<td>Company Name</td>
	</tr>
</thead>
<tbody>
<% for(Company company : companies) { %>
	<tr>
		<td><input type="checkbox" value="<%=company.getCompanyId()%>" name="companyId" id="companyId"></td>
		<td><label><%=company.getCompanyName()%></label></td>
	</tr>
<% } %>
	<tr>
		<td><button type="submit" class="btn btn-success" name="operation" value="edit">Edit</button></td>
		<td><button type="submit" class="btn btn-success" name="operation" value="delete">Delete</button></td>
	</tr>
</tbody>

</table>
</form>
</div>
		
</div>
</div>
<%@include file="footer.jsp" %>

<script type="text/javascript">

</script>
