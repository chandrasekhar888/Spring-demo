<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student List</title>
    <style>
        table {
            width: 70%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #999;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        h2 {
            text-align: center;
        }
    </style>
</head>
<body>

<h2>Student Records</h2>

<table>
    <tr>
        <th>Name</th>
        <th>Course</th>
        <th>Email</th>
        <th>Delete</th>
        <th>Update</th> 
    </tr>
    
    <c:forEach var="student" items="${students}">
        <tr>
            <td>${student.name}</td>
            <td>${student.course}</td>
            <td>${student.email}</td>
            <td>
                <a href="deleteReg?id=${student.id}" onclick="return confirm('Are you sure you want to delete this student?');">Delete</a>
            </td> <!-- Delete link -->
            <td>
                <a href="FindByIdReg?id=${student.id}" onclick="return confirm('Are you sure you want to update this student?');">Update</a>
            </td> <!-- Update link And find by id from view controller -->
        </tr>
    </c:forEach>
</table>

</body>
</html>
