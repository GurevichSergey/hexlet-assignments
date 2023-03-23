package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        PrintWriter pw = response.getWriter();
        if (request.getQueryString() == null) {
            pw.write(getCompanies().stream().collect(Collectors.joining("\n")));
        }
        else if (request.getQueryString().contains("search") && !request.getParameter("search").isEmpty()) {
            String result = getCompanies().stream()
                    .filter(x -> x.contains(request.getParameter("search")))
                    .collect(Collectors.joining("\n"));
            if (!result.isEmpty()) {
                pw.write(result);
            } else {
                pw.write("Companies not found");
            }
        } else {
            pw.write(getCompanies().stream().collect(Collectors.joining("\n")));
        }
        pw.close();
        // END
    }
}
