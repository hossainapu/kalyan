<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Enrollment Application</title>
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
            $('#enrolledApplicationTable').dataTable();
        });
    </script>
    <title>আপলোড করা এপ্লিকেশন</title>
</head>
<body class="bg-primary">
<c:import url="menu.jsp"/>

<div class="container container-fluid" style="background-color: white">
    <h4 class="alert-message"><c:out value="${message}" escapeXml="false"></c:out></h4>
    <form role="form" method="post" action="${contextPath}/admin/search-uploaded-application">
        <div class="row">
            <div class="col-sm-4">
                <div class="form-group">
                    <label class="col-md-4 control-label">নাম</label>
                    <div class="col-md-8 inputGroupContainer">
                        <input id="name" name="name" placeholder="নাম" class="form-control" type="text" maxlength="100">
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
                    <label class="col-md-4 control-label">অথরিটি কোড</label>
                    <div class="col-md-8 inputGroupContainer">
                        <input id="authorityCode" name="authorityCode" placeholder="অথরিটি কোড" class="form-control" type="text" maxlength="100">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">বাবার নাম</label>
                    <div class="col-md-8 inputGroupContainer">
                        <input id="fatherName" name="fatherName" placeholder="বাবার নাম" class="form-control" type="text" maxlength="100">
                    </div>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="form-group">
                    <label class="col-md-4 control-label">ইনস্টলমেন্ট পেইড</label>
                    <div class="col-md-8 inputGroupContainer">
                        <input id="installmentPaid" name="installmentPaid" placeholder="ইনস্টলমেন্ট পেইড" class="form-control" type="text" maxlength="100">
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
    <table class="table table-responsive table-striped" id="enrolledApplicationTable">
        <thead>
        <tr>
            <th>সিরিয়াল</th>
            <th>নাম</th>
            <th>এপ্লিকেশন টাইপ</th>
            <th>কন্টাক্ট নম্বর</th>
            <th>ইনস্টলমেন্ট পেইড</th>
            <th>জরুরী যোগাযোগ</th>
            <th>অথরিটি</th>
            <th>অ্যাকশন</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${applications}" var="app" varStatus="loop">
            <tr>
                <td>${loop.index +1}</td>
                <td>${app.name}</td>
                <td>${app.applicationType}</td>
                <td>${app.contactNumber}</td>
                <td>${app.installmentPaid}</td>
                <td>${app.emergencyContact}</td>
                <td>${app.authority}</td>
                <td>
                    <p data-placement="top" data-toggle="tooltip" title="View">
                        <a class="btn btn-primary btn-xs" href="${contextPath}/admin/viewUploadedApplication/${app.id}" ><span class="glyphicon glyphicon-eye-open"></span></a>
                        <a class="btn btn-primary btn-xs" href="${contextPath}/admin/update-application/${app.id}" ><span class="glyphicon glyphicon-edit"></span></a>
                    </p>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>
</html>