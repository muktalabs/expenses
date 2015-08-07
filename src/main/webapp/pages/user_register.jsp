<%@page import="com.muktalabs.em.model.Company"%>
<%@page import="java.util.List"%>
<%@page import="com.muktalabs.em.controller.CompanyController"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="org.springframework.web.context.support.SpringBeanAutowiringSupport"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@include file="header1.jsp" %>

<%!
	public void jspInit() {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext());
	}
	
	@Autowired
	CompanyController companyController;
%>
<%
List<Company> companys = companyController.getAllCompanys(0, 20, session);
%>




<div id="IT" class="pad-top-botm">
	<div class="container">
		<div class="row text-center ">
			<div
				class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2">
				<h2 data-wow-delay="0.3s" class="wow rollIn animated">
					<strong>Register </strong>
				</h2>
				<p class="sub-head"></p>

			</div>
		</div>
		<div class="row ">
			<div class="col-lg-4 col-md-4 col-sm-4 ">
				<div class="panel-body">
					<form class="form-horizontal ng-pristine ng-valid"
						action="/em/ws/user/create" method="post">
						
						<input type="hidden" name="userId" value="${userData.userId}" />
						
						<div class="form-group"> Company Name :
							<select name="companyId" class="form-control">
    			        			<% for(Company company : companys) { %>
            						    <option value="<%=company.getCompanyId()%>"><%=company.getCompanyName()%></option>
              						<% } %>
              					</select>
						</div>
						
						<div class="form-group"> Login ID : 
                                    <input type="text" id="loginId" name="loginId" class="form-control" required="required" placeholder="Login ID">
                         </div>
                         <div class="form-group"> Password: 
                                    <input type="password" id="password" name="password" class="form-control" required="required" placeholder="Password">
                         </div>
						<div class="form-group"> Re- Enter Password :
                                    <input type="password" id="password2" name="password2" class="form-control" required="required" placeholder="Password">
                         </div>
						
						
						
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-success pull-right">Create User Account
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="footer.jsp" %>

</html>