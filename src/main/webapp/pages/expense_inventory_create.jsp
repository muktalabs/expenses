<%@page import="com.muktalabs.em.model.User"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="org.springframework.web.context.support.SpringBeanAutowiringSupport"%>
<%@page import="com.muktalabs.em.controller.ExpenseTypeController"%>
<%@page import="com.muktalabs.em.model.ExpenseType"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@include file="header.jsp" %>

<%!
	public void jspInit() {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext());
	}
	
	@Autowired
	ExpenseTypeController expenseTypeController;
%>
<%
	List<ExpenseType> expenseTypes = expenseTypeController.getAllExpenseType(0, 20, session);
	System.out.println("blah blah blah");	
	User user=(User)session.getAttribute("loggedinuser");
	String uid=user.getUserId();
	System.out.println("user :   "+user.getUserId() + "   value:   " + uid);
	String cid=user.getCompanyId();
	System.out.println("company :  "+user.getCompanyId() + "   value:   " + cid);
%>


<body onload="onLoadFunction();">
<div id="IT" class="pad-top-botm">
	<div class="container">
		<div class="row text-center ">
			<div
				class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2">
					<p class="sub-head"></p>
			</div>
		</div>
		<div class="row ">
			<div class="col-lg-4 col-md-4 col-sm-4 ">
				<div class="panel-body">
					<form class="form-horizontal ng-pristine ng-valid"
						action="/em/ws/expenseinventory/create" method="post">
						<input type="hidden" name="inventoryId" id="inventoryId" value="${expenseInventoryData.inventoryId}" />
						<div class="form-group">Expense Type
	              				<select name="expenseTypeId" class="form-control">
    			        			<% for(ExpenseType expenseType : expenseTypes) { %>
            						    <option value="<%=expenseType.getExpenseTypeId()%>"><%=expenseType.getExpenseType()%></option>
              						<% } %>
              					</select>
              			</div>
						<div class="form-group">
							<label for="userId" class="col-sm-3 control-label">User ID</label>
							<div class="col-sm-9">
								<input type="hidden" class="form-control" id="userId"
									name="userId" placeholder="User Id" value="<%=uid%>">
							</div>
						</div>
						<div class="form-group">
							<label for="companyId" class="col-sm-3 control-label">Company ID</label>
							<div class="col-sm-9">
								<input type="hidden" class="form-control" id="companyId"
									name="companyId" placeholder="Company Id" value="<%=cid%>">
							</div>
						</div>
						
						<div class="form-group">Transaction Type
              				<select name="transactionType" class="form-control">
            			    <option value="Purchase">Purchase</option>
              				<option value="Service">Service</option>
              				<option value="Rental">Rental</option>
              				</select>
          				</div>
						
						
      	              <div class="form-group">
   						<label for="date" class="col-sm-3 control-label">Date</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="transactionDate"
									name="transactionDate" placeholder="transactionDate" value="${expenseInventoryData.transactionDate}">
							</div>
    				</div>
					
						<div class="form-group">
							<label for="cost" class="col-sm-3 control-label">Cost</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="cost"
									name="cost" placeholder="cost" value="${expenseInventoryData.cost}">
							</div>
						</div>
						
						<div class="form-group">
							<label for="description" class="col-sm-3 control-label">Description</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="description"
									name="description" placeholder="Transaction Description" value="${expenseInventoryData.description}">
							</div>
						</div>
						
						<div class="form-group">Payment Mode : 
              				<select name="paymentMode" class="form-control" id="paymentMode">
            			    <option value="Cash">Cash</option>
              				<option value="Credit Card">Credit Card</option>
              				<option value="Debit Card">Debit Card</option>
              				<option value="Cheque">Cheque</option>
              				 </select>
          				</div>
						
						<div class="form-group">
							<label for="voucherNo" class="col-sm-3 control-label">Voucher Number</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="voucherNo"
									name="voucherNo" placeholder="Voucher No" value="${expenseInventoryData.voucherNo}">
							</div>
						</div>
										
						
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-success pull-right">Create Expense Inventory
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

</body>

<%@include file="footer.jsp" %>

</html>


<script type="text/javascript">

$(document).ready(function(){
	
	$('#transactionDate').datepicker({
		changeMonth:true,
		changeYear:true,
		yearRange:'-120:+0'
	});	
	
});
</script>
