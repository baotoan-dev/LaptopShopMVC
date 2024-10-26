<%@ page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <title>Login - Laptopshop</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script
      src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
      crossorigin="anonymous"
    ></script>
    <style>
      .bg-primary-custom {
        background: linear-gradient(135deg, #007bff, #6c757d);
        min-height: 100vh;
      }
      .card-custom {
        border: none;
        border-radius: 1rem;
        box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.2);
      }
      .form-control-custom {
        height: calc(3rem + 2px);
        border-radius: 0.5rem;
        border: 1px solid #ddd;
      }
      .btn-primary-custom {
        background: #007bff;
        border: none;
        font-size: 1rem;
        padding: 0.75rem 1rem;
        border-radius: 0.5rem;
        transition: background 0.3s;
      }
      .btn-primary-custom:hover {
        background: #0056b3;
      }
      .text-muted-custom {
        color: #6c757d;
        font-size: 0.9rem;
      }
    </style>
  </head>

  <body class="bg-primary-custom">
    <div id="layoutAuthentication">
      <div id="layoutAuthentication_content">
        <main>
          <div class="container">
            <div class="row justify-content-center">
              <div class="col-lg-5">
                <div class="card card-custom shadow-lg border-0 mt-5">
                  <div class="card-header text-center">
                    <h3 class="text-uppercase font-weight-bold my-3">Login</h3>
                  </div>
                  <div class="card-body px-5 py-4">
                    <form method="post" action="/login">
                      <c:if test="${param.error != null}">
                        <div class="alert alert-danger text-center my-2">
                          Invalid email or password.
                        </div>
                      </c:if>

                      <div class="form-floating mb-4">
                        <input
                          class="form-control form-control-custom"
                          type="email"
                          placeholder="name@example.com"
                          name="username"
                          required
                        />
                        <label>Email address</label>
                      </div>
                      <div class="form-floating mb-4">
                        <input
                          class="form-control form-control-custom"
                          type="password"
                          placeholder="Password"
                          name="password"
                          required
                        />
                        <label>Password</label>
                      </div>
                      <input
                        type="hidden"
                        name="${_csrf.parameterName}"
                        value="${_csrf.token}"
                      />

                      <div class="mt-4 mb-3 d-grid">
                        <button
                          class="btn btn-primary-custom btn-block"
                          type="submit"
                        >
                          Login
                        </button>
                      </div>
                    </form>
                  </div>
                  <div class="card-footer text-center py-3">
                    <div class="small text-muted-custom">
                      <a href="/register">Need an account? Sign up!</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"
    ></script>
    <script src="/js/scripts.js"></script>
  </body>
</html>
