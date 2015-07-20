<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@include file="header.jsp" %>

<body>
<div id="IT" class="pad-top-botm">
	<div class="container">
		<div class="row text-center ">
			<div
				class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2">
				<h2 data-wow-delay="0.3s" class="wow rollIn animated">
					<strong>CREATE EXPENSE TYPE</strong>
				</h2>
				<p class="sub-head"></p>

			</div>
		</div>
		<div class="row ">
			<div class="col-lg-4 col-md-4 col-sm-4 ">
				<div class="panel-body">
					<form class="form-horizontal ng-pristine ng-valid"
						action="/em/ws/expensetype/create" method="post">
						
						<input type="hidden" name="expenseTypeId" id="expenseTypeId" value="${expenseTypeData.expenseTypeId}" />
						<div class="form-group">
							<label for="expenseType" class="col-sm-3 control-label">Expense Type</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="expenseType"
									name="expenseType" placeholder="ExpenseType" value="${expenseTypeData.expenseType}">
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="col-sm-3 control-label">Description</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="description"
									name="description" placeholder="Expense Description" value="${expenseTypeData.description}">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-success pull-right">Create ExpenseType
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