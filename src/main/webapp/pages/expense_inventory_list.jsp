<%@page import="java.util.Date"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.muktalabs.em.model.ExpenseType"%>
<%@page import="com.muktalabs.em.controller.ExpenseTypeController"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="com.muktalabs.em.controller.ExpenseInventoryController"%>
<%@page import="com.muktalabs.em.controller.ExpenseTypeController"%>

<%@page import="org.springframework.web.context.support.SpringBeanAutowiringSupport"%>
<%@page import="com.muktalabs.em.model.ExpenseInventory"%>
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
	ExpenseInventoryController expenseInventoryController;

	@Autowired
	ExpenseTypeController expenseTypeController;
%>

<%

		
	List<ExpenseInventory> expense_inv = expenseInventoryController.getExpenseInventoryByDates( new Date(request.getParameter("fromDate")),new Date(request.getParameter("toDate")), session);
	
%>
<%
	List<ExpenseType> expense_type = expenseTypeController.getAllExpenseType(0, 20, session);
				HashMap hm = new HashMap();


				for(ExpenseType expense_type1 : expense_type) {
					
					hm.put(expense_type1.getExpenseTypeId(),expense_type1.getExpenseType());	
					
				}	

%>

<div>
<form action="/em/ws/expenseinventory/editordelete" method="post">
<table border="2" bordercolor="black">
<thead>
<tr>
		<td> </td>
		<td> Expense Type </td>
		<td>  Cost </td>
		<td> Payment Mode </td>
		<td> Description </td>
		<td> Date  </td>
		<td> Transaction Type </td>
		<td> Edit </td>
		<td> Delete </td>
</tr>
</thead>
<tbody>
<% for(ExpenseInventory expenseInventory : expense_inv) { %>
	<tr>
		<td><input type="checkbox" value="<%=expenseInventory.getInventoryId()%>" name="InventoryId" id="expenseInventoryId"></td>
		<td> <label><%= hm.get(expenseInventory.getExpenseTypeId()) %></label></td>
		<td> <label><%=expenseInventory.getCost()%></label></td>
		<td>  <label><%=expenseInventory.getPaymentMode()%></label></td>
		<td>  <label><%=expenseInventory.getDescription()%></label></td>
		<td>  <label><%=expenseInventory.getTransactionDate()%></label></td>
		<td>  <label><%=expenseInventory.getTransactionType()%></label></td>
		<td><button type="submit" class="btn btn-success" name="operation" value="edit">Edit</button></td>
		<td><button type="submit" class="btn btn-success" name="operation" value="delete">Delete</button></td>
		
	</tr>
<% } %>
	
</tbody>

</table>
</form>
</div>
		
</div>
</div>
<%@include file="footer.jsp" %>

<script type="text/javascript">

</script>
