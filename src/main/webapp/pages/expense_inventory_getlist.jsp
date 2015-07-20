<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@include file="header.jsp" %>


<div id="IT" class="pad-top-botm">
	<div class="container">
		<div class="row text-center ">
			<div
				class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2">
				<h2 data-wow-delay="0.3s" class="wow rollIn animated">
					<strong>EXPENSE INVENTORY</strong>
				</h2>
				<p class="sub-head"></p>

			</div>
		</div>
		<div class="row ">
			<div class="col-lg-4 col-md-4 col-sm-4 ">
				<div class="panel-body">
					<form class="form-horizontal ng-pristine ng-valid"
						action="${baseURL}/pages/expense_inventory_list.jsp" method="post">
						
	
					<div class="form-group">
   						<label for="date" class="col-sm-3 control-label">From Date</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="fromDate"
									name="fromDate" placeholder="From Date" >
							</div>
    				</div>
									
					
					
						 <div class="form-group">
   						<label for="date" class="col-sm-3 control-label"> To Date</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="toDate"
									name="toDate" placeholder="To Date" >
							</div>
    				</div>
					
					
					 
					
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-success pull-right">Get all expenses
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
<script type="text/javascript">

$(document).ready(function(){
	
	$('#toDate').datepicker({
		changeMonth:true,
		changeYear:true,
		yearRange:'-120:+0'
	});	
	
	$('#fromDate').datepicker({
		changeMonth:true,
		changeYear:true,
		yearRange:'-120:+0'
	});	
	
});
</script>