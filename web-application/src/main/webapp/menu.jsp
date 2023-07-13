<%@ page session="true" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="userRole" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userRole}"/>


<script>
    $(document).ready(function () {
        var value = $(".alert-message").html();
        if($.trim(value)) {
            $("#globalAlertBody").html(value);
            window.$("#globalAlert").modal("show");
        }
    });
</script>


<div class="modal" id="globalAlert">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header  modal-header-warning">
                <h4 class="modal-title">Operation status!</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <p id="globalAlertBody"></p>
            </div>
            <div class="modal-footer">
                <button type="button" id="globalAlertOk" class="btn btn-primary" data-dismiss="modal">Ok</button>
            </div>
        </div>
    </div>
</div>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li class="active"><a href="/">Home</a></li>
            <li><a href="${contextPath}/registration">Enroll Application</a></li>
            <li><a href="${contextPath}/downloadReceipt">Download Receipt</a></li>
            <c:if test="${userRole == 'ROLE_ADMIN'}">
                <li><a href="${contextPath}/admin/application-search">Enrolled Application</a></li>
                <li><a href="${contextPath}/admin/search-uploaded-application">Uploaded Application</a></li>
                <li><a href="${contextPath}/user/create-user">Create User</a></li>
                <li><a href="${contextPath}/user/search-user">List User</a></li>
                <li><a href="${contextPath}/admin/sendSMS">SendSMS</a></li>
            </c:if>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <security:authorize access="!isAuthenticated()">
                <li><a href="${contextPath}/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </security:authorize>

            <security:authorize access="isAuthenticated()">
                <li><a href="${contextPath}/logout">Logout</a></li>
            </security:authorize>

        </ul>
    </div>
</nav>