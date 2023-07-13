<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>



<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="${contextPath}/resources/js/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <title>ইউজার আপডেট করুন</title>
</head>
<body class="bg-primary">
<c:import url="menu.jsp"/>

<div class="container container-fluid">
    <table class="table table-striped">
        <h4 class="alert-message"><c:out value="${message}" escapeXml="false"></c:out></h4>
        <tbody>
        <form class="well form-horizontal" action="${contextPath}/user/reset-password" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${user.id}">
            <tr>
                <td class="col-sm-3"></td>
                <td class="col-sm-6">
                    <h4>ইউজার আপডেট করুন</h4>
                    <fieldset>
                        <div class="form-group">
                            <label class="col-md-4 control-label">নাম<i class="text-danger">*</i></label>
                            <div class="col-md-8 inputGroupContainer">
                                <input id="name" name="name" readonly="readonly" value="${user.name}" placeholder="নাম" class="form-control" required="true" type="text" maxlength="100">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">কন্ট্যাক্ট নম্বর<i class="text-danger">*</i></label>
                            <div class="col-md-8 inputGroupContainer">
                                <input id="contactNumber" readonly="readonly" name="contactNumber" value="${user.contactNumber}" placeholder="কন্ট্যাক্ট নম্বর" class="form-control" required="true" type="text" maxlength="100">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">ইমেইল এড্রেস</label>
                            <div class="col-md-8 inputGroupContainer">
                                <input type="text" readonly="readonly" value="${user.email}" class="form-control" name="email" id="email" placeholder="ইমেইল এড্রেস" title="ইমেইল এড্রেস" maxlength="100">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">ইউজার নাম<i class="text-danger">*</i></label>
                            <div class="col-md-8 inputGroupContainer">
                                <input type="text" readonly="readonly" value="${user.username}" class="form-control" name="username" id="username" placeholder="ইউজার নাম" title="ইউজার নাম" maxlength="50">

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">পাসওয়ার্ড<i class="text-danger">*</i></label>
                            <div class="col-md-8 inputGroupContainer">
                                <input type="password" class="form-control" name="password" value="${user.password}" id="password" placeholder="পাসওয়ার্ড" title="পাসওয়ার্ড" maxlength="20">

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">কন্ফার্ম পাসওয়ার্ড<i class="text-danger">*</i></label>
                            <div class="col-md-8 inputGroupContainer">
                                <input type="password" class="form-control" name="passwordConfirm" value="${user.passwordConfirm}" id="passwordConfirm" placeholder="কন্ফার্ম পাসওয়ার্ড" title="কন্ফার্ম পাসওয়ার্ড" maxlength="20">

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">রোল<i class="text-danger">*</i></label>
                            <div class="col-md-8 inputGroupContainer">
                                <select class="selectpicker form-control" name="roleName" id="roleName">
                                    <option value="">--পছন্দ করুন--</option>
                                    <c:forEach items="${roles}" var="role">
                                        <option value="${role}" <c:out value="${user.roleName == role ? 'selected=\"selected\"' : ''}"/>>${role}</option>
                                    </c:forEach>
                                </select>

                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-4 inputGroupContainer">
                                <input type="submit" class="form-control btn btn-primary" value="আপডেট">
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

