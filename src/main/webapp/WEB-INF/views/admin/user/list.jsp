<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="Dự án laptopshop" />
    <meta name="author" content="" />
    <title>Dashboard</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script
      src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
      crossorigin="anonymous"
    ></script>
  </head>

  <body class="sb-nav-fixed">
    <jsp:include page="../layout/header.jsp" />
    <div id="layoutSidenav">
      <jsp:include page="../layout/sidebar.jsp" />
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">Manage Users</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
              <li class="breadcrumb-item active">Users</li>
            </ol>
            <div class="d-flex justify-content-between mb-4">
              <h3></h3>
              <div class="d-flex gap-2">
                <form action="/admin/user" method="get">
                  <input type="hidden" name="keyword" value="${keyword}" path="${keyword}" />
                  <select name="sort" class="form-select" onchange="this.form.submit()">
                    <option value="" ${sort == '' ? 'selected' : ''}>Sort by</option>
                    <option value="fullName_asc" ${sort == 'fullName_asc' ? 'selected' : ''}>Name A-Z</option>
                    <option value="fullName_desc" ${sort == 'fullName_desc' ? 'selected' : ''}>Name Z-A</option>
                    <option value="email_asc" ${sort == 'email_asc' ? 'selected' : ''}>Email A-Z</option>
                    <option value="email_desc" ${sort == 'email_desc' ? 'selected' : ''}>Email Z-A</option>
                  </select>
                </form>                    
                <a href="/admin/user/create" class="btn btn-primary"
                  >Create a product</a
                >
              </div>
            </div>
              <!-- search -->
              <form action="/admin/user" method="get" id="searchForm">
                <div class="input-group mb-3">
                  <input type="hidden" name="sort" value="${sort}" path="${sort}" />
                  <input
                    type="text"
                    class="form-control"
                    placeholder="Search by email"
                    name="keyword"
                    id="emailInput"
                    value="${keyword}"
                    path="${keyword}"
                  />
                </div>
              </form>
        
              <table class="table table-striped table-hover table-bordered table-responsive">
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Role</th>
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
                      <td>${user.role.name}</td>
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
        </main>
        <jsp:include page="../layout/footer.jsp" />
      </div>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"
    ></script>
    <script src="/js/scripts.js"></script>
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
