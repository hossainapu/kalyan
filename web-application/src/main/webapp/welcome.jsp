<%@ page session="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/welcome.css">
        <script src="resources/js/jquery.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
    </head>
<body>
    <c:import url="menu.jsp"/>
    <div class="dashboard">
        <div class="row">
            <div class="tab-content col-md-12">
                <div role="tabpanel" class="tab-pane profile-pane active" id="profile">
                    <div>

                        <div>
                            <div class="header">
                                <h4>Enrollment Help Information</h4>
                            </div>
                            <div class="content">
                                <div class="row">
                                    <h3 class="col-sm-10 col-sm-offset-1 title">Information</h3>
                                    <article class="col-sm-10 col-sm-offset-1">
                                        <p>All start (*) marked fields are required!</p>
                                        <p>Please fill with correct information.</p>
                                    </article>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

