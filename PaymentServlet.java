package com.mstore.payment;

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
import com.mstore.order.Order;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static String TableName = "payment";
	private static String[] Columns = {"paymentid", "user", "orders", "date", "totalprice", "status", "paymentmethod" };

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
		case "updatemethod":
			updatemethod(request, response);
			break;
		}
		response.sendRedirect(redirect);
	}
	
	
	private void updatemethod(HttpServletRequest request, HttpServletResponse response) {
		String[] columnsToUpdate = {"paymentid", "paymentmethod", "status"};
		String[] valuesToUpdate = {request.getParameter("paymentid") ,request.getParameter("paymentmethod"), request.getParameter("status")};
        DBConnectStatic.UpdateDB(TableName, columnsToUpdate, valuesToUpdate);     
	}


	private static String[] getValues(HttpServletRequest request) {
		return new String[] { request.getParameter("paymentid"), request.getParameter("user"),
				request.getParameter("orders"), request.getParameter("date"), request.getParameter("totalprice"), request.getParameter("status"), request.getParameter("paymentMethod") };
	}
	
	private static void delete(HttpServletRequest request, HttpServletResponse response) {
		DBConnectStatic.DeleteFromDB(TableName,"paymentid", request.getParameter("paymentid"));	
	}

	private static void update(HttpServletRequest request, HttpServletResponse response) {
	        DBConnectStatic.UpdateDB(TableName, Columns, getValues(request));}

	private static void create(HttpServletRequest request, HttpServletResponse response) {
		DBConnectStatic.Add2DBWithoutPK(TableName,Columns,getValues(request));
		delete("user", request.getParameter("user"));
	}
	
	private static void delete(String column ,String value) {
		DBConnectStatic.DeleteFromDB("mstore.order", column, value);
	}
	
	public static List<Payment> getAll() {
		String buffer;
		List<String> Orders = new ArrayList<String>();
		String[] OrdersArr;
		ResultSet RS =  DBConnectStatic.ReadFromDB(TableName);
		List<Payment> Payments = new ArrayList<Payment>();
		try {
			while (RS.next()) {
				buffer = RS.getString("orders");
				for (String retval : buffer.split(";")) {
					Orders.add(retval);
				}
				OrdersArr = Orders.toArray(new String[Orders.size()]);
				Payments.add(new Payment(RS.getString("paymentid"), RS.getString("user"), RS.getString("orders"), RS.getString("date"), RS.getString("totalprice"), RS.getString("status"), RS.getString("paymentmethod")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Payments;
	}

	public static Payment getOne(String paymentid) {
		String buffer;
		List<String> Orders = new ArrayList<String>();
		String[] OrdersArr;
		ResultSet RS = DBConnectStatic.ReadFromDB(TableName, "paymentid", paymentid);
		Payment payment = null;
		try {
			while (RS.next()) {
				payment = new Payment(RS.getString("paymentid"), RS.getString("user"), RS.getString("orders"), RS.getString("date"), RS.getString("totalprice"), RS.getString("status"), RS.getString("paymentmethod"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return payment;
	}
}
