package com.diegobonnin.ipoo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.diegobonnin.ipoo.respuestos.Repuestos;

@WebServlet("/logout")
public class Logout extends HttpServlet {
	
	private Repuestos repuestos;

    public Logout() {
        super();
        repuestos=new Repuestos();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession sesion=request.getSession();
		sesion.invalidate();
		
		response.sendRedirect("inicio");
				
		
	}


}
