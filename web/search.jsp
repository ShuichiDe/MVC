<%-- 
    Document   : search
    Created on : Jun 8, 2023, 7:17:05 AM
    Author     : nguye
--%>

<%@page import="phucntt.registation.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <%
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                %>
                <font color="red">Welcome, <%=cookies[cookies.length - 1].getName() %></font>
        <%
            }
        %>
        <h1>Search Page</h1>
        <a href="DispatchServlet?action=Logout">Logout</a>
        <form action="DispatcherServlet">
            Search <input type="text" name="txtSearch" 
                          value="<%= request.getParameter("txtSearch")%>"/></br>
            <input type="submit" value="Search" name="btAction" />
        </form>
        </br>
        <%
            String searchValue = request.getParameter("txtSearch"); 
            if (searchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
                if (result != null) {
        %>

        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>User</th>
                    <th>Full Name</th>
                    <th>Password</th>
                    <th>Role ID</th>
                    <th>Status</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "DispatchServlet?btAction=delete&pk=" + dto.getUserID() + "&lastSearchValue=" + searchValue;
                %>
            <form action="DispatchServlet" method="POST">
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= dto.getUserID()%>
                        <input type="hidden" name="txtUsername" value="<%= dto.getUserID()%>" />
                    </td>
                    <td>
                        <%= dto.getFullName()%>
                    </td>
                    <td>
                        <input type="text" name="txtPassword" value="<%= dto.getPassword()%>" />
                    </td>
                    <td>
                        <input type="checkbox" name="chkAdmin" value="ON" 
                               <%
                                   if (dto.isStatus()) {
                               %>
                               checked="checked"
                               <%
                                   }
                               %>
                               />
                    </td>
                    <td>
                        <%= dto.isStatus()%>
                    </td>
                    <td>
                        <a href="<%= urlRewriting %>">Delete</a>
                    </td>
                    <td>
                        <input type="hidden" name="lastSearchValue" value="<%= searchValue %>" />
                        <button type="submit" value="Update" name="btAction">Update</button>

                    </td>
                </tr> 
            </form>

            <%
                }//end data from result


            %>

        </tbody>
    </table>


    <%            } else {//result == null
    %>
    <h2> No recording is matched!!! </h2>
    <%
            }
        }//end page is called invalid
%>

</body>
</html>
