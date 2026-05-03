<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Produits</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<div class="container mt-4">

    <!-- HEADER -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>Gestion des Produits</h3>

        <div>
            <c:if test="${currentUser != null}">
                <span class="badge bg-primary">
                    ${currentUser.username} (${currentUser.role})
                </span>
            </c:if>

            <a href="logout" class="btn btn-danger btn-sm ms-2">Logout</a>
        </div>
    </div>

    <!-- ERROR MESSAGE -->
    <c:if test="${param.unauthorized != null}">
        <div class="alert alert-danger">
            Accès non autorisé
        </div>
    </c:if>

    <!-- SEARCH -->
    <div class="card mb-3">
        <div class="card-body">
            <form action="searchProduct" method="post" class="d-flex gap-2">
                <input type="text" name="idProduit" class="form-control" placeholder="Search by ID">
                <button class="btn btn-primary">Search</button>
            </form>
        </div>
    </div>

    <!-- ADD / EDIT (ADMIN ONLY) -->
    <c:if test="${currentUser.role == 'ADMIN'}">
        <div class="card mb-3">
            <div class="card-body">

                <form action="${produitEdit != null ? 'updateProduit' : 'addProduct'}" method="post">

                    <c:if test="${produitEdit != null}">
                        <input type="hidden" name="idProduit" value="${produitEdit.idProduit}">
                    </c:if>

                    <div class="row g-2">

                        <div class="col">
                            <input type="text" name="nom" class="form-control"
                                   placeholder="Nom"
                                   value="${produitEdit.nom}">
                        </div>

                        <div class="col">
                            <input type="text" name="description" class="form-control"
                                   placeholder="Description"
                                   value="${produitEdit.description}">
                        </div>

                        <div class="col">
                            <input type="number" step="0.01" name="prix" class="form-control"
                                   placeholder="Prix"
                                   value="${produitEdit.prix}">
                        </div>

                        <div class="col-auto">
                            <button class="btn btn-success">
                                ${produitEdit != null ? 'Update' : 'Add'}
                            </button>
                        </div>

                    </div>
                </form>

            </div>
        </div>
    </c:if>

    <!-- TABLE -->
    <div class="card">
        <div class="card-body">

            <table class="table table-bordered table-hover">

                <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Description</th>
                    <th>Prix</th>
                    <c:if test="${currentUser.role == 'ADMIN'}">
                        <th>Actions</th>
                    </c:if>
                </tr>
                </thead>

                <tbody>

                <c:forEach items="${listeProduit}" var="p">
                    <tr>
                        <td>${p.idProduit}</td>
                        <td>${p.nom}</td>
                        <td>${p.description}</td>
                        <td>${p.prix}</td>

                        <c:if test="${currentUser.role == 'ADMIN'}">
                            <td>
                                <a href="editProduit?id=${p.idProduit}" class="btn btn-warning btn-sm">Edit</a>
                                <a href="deleteProduit?id=${p.idProduit}" class="btn btn-danger btn-sm"
                                   onclick="return confirm('Delete?')">Delete</a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>

                <c:if test="${empty listeProduit}">
                    <tr>
                        <td colspan="5" class="text-center">No products</td>
                    </tr>
                </c:if>

                </tbody>

            </table>

        </div>
    </div>

</div>

</body>
</html>