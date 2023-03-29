package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;



public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        List<Map<String, String>> articles = new ArrayList<>();
        int page;
        if (request.getParameter("page") == null || Integer.parseInt(request.getParameter("page")) < 1) {
            page = 1;
        } else {
            page = Integer.parseInt(request.getParameter("page"));
        }
        String query;
        if (page == 1) {
            query = "SELECT * FROM articles ORDER BY id LIMIT 10 OFFSET ? * 0";
        } else {
            query = "SELECT * FROM articles ORDER BY id LIMIT 10 OFFSET ? * 10 - 10";
        }
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, page);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                articles.add(Map.of(
                        "id", result.getString("id"),
                        "title", result.getString("title"),
                        "body", result.getString("body")
                        )
                );
            }
        } catch (SQLException e) {
            response.sendError(500);
            return;
        }
        request.setAttribute("articles", articles);
        request.setAttribute("page", page);
        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        var article = new HashMap<>();
        String query = "SELECT * FROM articles WHERE id = ?";
        int id = Integer.parseInt(getId(request));
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                article.put("id",result.getString("id"));
                article.put("title",result.getString("title"));
                article.put("body",result.getString("body"));
            }
        } catch (SQLException e) {
            response.sendError(500);
            return;
        }
        if (article.isEmpty()) {
            response.sendError(404);
            return;
        }
        request.setAttribute("article", article);
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
