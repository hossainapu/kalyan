<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Send SMS</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="${contextPath}/resources/js/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</head>


<body class="bg-primary">
<c:import url="menu.jsp"/>

<div class="container container-fluid">
    <table class="table table-striped">
        <h4 class="alert-message"><c:out value="${message}" escapeXml="false"></c:out></h4>
        <tbody>
        <form class="well form-horizontal" action="${contextPath}/admin/sendSMS" method="post" id="sendSMS" enctype="multipart/form-data">
            <tr>
                <td class="col-sm-3"></td>
                <td class="col-sm-6">
                    <h4>Send Message</h4>
                    <fieldset>
                        <div class="form-group">
                            <label class="col-md-4 control-label">SMS Text<i class="text-danger">*</i></label>
                            <div class="col-md-8 inputGroupContainer">
                                <textarea id="smsText" name="smsText" placeholder="Text" class="form-control" required="true" type="text" maxlength="1000"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">Contact Number(,) separated<i class="text-danger">*</i></label>
                            <div class="col-md-8 inputGroupContainer">
                                <textarea id="contactNumber" name="contactNumber" placeholder="কন্ট্যাক্ট নম্বর" class="form-control" required="true" type="text" maxlength="1100"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-4 inputGroupContainer">
                                <input type="submit" class="form-control btn btn-primary" value="Send SMS">
                            </div>
                            <div class="col-sm-4">
                                <input type="reset" class="form-control btn btn-danger" value="Reset">
                            </div>
                        </div>

                    </fieldset>
                </td>
                <td class="col-sm-3"></td>
            </tr>
        </form>
        </tbody>

    </table>

</div>
</body>
</html>