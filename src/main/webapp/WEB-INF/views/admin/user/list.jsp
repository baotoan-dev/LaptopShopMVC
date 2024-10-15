<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link href="/css/demo.css" rel="stylesheet" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  </head>
  <body>
    <!-- render all users -->
    <div class="container">
      <div class="d-flex justify-content-between align-items-center">
        <h1 class="mt-5">Users</h1>
        <a href="/admin/user/create" class="btn btn-primary">Create User</a>
      </div>
      <!-- search -->
      <form action="/admin/user" method="get" id="searchForm">
        <div class="input-group mb-3">
          <input
            type="text"
            class="form-control"
            placeholder="Search by email"
            name="email"
            id="emailInput"
            value="${email}"
          />
        </div>
      </form>

      <table class="table table-striped table-hover table-bordered table-responsive">
        <thead>
          <tr>
            <th>Id</th>
            <th>fullName</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="user" items="${users}">
            <tr>
              <td>${user.id}</td>
              <td>${user.fullName}</td>
              <td>${user.email}</td>
              <td>${user.phone}</td>
              <td>
                <a href="/admin/user/detail/${user.id}" class="btn btn-warning">View</a>
                <a href="/admin/user/edit/${user.id}" class="btn btn-info">Edit</a>
                <a class="btn btn-danger btn-delete" data-id="${user.id}">Delete</a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>

      <!-- popup confirm delete -->
      <div class="modal" id="deleteModal">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title">Delete User</h4>
              <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
              Are you sure you want to delete this user?
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <a id="confirmDelete" href="#" class="btn btn-danger">Delete</a>
            </div>
          </div>
        </div>
  </body>

  <script>
    const emailInput = document.getElementById('emailInput');
    const searchForm = document.getElementById('searchForm');
    const btnDeletes = document.querySelectorAll('.btn-delete');
    const deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
  
    emailInput.addEventListener('input', function () {
      clearTimeout(this.delay);
      this.delay = setTimeout(function () {
        searchForm.submit();
      }, 500);
    });

   
    btnDeletes.forEach((btnDelete) => {
      btnDelete.addEventListener('click', function (event) {
        event.preventDefault(); 
        const id = this.getAttribute('data-id');
        const confirmDelete = document.getElementById('confirmDelete');
        const url = '/admin/user/delete/' + id;
        confirmDelete.href = url;
        deleteModal.show();
      });
    });
    
  </script>
</html>
