package com.diegobonnin.ipoo.cajeroAutomatico.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/inicio")
public class Inicio extends HttpServlet {
	

    public Inicio() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion=request.getSession();
		
		if(sesion!=null){
			if(sesion.isNew()){
				response.sendRedirect("login");
			}else{
				request.getRequestDispatcher("inicio.jsp").forward(request, response);
			}
		}else{
			request.getRequestDispatcher("inicio.jsp").forward(request, response);
		}
		
	}

}
