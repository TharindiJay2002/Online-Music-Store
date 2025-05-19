package com.mstore.order;

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

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static String TableName = "mstore.order";
	private static String[] Columns = {"orderid", "user", "song", "date", "price"};

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
		case "updateTitle":
		    updateTitle(request, response);
            break;
		}
		
		response.sendRedirect(redirect);
	}
	
	
	private void updateTitle(HttpServletRequest request, HttpServletResponse response) {
		String[] columns = {"orderid" , "song"};
		String[] values = {request.getParameter("orderid"), request.getParameter("newTitle")};
		DBConnectStatic.UpdateDB(TableName, columns, values);
	}


	private static String[] getValues(HttpServletRequest request) {
		System.out.println(request.getParameter("orderid"));
		return new String[] { request.getParameter("orderid"), request.getParameter("user"),
                request.getParameter("song"), request.getParameter("date"), request.getParameter("price")
        };

	}
	
	private static void delete(HttpServletRequest request, HttpServletResponse response) {
		DBConnectStatic.DeleteFromDB(TableName, "orderid", request.getParameter("orderid"));	
	}

	private static void update(HttpServletRequest request, HttpServletResponse response) {
        DBConnectStatic.UpdateDB(TableName, Columns, getValues(request));
	}

	private static void create(HttpServletRequest request, HttpServletResponse response) {
		DBConnectStatic.Add2DBWithoutPK(TableName,Columns,getValues(request));
	}



	public static List<Order> getAll() {
		ResultSet RS =  DBConnectStatic.ReadFromDB(TableName);
		List<Order> orders = new ArrayList<Order>();
		try {
			while (RS.next()) {
				orders.add(new Order(RS.getString("orderid"), RS.getString("User"), RS.getString("Song"),
                        RS.getString("Date"), RS.getDouble("Price")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public static Order getOne(String orderid) {
		ResultSet RS = DBConnectStatic.ReadFromDB(TableName, "orderid", orderid);
		Order order = null;
		try {
			while (RS.next()) {
				order = new Order(RS.getString("orderid"), RS.getString("User"), RS.getString("Song"), RS.getString("Date"), RS.getDouble("Price"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

}
