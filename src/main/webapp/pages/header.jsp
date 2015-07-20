<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
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
    <!--  Date picker Style -->
    <link href="${baseURL}/css/jquery.ui.core.css" rel="stylesheet" />    
    <link href="${baseURL}/css/jquery.ui.datepicker.css" rel="stylesheet" />
    <link href="${baseURL}/css/jquery.ui.theme.css" rel="stylesheet" />
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
            <h2>Welcome</h1>
                <ul class="nav navbar-nav navbar-right">
                    <li >
                        <a href="${baseURL}/pages/company_list.jsp">Company List 
                        </a>
                    </li>
                    <li >
                        <a href="${baseURL}/pages/expensetype_list.jsp">Expense Type List
                        </a>
                    </li>
                    <li >
                        <a href="#IT">Information Technology
                        </a>
                    </li>
                    <li >
                        <a href="#maintenance">Maintenance & Testing
                        </a>
                    </li>
                    <li >
                        <a href="#finance">Operations 
                        </a>
                    </li>
                    <li >
                        <a href="#finance">Finance 
                        </a>
                    </li>
                    <li >
                        <a href="#sign">Signout 
                        </a>
                    </li>
                </ul>
 				
            </div>

        </div>
    </div>
    <!--./ NAV BAR END -->
    