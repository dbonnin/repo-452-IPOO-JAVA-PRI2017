package com.diegobonnin.ipoo.cajeroAutomatico.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.diegobonnin.cajeroAutomatico.Cajero;
import com.diegobonnin.cajeroAutomatico.datos.Acceso;

@WebServlet("/logout")
public class Logout extends HttpServlet {
	
	private Cajero cajero;

    public Logout() {
        super();
        cajero=new Cajero();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion=request.getSession();
		Acceso a=(Acceso)sesion.getAttribute("acceso");
		
		if(a!=null) cajero.finalizarSesion(a);
		
		sesion.invalidate();
		
		response.sendRedirect("inicio");
				
		
	}


}
