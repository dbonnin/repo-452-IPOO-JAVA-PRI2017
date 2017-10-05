package com.diegobonnin.ipoo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.diegobonnin.ipoo.repuestos.datos.Acceso;
import com.diegobonnin.ipoo.respuestos.Repuestos;

@WebServlet("/login")
public class Login extends HttpServlet {
	
	private Repuestos repuestos;

    public Login() {
        super();
        repuestos=new Repuestos();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tipoDoc=request.getParameter("tipoDoc");
		String nroDoc=request.getParameter("nroDoc");
		String password=request.getParameter("password");
		
		Acceso acceso=repuestos.autenticar(tipoDoc, nroDoc, password, request.getRemoteAddr());
		
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
