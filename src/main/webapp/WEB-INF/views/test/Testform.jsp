<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="<%=request.getContextPath()%>" />
<!doctype html>
<html>
  <head>
  <script src="https://code.jquery.com/jquery-latest.min.js"></script>
  <script src="${path}/resources/ckeditor5/build/ckeditor.js"></script>
  <meta charset="utf-8">
    <title>CKEditor</title>
    <style>
      .ck-editor__editable { height: 400px; }
      .ck-content { font-size: 12px; }
    </style>
  </head>
  <body>
    <h1>CKEditordd</h1>
    <form action="" method="POST">
      <textarea name="text" id="editor"></textarea>
    <p><input type="submit" value="전송"></p>
    </form>
    <script>
      ClassicEditor.create( document.querySelector( '#editor' ) );
    </script>
  </body>
</html>
