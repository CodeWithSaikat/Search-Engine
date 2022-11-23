<%@page import="java.util.ArrayList"%>
<%@page import="com.Accio.SearchResult"%>

<html>
<head>
    <link rel ="stylesheet" type ="text/css" href="style.css">
</head>
<body>
<h2 class="searchResult"> This is a Search Result </h2>
    <div class ="searchItem">
            <div class="search">
                <form action="Search">
                    <input type="text" placeholder="Search here" name="keyword"></input>
                    <button type="submit">Search</button>
                </form>
            </div>
            <div class ="history">
                <form action="History">
                    <button type="submit">History</button>
                </form>
            </div>
        </div>

    <table border = 2 class="table">
        <tr>
            <td>Name</td>
            <td>Link</td>
        </tr>
        <%
            ArrayList<SearchResult> results = (ArrayList<SearchResult>) request.getAttribute("results");
            for(SearchResult result : results){
         %>
         <tr>
            <td> <% out.println(result.getTitle()); %> </td>
            <td> <a href="<% out.println(result.getLink()); %>"> <% out.println(result.getLink()); %></a> </td>
          </tr>
          <%
            }
          %>
    </table>
</body>
</html>
