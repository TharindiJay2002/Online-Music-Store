package com.mstore.user;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mstore.dbutill.DBConnectStatic;
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static String TableName = "user";
	private static String[] Columns = {"email", "Password", "fname", "lname", "Address", "Phone", "Role", "DOB"};
	private String redirect;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		redirect = request.getParameter("redirect");
		switch (action) {
		case "create":
			create(request, response);
			break;
		case "update":
			update(request, response);
			sessionrefresh(request);
			break;
		case "delete":
			delete(request, response);
			break;
		case "login":
			login(request, response);
			break;
		case "deleteandlogout":
			delete(request, response);
			logout(request, response);
			break;
		}
		response.sendRedirect(redirect);
	}
	
	private void sessionrefresh(HttpServletRequest request) {
		String userEmail = request.getParameter("email"); // Get email
		User updatedUser = UserServlet.getOne(userEmail); // Fetch updated user details
		request.getSession().setAttribute("user", updatedUser); // Update session
		request.getSession().setAttribute("updateSuccess", true);
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("user");	
	}
	
	
	private void login(HttpServletRequest request, HttpServletResponse response) {
		ResultSet RS =  DBConnectStatic.ReadFromDB(TableName);
        try {
            while (RS.next()) {
            	request.getSession().setAttribute("admin", null);
                if (RS.getString("email").toLowerCase().equals(request.getParameter("email").toLowerCase()) && RS.getString("Password").equals(request.getParameter("password"))) {
                    redirect = "user.jsp?email=" + RS.getString("email");
                	request.getSession().setAttribute("user", new User(RS.getString("Email"), RS.getString("Password"), RS.getString("fName"),
                            RS.getString("lName"), RS.getString("Address"), RS.getString("Phone"), RS.getString("Role"),
                            RS.getString("DOB")));
                    request.getSession().setAttribute("artist", null);
					if (RS.getString("Role").equals("Admin")) {
						request.getSession().setAttribute("Admin", "True");
						redirect = "admin.jsp";
					}
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	private static String[] getValues(HttpServletRequest request) {
		return new String[] { request.getParameter("email"), request.getParameter("password"),
				request.getParameter("fname"), request.getParameter("lname"), request.getParameter("address"),
				request.getParameter("phone"), request.getParameter("role"), request.getParameter("dob") };
	}
	
	private static void delete(HttpServletRequest request, HttpServletResponse response) {
		DBConnectStatic.DeleteFromDB(TableName, "Email",  request.getParameter("email"));	
	}

	private static void update(HttpServletRequest request, HttpServletResponse response) {
       System.out.println(DBConnectStatic.UpdateDB(TableName, Columns, getValues(request)));
    }

	private static void create(HttpServletRequest request, HttpServletResponse response) {
		DBConnectStatic.Add2DB(TableName,Columns,getValues(request));
	}
	
	public static List<User> getAll() {
		ResultSet RS =  DBConnectStatic.ReadFromDB(TableName);
		List<User> users = new ArrayList<User>();
		try {
			while (RS.next()) {
				users.add(new User(RS.getString("Email"), RS.getString("Password"), RS.getString("fname"),
						RS.getString("lname"), RS.getString("Address"), RS.getString("Phone"), RS.getString("Role"),
						RS.getString("DOB")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public static User getOne(String email) {
		ResultSet RS = DBConnectStatic.ReadFromDB(TableName, "Email", email);
		User user = null;
		try {
			while (RS.next()) {
				user = new User(RS.getString("Email"), RS.getString("Password"), RS.getString("fName"),
						RS.getString("lName"), RS.getString("Address"), RS.getString("Phone"), RS.getString("Role"),
						RS.getString("DOB"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
