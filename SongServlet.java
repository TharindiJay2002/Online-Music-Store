package com.mstore.song;

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

@WebServlet("/SongServlet")
public class SongServlet extends HttpServlet {
	private static String TableName = "song";
	private static String[] Columns = {"title", "artist", "album", "genre", "releaseddate", "duration", "link", "price"};
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String redirect = request.getParameter("redirect");
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
		}
		response.sendRedirect(redirect);
	}
	
	
	private static String[] getValues(HttpServletRequest request) {
		return new String[] { request.getParameter("title"), request.getParameter("artist"),
                request.getParameter("album"), request.getParameter("genre"), request.getParameter("releasedate"),
                request.getParameter("duration"), request.getParameter("link"), request.getParameter("price") };
	}
	
	private static void delete(HttpServletRequest request, HttpServletResponse response) {
		DBConnectStatic.DeleteFromDB(TableName, "Title",  request.getParameter("title"));	
	}

	private static void update(HttpServletRequest request, HttpServletResponse response) {
        DBConnectStatic.UpdateDB(TableName, Columns, getValues(request));}

	private static void create(HttpServletRequest request, HttpServletResponse response) {
		DBConnectStatic.Add2DB(TableName,Columns,getValues(request));
	}
	
	public static List<Song> getAll() {
		ResultSet RS =  DBConnectStatic.ReadFromDB(TableName);
		List<Song> songs = new ArrayList<Song>();
		try {
			while (RS.next()) {
				songs.add(new Song(RS.getString("Title"), RS.getString("Artist"), RS.getString("Album"),
						RS.getString("Genre"), RS.getString("releasedDate"), RS.getString("duration"), RS.getString("link"), RS.getString("price")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return songs;
	}
	
	public static Song getOne(String title) {
		ResultSet RS = DBConnectStatic.ReadFromDB(TableName, "title", title);
		Song song = null;
		try {
			while (RS.next()) {
				song = new Song(RS.getString("Title"), RS.getString("Artist"), RS.getString("Album"),
						RS.getString("Genre"), RS.getString("releasedDate"), RS.getString("duration"), RS.getString("link"), RS.getString("price"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return song;
	}

}
