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

@WebServlet("/login")
public class Login extends HttpServlet {
	
	private Cajero cajero;

    public Login() {
        super();
        cajero=new Cajero();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tipoDoc=request.getParameter("tipoDoc");
		String nroDoc=request.getParameter("nroDoc");
		String password=request.getParameter("password");
		
		Acceso acceso=cajero.autenticar(tipoDoc, nroDoc, password, request.getRemoteAddr());
		
		if(acceso!=null){
			HttpSession sesion=request.getSession();
			sesion.setAttribute("acceso", acceso);
			response.sendRedirect("inicio");
		}else{
			request.setAttribute("mensaje", "Usuario inexistente o contraseña incorrecta");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
		
		
		
	}	

	

}
