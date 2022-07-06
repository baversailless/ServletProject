package com.example.ServletPro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/organizations")
public class HelloServlet extends HttpServlet {
    private Map<Long, Organization> organizations = new HashMap<>();
    Organization organization1 = new Organization("Bowman", "Box 198", LocalDate.of(2020, 12, 15));
    Organization organization2 = new Organization("Davidson Cattle", "HC 78 Box 110", LocalDate.of(2020, 6, 04));
    Organization organization3 = new Organization("Montana Stockgrowers", "111 Garrywen", LocalDate.of(2021, 8, 23));

    public void init() throws ServletException {
        organizations.put(organization1.getId(), organization1);
        organizations.put(organization2.getId(), organization2);
        organizations.put(organization3.getId(), organization3);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String organizationJsonString = mapper.writeValueAsString(organizations);
        pw.println(organizationJsonString);
        pw.flush();
    }

    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter pw = resp.getWriter();
        String title = req.getParameter("title");
        String address = req.getParameter("address");
        String creationDate = req.getParameter("creationDate"); //yyyy-MM-dd
        LocalDate localDate = LocalDate.parse(creationDate);
        Organization organization = new Organization(title, address, localDate);
        organizations.put(organization.getId(), organization);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String json = mapper.writeValueAsString(organization);
        pw.println(json);
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        organizations.remove(id);
    }
}