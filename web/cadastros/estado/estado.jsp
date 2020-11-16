<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="iso-8859-1" %>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

        <h2>Estados</h2>
        <table align="center" border="1">
            <thead align="center" border="1">
                <tr>
                    <th colspan="6" align="center">Lista de Estados</th>
                </tr>
                <tr>
                    <th align="center">ID</th>
                    <th align="center">Nome</th>
                    <th align="center">Sigla</th>
                    <th align="center" colspan="2">Editar</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="estado" items="${estados}">
                    <tr>
                        <td align="center">${estado.idEstado}</td>
                        <td align="center">${estado.nomeEstado}</td>
                        <td align="center">${estado.siglaEstado}</td>
                        <td align="center"><a href=
                        "${pageContext.request.contextPath}/EstadoExcluir?idEstado=
                        ${estado.idEstado}">Excluir</a></td>

                        <td align="center"><a href=
                        "${pageContext.request.contextPath}/EstadoCarregar?idEstado=
                        ${estado.idEstado}">Alterar</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div align="center">
            <a href="${pageContext.request.contextPath}/EstadoNovo">Novo</a>
            <a href="index.jsp">Voltar à Página Inicial</a>
        </div>

<%@include file="/footer.jsp"%>