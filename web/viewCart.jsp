<%-- 
    Document   : viewCart
    Created on : Jun 19, 2023, 8:00:05 AM
    Author     : nguye
--%>

<%@page import="java.util.Map"%>
<%@page import="phucntt.cart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Java Book Store</h1>
        <%
            //1. Customer goes his/her cart place
            if(session != null){
                //2. Customer tales his/her cart
                CartObj cart = (CartObj)session.getAttribute("CART");
                if(cart != null){
                    //3. Customer get items ( Lay ngan chua do )
                        Map<String, Integer> items = cart.getItems();
                        if(items != null){
                            
                            %>
                            <h2>Your cart includes</h2>
                            <form action="DispatchServlet">
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>Name</th>
                                        <th>Quantity</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int count = 0;
                                        for(String key: items.keySet()){
                                            %>
                                    <tr>
                                        <td>
                                            <%= ++count%>
                                        </td>
                                        <td>
                                            <%= key%>
                                        </td>
                                        <td>
                                            <%= items.get(key) %>
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chkItem" 
                                                   value="<%= key %>" />
                                        </td>
                                    </tr>
                                    <%
                                        }//end traverse items
                                    %>
                                    <tr>
                                        <td colspan="3">
                                            <a href="shopping.html">Add more Items  to your cart</a>
                                        </td>
                                        <td>
                                            <input type="submit" value="Remove Selected Items" name="btAction" />
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            </form>
        <%
                            //4. Travese all item to show
                            return;
                        }//Bat buoc phai ton tai
                }
            }//Xem gio hang thi phai co gio hang moi coi duoc
        %>
    </body>
</html>
