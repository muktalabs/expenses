<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />

<head>
	    <link rel='stylesheet prefetch' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css'>

        <link rel="stylesheet" href="css/style.css">
	
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
    <title>Expense Management</title>
    <!--  Bootstrap Style -->
    <link href="${baseURL}/css/bootstrap.css" rel="stylesheet" />
    <!--  Font-Awesome Style -->
    <link href="${baseURL}/css/font-awesome.min.css" rel="stylesheet" />
    <!--  Animation Style -->
    <link href="${baseURL}/css/animate.css" rel="stylesheet" />
    <!--  Pretty Photo Style -->
    <link href="${baseURL}/css/prettyPhoto.css" rel="stylesheet" />
    <!--  Google Font Style -->
    <link href='${baseURL}/http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    <!--  Custom Style -->
    <link href="${baseURL}/css/style.css" rel="stylesheet" />
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body>
    <div id="pre-div">
        <div id="loader">
        </div>
    </div>
    <!--/. PRELOADER END -->
    <div class="navbar navbar-default navbar-fixed-top move-me ">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>

                <a class="navbar-brand" href="#">
                    

                </a>
            </div>
            <div class="navbar-collapse collapse move-me">
                <ul class="nav navbar-nav navbar-right">
                    <li >
                        <a href="#home">HOME 
                        </a>
                    </li>
                    <li >
                        <a href="#about">ABOUT US
                        </a>
                    </li>
                    <li >
                        <a href="#port-folio">GALLERY
                        </a>
                    </li>
                    <li >
                        <a href="#help">HOW TO HELP?
                        </a>
                    </li>
                    <li >
                        <a href="#contact">CONTACT US 
                        </a>
                    </li>
                </ul>
            </div>

        </div>
    </div>
    <!--./ NAV BAR END -->
    <div id="home" >
        <div class="overlay">
            <div class="container">
                <div class="row ">
                    <div class="col-lg-7 col-md-9 head-text">
                        <h1 id="divDisp" >EXPENSE MANAGEMENT</h1>
                        <span >
                            <i class="fa fa-lightbulb-o " ></i>View your company expenses
                        </span>
                        <span>
                            <i class="fa fa-lightbulb-o" ></i>Manage company expenses
                        </span>
                    </div>
                    <div class="col-lg-3 col-md-4">
                        <div class="div-trans text-center">
                        <form method="post" action="/em/ws/login/loginuser">
                            
                            <h2 ><i class="fa fa-code" ></i><span >SIGN IN</span>  <i class="fa fa-code"></i></h2>
                            <div class="col-lg-12 col-md-12 col-sm-12" >
                                <div class="form-group">
                                    <input type="text" id="userid" name="userid" class="form-control" required="required" placeholder="User ID">
                                </div>
                                <div class="form-group">
                                    <input type="password" id="password" name="password" class="form-control" required="required" placeholder="Password">
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-success btn-block btn-lg">SIGN IN</button>
                                </div>
                                <div class="form-group">
    								<a href="${baseURL}/pages/user_register.jsp">Register</a>  
  								</div> 
                                
                             </div>
							</form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--./ HOME SECTION END -->
    <div id="footser-end">
        <div class="container">
            <div class="row">
            </div>

        </div>
    </div>
      <!--./ FOOTER SECTION END -->
    <script>
    	
    </script>
    <!--  Jquery Core Script -->
    <script src="${baseURL}/js/jquery-1.10.2.js"></script>
    <!--  Core Bootstrap Script -->
    <script src="${baseURL}/js/bootstrap.js"></script>
     <!--  WOW Script -->
    <script src="${baseURL}/js/wow.min.js"></script>
    <!--  Scrolling Script -->
    <script src="${baseURL}/js/jquery.easing.min.js"></script>
    <!--  PrettyPhoto Script -->
    <script src="${baseURL}/js/jquery.prettyPhoto.js"></script>
    <!--  Custom Scripts -->
    <script src="${baseURL}/js/custom.js"></script>
   
</body>
</html>
