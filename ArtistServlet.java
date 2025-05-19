package com.mstore.artist;

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
import com.mstore.user.User;
import com.mstore.user.UserServlet;

@WebServlet("/ArtistServlet")
public class ArtistServlet extends HttpServlet {
	private static String TableName = "artist";
	private static String[] Columns = {"email", "password", "fName", "lName", "artistbio"};
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
		}
		response.sendRedirect(redirect);
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("artist");
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) {
		ResultSet RS =  DBConnectStatic.ReadFromDB(TableName);
        try {
            while (RS.next()) {
                if (RS.getString("email").equals(request.getParameter("email")) && RS.getString("password").equals(request.getParameter("password"))) {
                    request.getSession().setAttribute("artist", new Artist(RS.getString("email"), RS.getString("password"), RS.getString("fname"),
                            RS.getString("lname"), RS.getString("artistbio")));
                    request.getSession().setAttribute("user", null);
                    redirect = "artist.jsp?email=" + RS.getString("email");
                    break;
                } else {
                	System.out.println(RS.getString("email") + " " + request.getParameter("email") +
                			"\n" + RS.getString("password") + " " + request.getParameter("password") );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}


	private static String[] getValues(HttpServletRequest request) {
		return new String[] { request.getParameter("email"), request.getParameter("password"),
                request.getParameter("fname"), request.getParameter("lname"), request.getParameter("artistbio")};
	}
	
	private static void delete(HttpServletRequest request, HttpServletResponse response) {
		DBConnectStatic.DeleteFromDB(TableName,"email", request.getParameter("email"));	
	}

	private static void update(HttpServletRequest request, HttpServletResponse response) {
        DBConnectStatic.UpdateDB(TableName,Columns,getValues(request));
		String artistEmail = request.getParameter("email"); // Get email
		Artist updatedArtist = getOne(artistEmail); // Fetch updated artist details
		request.getSession().setAttribute("artist", updatedArtist); // Update session
		request.getSession().setAttribute("updateSuccess", true);
        }
	
	private static void create(HttpServletRequest request, HttpServletResponse response) {
		DBConnectStatic.Add2DB(TableName,Columns,getValues(request));
	}
	
	public static List<Artist> getAll() {
		ResultSet RS =  DBConnectStatic.ReadFromDB(TableName);
		List<Artist> artists = new ArrayList<Artist>();
		try {
			while (RS.next()) {
				artists.add(new Artist(RS.getString("Email"), RS.getString("Password"), RS.getString("fName"),
						RS.getString("lName"),RS.getString("artistbio")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return artists;
	}
	
	
	public static Artist getOne(String email) {
		ResultSet RS = DBConnectStatic.ReadFromDB(TableName, "Email", email);
		Artist artist = null;
		try {
			while (RS.next()) {
				artist = new Artist(RS.getString("Email"), RS.getString("Password"), RS.getString("fName"),
						RS.getString("lName"), RS.getString("artistbio"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return artist;
	}

}
