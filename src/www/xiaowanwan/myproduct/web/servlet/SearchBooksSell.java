package www.xiaowanwan.myproduct.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import www.xiaowanwan.myproduct.service.OrderService;
import www.xiaowanwan.myproduct.util.DateHelper;

public class SearchBooksSell extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String year = request.getParameter("year");
		String mouth = request.getParameter("month");
		
		Date date = DateHelper.getSQLDate(Integer.parseInt(year), Integer.parseInt(mouth), 1);
		OrderService os = new OrderService();
		Map<String, Integer> booksSell = os.searchBooksSell(date);
		
		
		
	
		//×ª·¢
		request.setAttribute("sell", booksSell);
		request.getRequestDispatcher("/admin/products/download.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
