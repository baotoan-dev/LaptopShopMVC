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
            <h1 class="mt-4">Manage Products</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
              <li class="breadcrumb-item active">Products</li>
            </ol>
            <div>
              <div class="row">
                <div class="col-12 mx-auto">
                  <div class="d-flex justify-content-between">
                    <h3></h3>
                    <div class="d-flex gap-2">
                      <form action="/admin/product" method="get">
                        <input type="hidden" name="keyword" value="${keyword}" />
                        <select name="sort" class="form-select" onchange="this.form.submit()">
                            <option value="name_asc" ${sort == 'name_asc' ? 'selected' : ''}>Name A-Z</option>
                            <option value="name_desc" ${sort == 'name_desc' ? 'selected' : ''}>Name Z-A</option>
                        </select>
                      </form>                    
                      <a href="/admin/product/create" class="btn btn-primary"
                        >Create a product</a
                      >
                    </div>
                  </div>

                  <div>
                    <!-- Search Form -->
                    <form
                      action="/admin/product"
                      method="get"
                      class="d-flex mt-3"
                      id="searchForm"
                    >
                      <input type="hidden" name="sort" value="${sort}" />
                      <input
                        type="text"
                        name="keyword"
                        class="form-control"
                        placeholder="Search by name"
                        id="keyword"
                        value="${keyword}"
                      />
                    </form>
                  </div>

                  <hr />
                  <table class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>ID</th>
                        <th>Image</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Description</th>
                        <th>Action</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="product" items="${products}">
                        <tr>
                          <th>${product.id}</th>
                          <td>
                            <c:choose>
                              <c:when test="${not empty product.image}">
                                <img
                                  src="${product.image}"
                                  alt="${product.name}"
                                  width="100"
                                  height="100"
                                />
                              </c:when>
                              <c:otherwise>
                                <p>No image available</p>
                              </c:otherwise>
                            </c:choose>
                          </td>
                          <td>${product.name}</td>
                          <td>${product.price}</td>
                          <td>${product.detailDesc}</td>
                          <td>
                            <a
                              href="/admin/product/view/${product.id}"
                              class="btn btn-success"
                              >View</a
                            >
                            <a
                              href="/admin/product/edit/${product.id}"
                              class="btn btn-warning mx-2"
                              >Update</a
                            >
                            <a
                              href="/admin/product/delete/${product.id}"
                              class="btn btn-danger"
                              >Delete</a
                            >
                          </td>
                        </tr>
                      </c:forEach>
                    </tbody>
                  </table>

                  <!-- Pagination controls -->
                  <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                      <c:forEach var="i" begin="1" end="${totalPages}">
                        <li
                          class="page-item ${i == currentPage ? 'active' : ''}"
                        >
                          <a
                            class="page-link"
                            href="?page=${i}&keyword=${keyword}"
                            >${i}</a
                          >
                        </li>
                      </c:forEach>
                    </ul>
                    <p class="text-center">
                      Showing ${currentPage} of ${totalPages} pages
                    </p>
                  </nav>
                </div>
              </div>
            </div>
          </div>
        </main>
      </div>
      <jsp:include page="../layout/footer.jsp" />
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"
    ></script>
    <script src="/js/scripts.js"></script>
    <script>
      const keyword = document.getElementById("keyword");
      const searchForm = document.getElementById("searchForm");

      keyword.addEventListener("input", function () {
        clearTimeout(this.delay);
        this.delay = setTimeout(function () {
          searchForm.submit();
          keyword.focus();
        }, 500);
      });
    </script>
  </body>
</html>
