<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <meta name="author" content="Hỏi Dân IT" />
    <title>Dashboard</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script
      src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
      crossorigin="anonymous"
    ></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <style>
      body {
        background-color: #f8f9fa;
      }
      .form-container {
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      }
    </style>
  </head>

  <body class="sb-nav-fixed">
    <jsp:include page="../layout/header.jsp" />
    <div id="layoutSidenav">
      <jsp:include page="../layout/sidebar.jsp" />
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">Manage Users</h1>
            <div class="d-flex justify-content-center align-items-center">
              <div class="form-container col-12 col-md-6">
                <h1 class="text-center">Edit User</h1>
                <form:form action="/admin/user/update/${user.id}" method="post" modelAttribute="user">
                  <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <form:input
                      class="form-control"
                      id="email"
                      path="email"
                      value="${user.email}"
                    />
                  </div>
                  <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <form:password
                      class="form-control"
                      id="password"
                      path="password"
                      value="${user.password}"
                    />
                  </div>
                  <div class="mb-3">
                    <label for="phone" class="form-label">Phone</label>
                    <form:input
                      class="form-control"
                      id="phone"
                      path="phone"
                      value="${user.phone}"
                    />
                  </div>
                  <div class="mb-3">
                    <label for="fullName" class="form-label">fullName</label>
                    <form:input
                      class="form-control"
                      id="fullName"
                      path="fullName"
                      value="${user.fullName}"
                    />
                  </div>
                  <div class="mb-3">
                    <label for="address" class="form-label">Address</label>
                    <form:input
                      class="form-control"
                      id="address"
                      path="address"
                      value="${user.address}"
                    />
                  </div>
                  <div class="mb-3 d-flex justify-content-between gap-2">
                    <div class="col-6">
                      <label for="role" class="form-label">Role</label>
                      <form:select
                        class="form-select col-6"
                        id="role"
                        path="role"
                      >
                        <form:option value="USER" label="User" />
                        <form:option value="ADMIN" label="Admin" />
                      </form:select>
                    </div>
                    <div class="col-6">
                      <label for="avatar" class="form-label"
                      id="avatarLabel"
                      <c:if test="${not empty user.avatar}">style="display:none;"</c:if>
                      >
                        Avatar
                      </label>
                      <input
                        type="file"
                        class="form-control"
                        id="avatar"
                        accept=".jpg, .jpeg, .png"
                        <c:if test="${not empty user.avatar}">style="display:none;"</c:if>
                      />
                      <small id="small-note" class="text-muted mt-2" <c:if test="${not empty user.avatar}">style="display:none;"</c:if>>
                        Only .jpg, .jpeg, .png files are allowed
                      </small>
                    </div>
                    <form:hidden path="avatar" id="hiddenAvatar" value="${user.avatar}" />
                  </div>
                  <div class="col-12 mb-3">
                    <div style="width: 200px; position: relative" id="avatarContainer">
                        <img
                          style="width: 100%; height: 100%"
                          alt="avatar preview"
                          id="avatarPreview"
                          src="${user.avatar}"
                        />
                        <div
                          id="removeAvatar"
                          class="btn btn-danger"
                          style="position: absolute; top: 5px; right: 5px; border-radius: 50%; opacity: 0.8;"
                          onclick="removeAvatar()"
                          <c:if test="${empty user.avatar}">style="display:none;"</c:if>
                        >
                          X
                        </div>
                    </div>
                  </div>

                  <div class="mb-3 d-flex justify-content-between gap-2">
                    <a href="/admin/user" class="btn btn-secondary col-6">Cancel</a>
                    <button type="submit" class="btn btn-primary col-6">Update</button>
                  </div>
                </form:form>
              </div>
            </div>
          </div>
        </main>
        <jsp:include page="../layout/footer.jsp" />
      </div>
    </div>

    <script>
      $(document).ready(() => {
        const avatarFile = $("#avatar");
        avatarFile.change(function (e) {
          const removeAvatar = $("#removeAvatar");
          const imgURL = URL.createObjectURL(e.target.files[0]);
          removeAvatar.css({ display: "block" });
          $("avatarContainer").css({ height: "200px" });
          $("#avatarPreview").attr("src", imgURL);
          $("#avatarPreview").css({ display: "block" });

          const formData = new FormData();

          formData.append("file", e.target.files[0]);

          $.ajax({
            url: "/upload/image",
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
              const uploadedImageUrl = response.url;
              console.log('uploadedImageUrl', uploadedImageUrl);
              if (uploadedImageUrl) {
                const name = uploadedImageUrl.split("upload/")[1];
                if (name) {
                  $("#hiddenAvatar").val(name);
                  $("#avatarPreview").attr("src", uploadedImageUrl);
                }
              }
            },
            error: function (xhr, status, error) {
              console.error("Image upload failed:", error);
            },
          });
        });
      });
      function removeAvatar() {
        document.getElementById("avatarPreview").style.display = "none";
        document.getElementById("removeAvatar").style.display = "none";
        document.getElementById("avatar").style.display = "block";
        document.getElementById("avatarLabel").style.display = "block";
        document.getElementById("small-note").style.display = "block";
        document.getElementById("hiddenAvatar").value = "";
        document.getElementById("avatar").value = "";
      }
    </script>
  </body>
</html>
