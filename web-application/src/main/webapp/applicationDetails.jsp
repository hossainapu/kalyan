<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Enrollment Application</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport"  content="width=device-width, initial-scale=1">

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="${contextPath}/resources/js/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>


    <script>
        $(document).ready(function() {
            $("#idGoBack").click(function (ev) {
                ev.preventDefault();
                window.history.back();
            });
        });
    </script>

    <title>এপ্লিকেশনের ডিটেলস</title>

</head>
<body class="bg-primary">
<c:import url="menu.jsp"/>


<div class="container container-fluid">
    <div id="idMessage">
        <h3><c:out escapeXml="false" value="${error}"/></h3>
    </div>
    <table class="table table-striped">
        <tbody>
        <tr>
            <td class="col-sm-6">
                <h4>অ্যাপ্লিকেশন এনরোলমেন</h4>
                <fieldset>
                    <div class="form-group">
                        <label class="col-md-4 control-label">নাম</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input id="nameEn" readonly="readonly" value="${application.nameEn}" placeholder="নাম" class="form-control" required="true" type="text" maxlength="100">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">বাবার নাম</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input id="fatherName"  readonly="readonly" value="${application.fatherEn}" placeholder="বাবার নাম" class="form-control" required="true" type="text" maxlength="100">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">জেন্ডার</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" class="form-control" readonly="readonly" value="${application.gender}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">জন্ম তারিখ</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" readonly="readonly" value="${application.dateOfBirth}" class="form-control" id="dateOfBirth" placeholder="dd/mm/yyyy" title="জন্ম তারিখ" readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">বৈবাহিক অবস্হা</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" class="form-control" readonly="readonly" value="${application.maritalStatus}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">রক্তের গ্রুপ</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" class="form-control" readonly="readonly" value="${application.bloodGroup}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">স্বামী/স্ত্রী নাম</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" readonly="readonly" value="${application.spouseEn}" class="form-control"  id="spouseEn" placeholder="স্বামী/স্ত্রী নাম" title="স্বামী/স্ত্রী নাম" maxlength="100">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">এন এই ডি নম্বর</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" readonly="readonly" value="${application.nid}" class="form-control"  id="nid" placeholder="এন এই ডি নম্বর" title="এন এই ডি নম্বর" maxlength="20">

                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">মোবাইল</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" class="form-control"  readonly="readonly" value="${application.contactNumber}" id="contactNumber" placeholder="যোগাযোগের নম্বর" title="যোগাযোগের নম্বর" maxlength="20">

                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">ইমার্জেন্সি নাম</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" class="form-control"  readonly="readonly" value="${application.emergencyName}" id="emergencyName" placeholder="ইমার্জেন্সি নাম" title="ইমার্জেন্সি নাম" maxlength="20">

                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">ইমার্জেন্সি নম্বর</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" class="form-control"  readonly="readonly" value="${application.emergencyNumber}" id="emergencyNumber" placeholder="০১*********" title="ইমার্জেন্সি নম্বর" maxlength="11">

                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">ইমার্জেন্সি সম্পর্ক</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" class="form-control"  readonly="readonly" value="${application.emergencyRelation}" id="emergencyRelation" placeholder="ইমার্জেন্সি সম্পর্ক" title="Emergency Relation" maxlength="20">

                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-md-4 control-label">এপ্লিকেশন টাইপ</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" class="form-control" readonly="readonly" value="${application.applicationType}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">কিস্তি পরিশোধ</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" class="form-control" readonly="readonly" value="${application.installmentPaid}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">অফিস কোড</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" class="form-control" readonly="readonly" value="${application.authorityCode}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">সার্কেল নম্বর</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" class="form-control" readonly="readonly" value="${application.circleCode}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">ব্যাংক ট্রানসাকশান নম্বর<i class="text-danger">*</i></label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" class="form-control"  readonly="readonly" value="${application.bankTransactionNumber}" id="bankTransactionNumber" placeholder="ব্যাংক ট্রানসাকশান নম্বর" title="ব্যাংক ট্রানসাকশান নম্বর" maxlength="20">

                        </div>
                    </div>

                </fieldset>
            </td>
            <td class="col-sm-6">
                <h4>ঠিকানা</h4>
                <fieldset>
                    <div class="form-group">
                        <label class="col-md-4 control-label">বর্তমান বিভাগের নাম</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" class="form-control" readonly="readonly" value="${application.permanentDivision}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">বর্তমান জেলার নাম</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" class="form-control" readonly="readonly" value="${application.presentDistrict}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">বর্তমান থানার নাম</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" class="form-control" readonly="readonly" value="${application.presentUpozila}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">বর্তমান ঠিকানা লাইনে</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" class="form-control"  readonly="readonly" value="${application.presentAddressLine}" id="presentAddressLine" placeholder="বর্তমান ঠিকানা লাইনে" title="বর্তমান ঠিকানা লাইনে" maxlength="100">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">স্থায়ী বিভাগের নাম</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" class="form-control" readonly="readonly" value="${application.permanentDivision}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">স্থায়ী জেলার নাম</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" class="form-control" readonly="readonly" value="${application.permanentDistrict}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">স্থায়ী থানার নাম</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" class="form-control" readonly="readonly" value="${application.permanentUpozila}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">স্থায়ী ঠিকানা লাইনে</label>
                        <div class="col-md-8 inputGroupContainer">
                            <input type="text" class="form-control"  readonly="readonly" value="${application.permanentAddressLine}" id="permanentAddressLine" placeholder="স্থায়ী ঠিকানা লাইনে" title="স্থায়ী ঠিকানা লাইনে" maxlength="100">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">ছবি</label>
                        <div class="col-md-8 inputGroupContainer">
                            <c:choose>
                                <c:when test="${application.photoStr != null}">
                                    <img src="data:image/jpeg;base64,${application.photoStr}" class="avatar img-rounded img-thumbnail" alt="avatar">
                                </c:when>
                                <c:otherwise>
                                    <img src="${contextPath}/resources/images/face.PNG" class="avatar img-rounded img-thumbnail" alt="avatar">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">স্বাক্ষর</label>
                        <div class="col-md-8 inputGroupContainer">
                            <c:choose>
                                <c:when test="${application.signatureStr != null}">
                                    <img src="data:image/jpeg;base64,${application.signatureStr}" class="signature img-rounded img-thumbnail" alt="signature">
                                </c:when>
                                <c:otherwise>
                                    <img src="${contextPath}/resources/images/signature.png" class="signature img-rounded img-thumbnail" alt="signature">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </fieldset>
                <button type="button" class="btn btn-primary" id="idGoBack">পিছনে</button>
            </td>

        </tr>
        </tbody>

    </table>

</div>
</body>
</html>