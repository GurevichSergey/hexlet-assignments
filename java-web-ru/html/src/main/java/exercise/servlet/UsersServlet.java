package exercise.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("src/main/resources/users.json"), List.class);
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<Map> user = getUsers();
        StringBuilder body = new StringBuilder();
        body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
                                    rel="stylesheet"
                                    integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
                                    crossorigin="anonymous">
                    </head>
                    <body>
                    
                """);

        for (Map<String, String> users : user) {
            String id = users.get("id");
            String fullName = users.get("firstName") + " " + users.get("lastName");

            body.append("<tr>");
            body.append("<td>" + id + "</id>");
            body.append("<td><a href=\"/user/" + id + "\">" + fullName + "</a></id>");
            body.append("</tr>");
        }

        body.append("""
                          </table>
                       </div>
                    </body>
                </html>
                """);

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println(body.toString());
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List<Map> user = getUsers();
        Map<String, String> users = user
                .stream()
                .filter(u -> u.get("id").equals(id))
                .findAny()
                .orElse(null);

        if (users == null) {
            response.sendError(404);
            return;
        }

        StringBuilder body = new StringBuilder();
        body.append("""
                        <!DOCTYPE html>
                        <html lang=\"ru\">
                            <head>
                                <meta charset=\"UTF-8\">
                                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
                                            rel="stylesheet"
                                            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
                                            crossorigin="anonymous">
                            </head>
                            <body>
                            
                            
                        """);

        for (Map.Entry<String, String> entry : users.entrySet()) {
            body.append("<div>");
            body.append(entry.getKey() + ": " + entry.getValue());
            body.append("</div>");
        }

        body.append("""
                                  </table>
                               </div>
                            </body>
                        </html>
                        """);

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println(body.toString());
        // END
    }
}
