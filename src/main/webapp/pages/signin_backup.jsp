<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
                        <a href="#hr">Human Relations 
                        </a>
                    </li>
                    <li >
                        <a href="#accounts">Accounts
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
       <!--./ HOME SECTION END -->
        <!--./ ABOUT SECTION END -->
      <!--./ DONARS TESTIMONIALS END -->
    <div id="IT" class="pad-top-botm" >
        <div class="container">
            <div class="row text-center ">
                <div class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2">
                    <h2 data-wow-delay="0.3s" class="wow rollIn animated"><strong>EXPENSE MANAGEMENT</strong></h2>
                    <p class="sub-head"> </p>
                    
                </div>
            </div>
            <div class="row ">
  		<div class="col-lg-4 col-md-4 col-sm-4 ">

                    <div class="portfolio-item wow rotateIn animated" data-wow-delay="0.6s">


                        <img src="${baseURL}/img/portfolio/hr.jpeg" class="img-responsive " alt="" />
                        <div class="overlay">
                          <p>
                                
                               
                                HUMAN RELATIONS
                            </p>
                            <a class="preview " title="HUMAN RELATIONS" href="img/portfolio/hr.jpeg"><i class="fa fa-search-plus fa-5x"></i></a>

                        </div>
                    </div>
                </div>
  		
                <div class="col-lg-4 col-md-4 col-sm-4 ">

                    <div class="portfolio-item wow rotateIn animated" data-wow-delay="0.4s">


                        <img src="${baseURL}/img/portfolio/fin.jpeg" class="img-responsive " alt="" />
                        <div class="overlay">
                            <p>
                               
                               FINANCE
                            </p>
                            <a class="preview  " title="Image Title Here" href="img/portfolio/fin.jpeg"><i class="fa fa-search-plus fa-5x"></i></a>

                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-4 ">

                    <div class="portfolio-item wow rotateIn animated" data-wow-delay="0.5s">


                        <img src="${baseURL}/img/portfolio/it.jpeg" class="img-responsive " alt="" />
                        <div class="overlay">
                            <p>
                                
                                INFORMATION TECHNOLOGY
                            </p>
                            <a class="preview " title="Image Title Here" href="img/portfolio/it.jpeg"><i class="fa fa-search-plus fa-5x"></i></a>

                        </div>
                    </div>
                </div>
                
            </div>

            <div class="row " style="padding-top: 50px;">
                <div class="col-lg-4 col-md-4 col-sm-4 ">

                    <div class="portfolio-item wow rotateIn animated" data-wow-delay="0.7s">


                        <img src="${baseURL}/img/portfolio/maintenance.jpeg" class="img-responsive " alt="" />
                        <div class="overlay">
                           <p>
                                
                                MAINTENANCE
                            </p>
                            <a class="preview  " title="Image Title Here" href="img/portfolio/maintenance.jpeg"><i class="fa fa-search-plus fa-5x"></i></a>

                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-4 ">

                    <div class="portfolio-item wow rotateIn animated" data-wow-delay="0.8s">


                        <img src="${baseURL}/img/portfolio/acc.jpg" class="img-responsive " alt="" />
                        <div class="overlay">
                            <p>
                                ACCOUNTS
                            </p>
                            <a class="preview " title="Image Title Here" href="img/portfolio/acc.jpg"><i class="fa fa-search-plus fa-5x"></i></a>

                        </div>
                    </div>
                </div>
                
                                <div class="col-lg-4 col-md-4 col-sm-4 ">

                    <div class="portfolio-item wow rotateIn animated" data-wow-delay="0.9s">


                        <img src="${baseURL}/img/portfolio/operations.jpeg" class="img-responsive " alt="" />
                        <div class="overlay">
                          <p>
                            OPERATIONS
                            </p>
                            <a class="preview " title="Image Title Here" href="img/portfolio/operations.jpeg"><i class="fa fa-search-plus fa-5x"></i></a>

                        </div>
                    </div>
                </div>

            </div>

        </div>
    </div>
    <!--./ IT/PORTFOLIO SECTION END -->
     <!--./ finance SECTION END -->
    <div id="footser-end">
        <div class="container">
            <div class="row">
            </div>

        </div>
    </div>
    <!--./ FOOTER SECTION END -->
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