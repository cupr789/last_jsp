package com.last.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.last.jsp.factory.ServiceFactory;
import com.last.jsp.service.MenuService;
import com.last.jsp.service.UserService;
import com.last.jsp.service.impl.MenuServiceImpl;
import com.last.jsp.util.URIParser;


@WebServlet("/view/*")
public class JspServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ServiceFactory sf = ServiceFactory.getInstance();
	
    private static final Logger log = Logger.getLogger(JspServlet.class);
    public JspServlet() {
        super();
    }
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doProcess(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doProcess(req, res);
	}
	
	public void doProcess(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String uri = req.getRequestURI();
		log.debug(uri);
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF"+uri+".jsp");
		if(req.getServletContext().getAttribute("menuList")==null) {			
			MenuService ms = (MenuService)sf.getService("menu");
			//MenuService ms = new MenuServiceImpl();
			ms.getMenuList(req);
		}
		String command = URIParser.getCommand(uri, 1);
		log.debug(command);
		if(command.equals("list")) {
			uri = uri.replaceAll("/"+command, "");
			command = URIParser.getCommand(uri, 1);
			UserService s = (UserService)sf.getService("user");
			s.getUserList(req);
			log.debug(command);
			log.debug(uri);
		}
		rd.forward(req, res);
	}
}
