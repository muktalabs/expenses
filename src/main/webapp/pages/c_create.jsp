<div id="IT" class="pad-top-botm">
	<div class="container">
		<div class="row text-center ">
			<div
				class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2">
				<h2 data-wow-delay="0.3s" class="wow rollIn animated">
					<strong>CREATE COMPANY</strong>
				</h2>
				<p class="sub-head"></p>

			</div>
		</div>
		<div class="row ">
			<div class="col-lg-4 col-md-4 col-sm-4 ">
				<div class="panel-body">
					<form class="form-horizontal ng-pristine ng-valid"
						action="/em/ws/company/create" method="post">
						
						<input type="hidden" name="companyId" value="${companyData.companyId}" />
						<div class="form-group">
							<label for="company" class="col-sm-3 control-label">Company Name</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="companyName"
									name="companyName" placeholder="Company" value="${companyData.companyName}">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-success pull-right">Create Company
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
