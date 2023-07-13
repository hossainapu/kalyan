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
        <script src="${contextPath}/resources/js/jquery.min.js"></script>
        <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
        <script src="${contextPath}/resources/js/bootstrap-datetimepicker.js"></script>

        <script>
            $(document).ready(function() {
                var readURL = function(input) {
                    if (input.files && input.files[0]) {
                        var reader = new FileReader();

                        reader.onload = function (e) {
                            $('.avatar').attr('src', e.target.result);
                        }

                        reader.readAsDataURL(input.files[0]);
                    }
                }

                var readSignature = function(input) {
                    if (input.files && input.files[0]) {
                        var reader = new FileReader();

                        reader.onload = function (e) {
                            $('.signature').attr('src', e.target.result);
                        }

                        reader.readAsDataURL(input.files[0]);
                    }
                }


                $(".file-upload").on('change', function(){
                    console.log("SIZE:"+this.files[0].size);
                    if(this.files[0].size/1024 >100) {
                        $("#idImageError").modal('show');
                    } else {
                        readURL(this);
                    }
                });

                $(".signature-upload").on('change',function () {
                    console.log("SIZE:"+this.files[0].size);
                    if(this.files[0].size/1024 >100) {
                        $("#idImageError").modal('show');
                    } else {
                        readSignature(this);
                    }
                });


                $("#presentDivision").change(function(){
                    var categoryId = $(this).val();
                    $.ajax({
                        type: 'GET',
                        url: "/district/" + categoryId,
                        success: function(data){
                            var slctSubcat=$('#presentDistrict'), option="<option value=''>--পছন্দ করুন--</option>";
                            slctSubcat.empty();
                            for(var i=0; i<data.length; i++){
                                option = option + "<option value='"+data[i].name + "'>"+data[i].name + "</option>";
                            }
                            slctSubcat.append(option);
                        },
                        error:function(){
                            alert("error");
                        }

                    });
                });

                $("#presentDistrict").change(function(){
                    var categoryId = $(this).val();
                    $.ajax({
                        type: 'GET',
                        url: "/thana/" + categoryId,
                        success: function(data){
                            var slctSubcat=$('#presentUpozila'), option="<option value=''>--পছন্দ করুন--</option>";
                            slctSubcat.empty();
                            for(var i=0; i<data.length; i++){
                                option = option + "<option value='"+data[i].name + "'>"+data[i].name + "</option>";
                            }
                            slctSubcat.append(option);
                        },
                        error:function(){
                            alert("error");
                        }

                    });
                });


                $("#permanentDivision").change(function(){
                    var categoryId = $(this).val();
                    $.ajax({
                        type: 'GET',
                        url: "/district/" + categoryId,
                        success: function(data){
                            var slctSubcat=$('#permanentDistrict'), option="<option value=''>--পছন্দ করুন--</option>";
                            slctSubcat.empty();
                            for(var i=0; i<data.length; i++){
                                option = option + "<option value='"+data[i].name + "'>"+data[i].name + "</option>";
                            }
                            slctSubcat.append(option);
                        },
                        error:function(){
                            alert("error");
                        }

                    });
                });

                $("#permanentDistrict").change(function(){
                    var categoryId = $(this).val();
                    $.ajax({
                        type: 'GET',
                        url: "/thana/" + categoryId,
                        success: function(data){
                            var slctSubcat=$('#permanentUpozila'), option="<option value=''>--পছন্দ করুন--</option>";
                            slctSubcat.empty();
                            for(var i=0; i<data.length; i++){
                                option = option + "<option value='"+data[i].name + "'>"+data[i].name + "</option>";
                            }
                            slctSubcat.append(option);
                        },
                        error:function(){
                            alert("error");
                        }

                    });
                });

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


        <div class="modal fade" id="idImageError" role="dialog">
            <div class="modal-dialog">

                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title text-danger"><i class="glyphicon glyphicon-remove-sign"></i> Error in Selected image file!</h4>
                    </div>
                    <div class="modal-body">
                        <p>Only maximum 100KB JPEG files are accepted!</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>

        <div class="container container-fluid">
            <div id="idMessage">
                <h3><c:out escapeXml="false" value="${error}"/></h3>
            </div>
            <table class="table table-striped">
                <tbody>
                    <form class="well form-horizontal" action="${contextPath}/registration" method="post" id="applicationForm" enctype="multipart/form-data">
                    <tr>
                        <td class="col-sm-6">
                            <h4>অ্যাপ্লিকেশন এনরোলমেন</h4>
                            <fieldset>
                                <div class="form-group">
                                    <label class="col-md-4 control-label">নাম&nbsp;<i class="text-danger">*</i></label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <input id="nameEn" name="nameEn" value="${application.nameEn}" placeholder="নাম" class="form-control" required="true" type="text" maxlength="100">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4 control-label">বাবার নাম&nbsp;<i class="text-danger">*</i></label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <input id="fatherName" name="fatherEn" value="${application.fatherEn}" placeholder="বাবার নাম" class="form-control" required="true" type="text" maxlength="100">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4 control-label">জেন্ডার&nbsp;<i class="text-danger">*</i></label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <select class="selectpicker form-control" name="gender" id="gender">
                                            <option value="">--পছন্দ করুন--</option>
                                            <option value="পুরুষ" <c:out value="${application.gender =='পুরুষ' ? 'selected=\"selected\"' : ''}"/> >পুরুষ</option>
                                            <option value="মহিলা" <c:out value="${application.gender =='মহিলা' ? 'selected=\"selected\"' : ''}"/>>মহিলা</option>
                                            <option value="তৃতীয়" <c:out value="${application.gender =='তৃতীয়' ? 'selected=\"selected\"' : ''}"/> >তৃতীয়</option>
                                        </select>

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4 control-label">জন্ম তারিখ&nbsp;<i class="text-danger">*</i></label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <input type="text" value="${application.dateOfBirth}" class="form-control date_of_birth" name="dateOfBirth" id="dateOfBirth" placeholder="dd/mm/yyyy" title="জন্ম তারিখ" readonly="readonly">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4 control-label">বৈবাহিক অবস্হা</label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <select class="selectpicker form-control" name="maritalStatus" id="maritalStatus">
                                            <option value="">--পছন্দ করুন--</option>
                                            <option value="অবিবাহিত" <c:out value="${application.maritalStatus =='অবিবাহিত' ? 'selected=\"selected\"' : ''}"/>>অবিবাহিত</option>
                                            <option value="বিবাহিত" <c:out value="${application.maritalStatus =='বিবাহিত' ? 'selected=\"selected\"' : ''}"/>>বিবাহিত</option>
                                            <option value="তালাকপ্রাপ্ত" <c:out value="${application.maritalStatus =='তালাকপ্রাপ্ত' ? 'selected=\"selected\"' : ''}"/>>তালাকপ্রাপ্ত</option>
                                            <option value="বিধবা" <c:out value="${application.maritalStatus =='বিধবা' ? 'selected=\"selected\"' : ''}"/>>বিধবা</option>
                                            <option value="অনন্যা" <c:out value="${application.maritalStatus =='অনন্যা' ? 'selected=\"selected\"' : ''}"/>>অনন্যা</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4 control-label">রক্তের গ্রুপ</label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <select class="selectpicker form-control" name="bloodGroup" id="bloodGroup">
                                            <option value="">--পছন্দ করুন--</option>
                                            <option value="এ+" <c:out value="${application.bloodGroup =='এ+' ? 'selected=\"selected\"' : ''}"/>>এ+</option>
                                            <option value="এ-" <c:out value="${application.bloodGroup =='এ-' ? 'selected=\"selected\"' : ''}"/>>এ-</option>
                                            <option value="বি+" <c:out value="${application.bloodGroup =='বি+' ? 'selected=\"selected\"' : ''}"/>>বি+</option>
                                            <option value="বি-" <c:out value="${application.bloodGroup =='বি-' ? 'selected=\"selected\"' : ''}"/>>বি-</option>
                                            <option value="এবি+" <c:out value="${application.bloodGroup =='এবি+' ? 'selected=\"selected\"' : ''}"/>>এবি+</option>
                                            <option value="এবি-" <c:out value="${application.bloodGroup =='এবি-' ? 'selected=\"selected\"' : ''}"/>>এবি-</option>
                                            <option value="ও+" <c:out value="${application.bloodGroup =='ও+' ? 'selected=\"selected\"' : ''}"/>>ও+</option>
                                            <option value="ও-" <c:out value="${application.bloodGroup =='ও-' ? 'selected=\"selected\"' : ''}"/>>ও-</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4 control-label">স্বামী/স্ত্রী নাম</label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <input type="text" value="${application.spouseEn}" class="form-control" name="spouseEn" id="spouseEn" placeholder="স্বামী/স্ত্রী নাম" title="স্বামী/স্ত্রী নাম" maxlength="100">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-4 control-label">এন এই ডি নম্বর<i class="text-danger">*</i></label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <input type="text" value="${application.nid}" class="form-control" name="nid" id="nid" placeholder="এন এই ডি নম্বর" title="এন এই ডি নম্বর" maxlength="20">

                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-4 control-label">মোবাইল&nbsp;<i class="text-danger">*</i></label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <input type="text" class="form-control" name="contactNumber" value="${application.contactNumber}" id="contactNumber" placeholder="যোগাযোগের নম্বর" title="যোগাযোগের নম্বর" maxlength="20">

                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-4 control-label">ইমার্জেন্সি নাম<i class="text-danger">*</i></label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <input type="text" class="form-control" name="emergencyName" value="${application.emergencyName}" id="emergencyName" placeholder="ইমার্জেন্সি নাম" title="ইমার্জেন্সি নাম" maxlength="20">

                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-4 control-label">ইমার্জেন্সি নম্বর<i class="text-danger">*</i></label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <input type="text" class="form-control" name="emergencyNumber" value="${application.emergencyNumber}" id="emergencyNumber" placeholder="০১*********" title="ইমার্জেন্সি নম্বর" maxlength="11">

                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-4 control-label">ইমার্জেন্সি সম্পর্ক</label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <input type="text" class="form-control" name="emergencyRelation" value="${application.emergencyRelation}" id="emergencyRelation" placeholder="ইমার্জেন্সি সম্পর্ক" title="Emergency Relation" maxlength="20">

                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="col-md-4 control-label">এপ্লিকেশন টাইপ&nbsp;<i class="text-danger">*</i></label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <select class="selectpicker form-control" name="applicationType" id="applicationType">
                                            <option value="">--পছন্দ করুন--</option>
                                            <option value="6_S_A" <c:out value="${application.applicationType =='6 SEATER AUTO' ? 'selected=\"selected\"' : ''}"/>>6 SEATER AUTO</option>
                                            <option value="3_S_A" <c:out value="${application.applicationType =='3 SEATER AUTO' ? 'selected=\"selected\"' : ''}"/>>3 SEATER AUTO</option>
                                            <option value="M_R_V" <c:out value="${application.applicationType =='MANUAL RICKSHAW & VAN' ? 'selected=\"selected\"' : ''}"/>>MANUAL RICKSHAW & VAN</option>
                                            <option value="B_R_V" <c:out value="${application.applicationType =='BATTERY RICKSHAW & VAN' ? 'selected=\"selected\"' : ''}"/>>BATTERY RICKSHAW & VAN</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-4 control-label">কিস্তি পরিশোধ &nbsp;<i class="text-danger">*</i></label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <select class="selectpicker form-control" name="installmentPaid" id="installmentPaid">
                                            <option value="">--পছন্দ করুন--</option>
                                            <option value="0" <c:out value="${application.installmentPaid ==1 ? 'selected=\"selected\"' : ''}"/>>০</option>
                                            <option value="1" <c:out value="${application.installmentPaid ==1 ? 'selected=\"selected\"' : ''}"/>>১</option>
                                            <option value="2" <c:out value="${application.installmentPaid ==2 ? 'selected=\"selected\"' : ''}"/>>২</option>
                                            <option value="3" <c:out value="${application.installmentPaid ==3 ? 'selected=\"selected\"' : ''}"/>>৩</option>
                                            <option value="4" <c:out value="${application.installmentPaid ==4 ? 'selected=\"selected\"' : ''}"/>>৪</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-4 control-label">অফিস কোড&nbsp;<i class="text-danger">*</i></label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <select class="selectpicker form-control" name="authorityCode" id="authorityCode">
                                            <option value="">--পছন্দ করুন--</option>
                                            <option value="GCC" <c:out value="${application.authorityCode =='GCC' ? 'selected=\"selected\"' : ''}"/>>গাজীপুর সিটি কর্পোরেশন</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-4 control-label">সার্কেল নম্বর&nbsp;<i class="text-danger">*</i></label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <select class="form-control" name="circleCode" id="circleCode">
                                            <option value="">--পছন্দ করুন--</option>
                                            <c:forEach var = "i" begin = "1" end = "10">
                                                <c:choose>
                                                    <c:when test="${i <10}">
                                                        <option value="${i}" <c:out value="${application.circleCode ==i ? 'selected=\"selected\"' : ''}"/>>0${i}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${i}" <c:out value="${application.circleCode ==i ? 'selected=\"selected\"' : ''}"/>>${i}</option>
                                                    </c:otherwise>
                                                </c:choose>

                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-4 control-label">ব্যাংক ট্রানসাকশান নম্বর<i class="text-danger">*</i></label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <input type="text" class="form-control" name="bankTransactionNumber" value="${application.bankTransactionNumber}" id="bankTransactionNumber" placeholder="ব্যাংক ট্রানসাকশান নম্বর" title="ব্যাংক ট্রানসাকশান নম্বর" maxlength="20">

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
                                        <select class="form-control" name="presentDivision" id="presentDivision">
                                            <option value="">--পছন্দ করুন--</option>
                                            <c:forEach items="${divisions}" var="division" varStatus="loop">
                                                <option value="${division.name}" <c:out value="${application.permanentDivision ==division.name ? 'selected=\"selected\"' : ''}"/>>${division.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-4 control-label">বর্তমান জেলার নাম</label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <select class="form-control" name="presentDistrict" id="presentDistrict">
                                            <option value="">--পছন্দ করুন--</option>
                                            <c:if test="${presentDistricts != null && presentDistricts.size() >0}">
                                                <c:forEach items="${presentDistricts}" var="dis" varStatus="loop">
                                                    <option value="${dis.name}" <c:out value="${dis.name ==application.presentDistrict? 'selected=\"selected\"' : ''}"/>>${dis.name}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-4 control-label">বর্তমান থানার নাম</label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <select class="form-control" name="presentUpozila" id="presentUpozila">
                                            <option value="">--পছন্দ করুন--</option>
                                            <c:if test="${presentUpozilas != null && presentUpozilas.size() >0}">
                                                <c:forEach items="${presentUpozilas}" var="up" varStatus="loop">
                                                    <option value="${up.name}" <c:out value="${up.name ==application.presentUpozila? 'selected=\"selected\"' : ''}"/>>${up.name}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-4 control-label">বর্তমান ঠিকানা লাইনে&nbsp;<i class="text-danger">*</i></label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <input type="text" class="form-control" name="presentAddressLine" value="${application.presentAddressLine}" id="presentAddressLine" placeholder="বর্তমান ঠিকানা লাইনে" title="বর্তমান ঠিকানা লাইনে" maxlength="100">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-4 control-label">স্থায়ী বিভাগের নাম</label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <select class="form-control" id="permanentDivision" name="permanentDivision">
                                            <option value="">--পছন্দ করুন--</option>
                                            <c:forEach items="${divisions}" var="division" varStatus="loop">
                                                <option value="${division.name}" <c:out value="${application.permanentDivision ==division.name ? 'selected=\"selected\"' : ''}"/>>${division.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-4 control-label">স্থায়ী জেলার নাম</label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <select class="form-control" id="permanentDistrict" name="permanentDistrict">
                                            <option value="">--পছন্দ করুন--</option>
                                            <c:if test="${permanentDistricts != null && permanentDistricts.size() >0}">
                                                <c:forEach items="${permanentDistricts}" var="dis" varStatus="loop">
                                                    <option value="${dis.name}" <c:out value="${dis.name ==application.permanentDistrict? 'selected=\"selected\"' : ''}"/>>${dis.name}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-4 control-label">স্থায়ী থানার নাম</label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <select class="form-control" id="permanentUpozila" name="permanentUpozila">
                                            <option value="">--পছন্দ করুন--</option>
                                            <c:if test="${permanentUpozilas != null && permanentUpozilas.size() >0}">
                                                <c:forEach items="${permanentUpozilas}" var="up" varStatus="loop">
                                                    <option value="${up.name}" <c:out value="${up.name ==application.permanentUpozila? 'selected=\"selected\"' : ''}"/>>${up.name}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-4 control-label">স্থায়ী ঠিকানা লাইনে&nbsp;<i class="text-danger">*</i></label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <input type="text" class="form-control" name="permanentAddressLine" value="${application.permanentAddressLine}" id="permanentAddressLine" placeholder="স্থায়ী ঠিকানা লাইনে" title="স্থায়ী ঠিকানা লাইনে" maxlength="100">
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
                                                <img src="resources/images/face.PNG" class="avatar img-rounded img-thumbnail" alt="avatar">
                                            </c:otherwise>
                                        </c:choose>

                                        <h6>Upload a photo...</h6>
                                        <input type="file" name="photoFile" class="text-center center-block file-upload" accept="image/jpeg">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-4 control-label">স্বাক্ষর</label>
                                    <div class="col-md-8 inputGroupContainer">
                                        <div class="text-center">
                                            <c:choose>
                                                <c:when test="${application.signatureStr != null}">
                                                    <img src="data:image/jpeg;base64,${application.signatureStr}" class="signature img-rounded img-thumbnail" alt="signature">
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="resources/images/signature.png" class="signature img-rounded img-thumbnail" alt="signature">
                                                </c:otherwise>
                                            </c:choose>

                                            <h6>Upload Signature</h6>
                                            <input type="file" name="signatureFile" class="text-center center-block signature-upload" accept="image/jpeg">
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                            <input type="submit" class="btn btn-primary" value="সাবমিট">
                            <input type="reset" class="btn btn-danger" value="রিসেট">
                            <c:if test="${application.id != null}">
                                <a href="/application/${application.uuid}" class="btn btn-primary">ডাউনলোড</a>
                            </c:if>
                        </td>

                    </tr>


                    </form>
                </tbody>

            </table>

        </div>
    </body>
</html>

