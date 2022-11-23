<%@page import="java.util.ArrayList"%>
<%@page import="com.Accio.HistoryResult"%>

<html>
<head>
    <link rel ="stylesheet" type ="text/css" href="style.css">
</head>
<body>
<h2 class="searchResult"> This is a History Result </h2>

    <table border = 2>
        <tr>
            <td>Name</td>
            <td>Link</td>
        </tr>
        <%
            ArrayList<HistoryResult> results = (ArrayList<HistoryResult>) request.getAttribute("results");
            for(HistoryResult result : results){
         %>
         <tr>
            <td> <% out.println(result.getName()); %> </td>
            <td> <a href="<% out.println(result.getLink()); %> "> <% out.println(result.getLink()); %> </a> </td>
          </tr>
          <%
            }
          %>
    </table>
</body>
</html>
