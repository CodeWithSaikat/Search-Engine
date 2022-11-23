package com.Accio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/Search")
public class Search extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            String searchOutput = request.getParameter("keyword");
            Connection connection = DataBase.getConnection();

            searchOutput = searchOutput.toLowerCase();

            // result from your database
            ResultSet resultSet = connection
                                    .createStatement()
                                    .executeQuery("select pageTitle, pageLink, (length(lower(pageData))-length(replace(lower(pageData),'"+searchOutput+"', ' ')))/length('"+searchOutput+"') " +
                                            "as countoccurence from searchPages order by countoccurence desc limit 30;");

            ArrayList<com.Accio.SearchResult> results = new ArrayList<com.Accio.SearchResult>();
            while (resultSet.next()){
                SearchResult searchResult = new SearchResult();
                searchResult.setTitle(resultSet.getString("pageTitle"));
                searchResult.setLink(resultSet.getString("pageLink"));
                results.add(searchResult);
            }
//            for (SearchResult searchResult:results){
//                System.out.println(searchResult.getTitle()+ " " + searchResult.getLink()+"\n");
//            }
            // forwarded to search file for displying details;
            request.setAttribute("results", results);
            request.getRequestDispatcher("Search.jsp").forward(request, response);

            PreparedStatement preparedStatement = connection.prepareStatement("insert into histroy values(?,?)");
            preparedStatement.setString(1,searchOutput);
            preparedStatement.setString(2, "http://localhost:8080/SearchEngine/Search?keyword="+searchOutput);
            preparedStatement.executeUpdate();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2>This is a servlet " + searchOutput + "</h2>");

            // Crawler crawler = new Crawler();
            //crawler.getPageTextAndLink("https://www.javatpoint.com", 0);

        } catch (IOException ioException){
            System.out.println(ioException);
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
