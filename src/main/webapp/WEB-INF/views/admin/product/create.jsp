<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
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
    <meta name="description" content="Dự án laptopshop" />
    <meta name="author" content="" />
    <title>Create Product</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
      $(document).ready(() => {
        const avatarFile = $("#avatarFile");
        avatarFile.change(function (e) {
          const imgURL = URL.createObjectURL(e.target.files[0]);
          $("#imagePreview").attr("src", imgURL);
          $("#imagePreview").css({ display: "block" });
        });
      });
    </script>
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
            <h1 class="mt-4">Products</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
              <li class="breadcrumb-item active">Product</li>
            </ol>
            <div class="mt-5">
              <div class="row min-w-300 max-h-500 max-w-700 mx-auto h-100">
                <div class="col-md-12 col-12 mx-auto bg-white p-5 shadow-sm border-radius-10 border">
                  <h3>Create a product</h3>
                  <hr />
                  <form:form
                    method="post"
                    action="/admin/product/save"
                    class="row"
                    enctype="multipart/form-data"
                    modelAttribute="newProduct"
                  >
                    <div class="mb-3 col-12 col-md-6">
                      <label class="form-label">Name:</label>

                      <c:set var="name">
                        <form:errors path="name" />
                      </c:set>

                      <form:input
                        type="text"
                        class="form-control ${not empty errorEmail ? 'is-invalid' : ''}"
                        path="name"
                      />
                    </div>
                    <div class="mb-3 col-12 col-md-6">
                      <label class="form-label">Price:</label>
                      <form:input
                        type="number"
                        class="form-control"
                        path="price"
                      />
                    </div>
                    <div class="mb-3 col-12">
                      <label class="form-label">Detail description:</label>
                      <form:textarea
                        type="text"
                        class="form-control"
                        path="detailDesc"
                      />
                    </div>
                    <div class="mb-3 col-12 col-md-6">
                      <label class="form-label">Short description:</label>
                      <form:input
                        type="text"
                        class="form-control"
                        path="shortDesc"
                      />
                    </div>
                    <div class="mb-3 col-12 col-md-6">
                      <label class="form-label">Quantity:</label>
                      <form:input
                        type="number"
                        class="form-control"
                        path="quantity"
                      />
                    </div>

                    <div class="mb-3 col-12 col-md-6">
                      <label class="form-label">Factory:</label>
                      <form:select class="form-select" path="factory">
                        <form:option value="APPLE">Apple (MacBook)</form:option>
                        <form:option value="ASUS">Asus</form:option>
                        <form:option value="LENOVO">Lenovo</form:option>
                        <form:option value="DELL">Dell</form:option>
                        <form:option value="LG">LG</form:option>
                        <form:option value="ACER">Acer</form:option>
                      </form:select>
                    </div>
                    <div class="mb-3 col-12 col-md-6">
                      <label class="form-label">Target:</label>
                      <form:select class="form-select" path="target">
                        <form:option value="GAMING">Gaming</form:option>
                        <form:option value="SINHVIEN-VANPHONG"
                          >Sinh viên - Văn phòng
                        </form:option>
                        <form:option value="THIET-KE-DO-HOA"
                          >Thiết kế đồ họa
                        </form:option>
                        <form:option value="MONG-NHE">Mỏng nhẹ</form:option>
                        <form:option value="DOANH-NHAN">Doanh nhân</form:option>
                      </form:select>
                    </div>
                    <div class="col-6">
                      <label for="image" class="form-label">Image</label>
                      <input
                        type="file"
                        class="form-control"
                        id="image"
                        accept=".jpg, .jpeg, .png"
                      />
                      <small class="text-muted mt-2">
                        Only .jpg, .jpeg, .png files are allowed
                      </small>
                    </div>
                    <form:hidden path="image" id="hiddenImage" />
                    <div class="col-12 mb-3">
                      <div
                        style="width: 200px; position: relative"
                        id="imageContainer"
                      >
                        <img
                          style="width: 100%; height: 100%; display: none"
                          alt="avatar preview"
                          id="imagePreview"
                          class="position-relative"
                        />
                        <div
                          id="removeImage"
                          class="btn btn-danger"
                          style="
                            position: absolute;
                            top: 5px;
                            right: 5px;
                            display: none;
                            border-radius: 50%;
                            opacity: 0.8;
                            object-fit: cover;
                          "
                        >
                          X
                        </div>
                      </div>
                    </div>
                      <div class="col-12 mb-3">
                        <img
                          style="max-height: 250px; display: none"
                          alt="avatar preview"
                          id="imagePreview"
                        />
                      </div>
                      <div class="col-12 mb-5">
                        <button type="submit" class="btn btn-primary createBtn">
                          Create
                        </button>
                      </div>
                  </div>
                  </form:form>
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
      const removeImage = $("#removeImage");
      const btnCreate = $("#createBtn");

      $(document).ready(() => {
        const imageFile = $("#image");

        imageFile.change(function (e) {
          const file = e.target.files[0];

          btnCreate.prop("disabled", true);
          if (file) {
            const imgURL = URL.createObjectURL(file);

            removeImage.css({ display: "block" });
            $("#imageContainer").css({ height: "200px" });
            $("#imagePreview").attr("src", imgURL).css({ display: "block" });

            const formData = new FormData();
            formData.append("file", file);

            $.ajax({
              url: "/upload/image",
              type: "POST",
              data: formData,
              processData: false,
              contentType: false,
              success: function (response) {
                const uploadedImageUrl = response.url;
                if (uploadedImageUrl) {
                  btnUpdate.prop("disabled", false);
                  $("#hiddenImage").val(uploadedImageUrl);
                  $("#imagePreview").attr("src", uploadedImageUrl);
                }

                URL.revokeObjectURL(imgURL);
              },
              error: function (xhr, status, error) {
                btnUpdate.prop("disabled", true);
                console.error("Image upload failed:", error);
                alert("Image upload failed. Please try again.");
              },
            });
          }
        });

        removeImage.click(function () {
          $("#image").val("");
          $("#imagePreview").css({ display: "none" });
          removeImage.css({ display: "none" });
          $("#hiddenImage").val("");
        });
      });
    </script>
  </body>
</html>
