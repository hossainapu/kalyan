<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>List of User</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="${contextPath}/resources/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/custom-datatable.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/dataTables.bootstrap.css">
    <script src="${contextPath}/resources/js/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap-datetimepicker.js"></script>
    <script src="${contextPath}/resources/js/jquery.dataTables.min.js"></script>

    <script>
        $(document).ready(function() {
            $('#userTable').dataTable();
        });
    </script>
</head>
<body class="bg-primary">
<c:import url="menu.jsp"/>

<div class="container container-fluid" style="background-color: white">
    <form role="form" method="post" action="${contextPath}/user/search-user">
        <div class="row">
            <div class="col-sm-4">
                <div class="form-group">
                    <label class="col-md-4 control-label">ইউজার নাম</label>
                    <div class="col-md-8 inputGroupContainer">
                        <input id="userName" name="userName" placeholder="ইউজার নাম" class="form-control" type="text" maxlength="100">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">কন্টাক্ট নম্বর</label>
                    <div class="col-md-8 inputGroupContainer">
                        <input id="contactNumber" name="contactNumber" placeholder="কন্টাক্ট নম্বর" class="form-control" type="text" maxlength="100">
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="form-group">
                    <label class="col-md-4 control-label">ইমেইল এড্রেস</label>
                    <div class="col-md-8 inputGroupContainer">
                        <input id="email" name="email" placeholder="ইমেইল এড্রেস" class="form-control" type="text" maxlength="100">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">রোল নাম</label>
                    <div class="col-md-8 inputGroupContainer">
                        <input id="roleName" name="roleName" placeholder="রোল নাম" class="form-control" type="text" maxlength="100">
                    </div>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="form-group">
                    <label class="col-md-4 control-label">নাম</label>
                    <div class="col-md-8 inputGroupContainer">
                        <input id="name" name="name" placeholder="নাম" class="form-control" type="text" maxlength="100">
                    </div>
                </div>
                <div class="form-group">
                    <input type="submit" value="সার্চ" class="btn btn-primary">
                </div>
            </div>

        </div>
    </form>
</div>

<div class="container container-fluid" style="background-color: white">
    <table class="table table-responsive table-striped" id="userTable">
        <thead>
        <tr>
            <th>সিরিয়াল</th>
            <th>নাম</th>
            <th>ইউজার নাম</th>
            <th>কন্টাক্ট নম্বর</th>
            <th>ইমেইল এড্রেস</th>
            <th>রোল নাম</th>
            <th>অ্যাকশন</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user" varStatus="loop">
            <tr>
                <td>${loop.index +1}</td>
                <td>${user.name}</td>
                <td>${user.username}</td>
                <td>${user.contactNumber}</td>
                <td>${user.email}</td>
                <td>${user.roleName}</td>
                <td>
                    <p data-placement="top" data-toggle="tooltip" title="View">
                        <a class="btn btn-primary btn-xs" href="${contextPath}/user/update-user/${user.id}" ><span class="glyphicon glyphicon-eye-open"></span></a>
                        <a class="btn btn-primary btn-xs" href="${contextPath}/user/reset-password/${user.id}" ><span class="glyphicon glyphicon-edit"></span></a>
                    </p>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>
</html>