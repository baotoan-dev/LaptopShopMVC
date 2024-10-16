<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%> <%@ taglib uri="http://www.springframework.org/tags/form"
prefix="form"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Create User</title>
    <link href="/css/demo.css" rel="stylesheet" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
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
  <body>
    <div
      class="container d-flex justify-content-center align-items-center"
      style="height: 100vh"
    >
      <div class="form-container col-12 col-md-6">
        <h1 class="text-center">Edit User</h1>
        <form:form
          action="/admin/user/update/${user.id}"
          method="post"
          modelAttribute="user"
        >
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
            <a href="/admin/user" class="btn btn-secondary col-6">Cancel</a>
            <button type="submit" class="btn btn-primary col-6">Update</button>
          </div>
        </form:form>
      </div>
    </div>
  </body>
</html>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%> <%@ taglib uri="http://www.springframework.org/tags/form"
prefix="form"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Create User</title>
    <link href="/css/demo.css" rel="stylesheet" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
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
  <body>
    <div
      class="container d-flex justify-content-center align-items-center"
      style="height: 100vh"
    >
      <div class="form-container col-12 col-md-6">
        <h1 class="text-center">Edit User</h1>
        <form:form
          action="/admin/user/update/${user.id}"
          method="post"
          modelAttribute="user"
        >
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
            <a href="/admin/user" class="btn btn-secondary col-6">Cancel</a>
            <button type="submit" class="btn btn-primary col-6">Update</button>
          </div>
        </form:form>
      </div>
    </div>
  </body>
</html>
>>>>>>> 893de404eba84b784dead3bb210b439cd81f42f2
