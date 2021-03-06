<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/TipoUsuario" var="registerUrl" />
<c:set var="data" scope="application" value="${datos}"/>

<%--header--%>
<jsp:include page="../components/header.jsp"/>

<%-- navbar --%>
<jsp:include page="../components/navbar.jsp"/>

<div class="full-height col-12">
	<div class="content full-height col-10 offset-1">
		<div class="row">
			<div class="col-12">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="Usuario?action=index">Administración de Usuarios</a></li>
						<li class="breadcrumb-item"><a href="TipoUsuario?action=index">Tipo Usuario</a></li>
						<li class="breadcrumb-item active" aria-current="page">Edición</li>
					</ol>
				</nav>
			</div>
		</div>

		<div class="container col-12 col-md-9 offset-md-1">
			<form method="POST" action="${registerUrl}" class="needs-validation pt-3" novalidate>
				<c:forEach items="${data}" var="tipo_usuario">
					<input type="hidden" name="option" value="update">
					<input type="hidden" name="redirect" value="false">

					<input type="hidden" name="id" value="${tipo_usuario.id}">

					<section class="form-group">
						<label><strong class="asterisk-required">*</strong> Tipo</label>
						<input type="text" name="tipo" class="form-control" value="${tipo_usuario.tipo}" required>
						<div class="invalid-feedback">
							Debe ingresar un tipo de usuario.
						</div>
					</section>

					<section class="form-group">
						<label><strong class="asterisk-required">*</strong> Descripcion </label>
						<input type="text" name="descripcion" class="form-control" value="${tipo_usuario.descripcion}" required>
						<div class="invalid-feedback">
							Debe ingresar una descripción.
						</div>
					</section>
				</c:forEach>

				<section>
					<label class="required"><strong>* Campo requerido</strong></label>
				</section>

				<div class="row pt-3 justify-content-end">
					<a href="TipoUsuario?action=index" class="col-12 col-md-3 offset-md-5 mr-3 btn btn-danger" role="button" aria-pressed="true">Cancelar</a>
					<button type="submit" class="col-12 col-md-3 mr-3 btn btn-success">Actualizar</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- jQuery CDN -->
<script src="js/vendor/jquery.js"></script>

<!-- Popper.JS -->
<script src="js/vendor/popper.js"></script>

<!-- Bootstrap JS -->
<script src="js/vendor/bootstrap.js"></script>

<!-- app -->
<script src="js/app.js"></script>

<%-- footer--%>
<jsp:include page="../components/footer.jsp"/>
            
