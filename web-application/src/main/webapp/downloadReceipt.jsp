<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
    <head>
        <title>Enrollment Application</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link href="resources/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="resources/css/bootstrap-datetimepicker.min.css">
        <script src="resources/js/jquery.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script src="resources/js/bootstrap-datetimepicker.js"></script>
        <script>
            $(document).ready(function() {
                $(".date_of_birth").datetimepicker({
                    pickTime: false,
                    minView: 2,
                    format: 'dd/mm/yyyy',
                    autoclose: true
                });
            });
        </script>
    </head>


    <body class="bg-primary">
    <c:import url="menu.jsp"/>

    <div class="container container-fluid">
        <table class="table table-striped">
            <h4 class="alert-message"><c:out value="${message}" escapeXml="false"></c:out></h4>
            <tbody>
            <form class="well form-horizontal" action="${contextPath}/downloadReceipt" method="post" id="downloadReceipt" enctype="multipart/form-data">
                <tr>
                    <td class="col-sm-3"></td>
                    <td class="col-sm-6">
                        <h4>ডাউনলোড রিসিট</h4>
                        <fieldset>
                            <div class="form-group">
                                <label class="col-md-4 control-label">জন্ম তারিখ<i class="text-danger">*</i></label>
                                <div class="col-md-8 inputGroupContainer">
                                    <input id="dateOfBirth" name="dateOfBirth" placeholder="dd/mm/yyyy" class="date_of_birth form-control" required="true" type="text" maxlength="100">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label">কন্ট্যাক্ট নম্বর<i class="text-danger">*</i></label>
                                <div class="col-md-8 inputGroupContainer">
                                    <input id="contactNumber" name="contactNumber" placeholder="কন্ট্যাক্ট নম্বর" class="form-control" required="true" type="text" maxlength="20">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-4 inputGroupContainer">
                                    <input type="submit" class="form-control btn btn-primary" value="ডাউনলোড">
                                </div>
                                <div class="col-sm-4">
                                    <input type="reset" class="form-control btn btn-danger" value="রিসেট">
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