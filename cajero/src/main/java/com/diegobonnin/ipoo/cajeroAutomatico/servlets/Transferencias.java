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
import com.diegobonnin.cajeroAutomatico.datos.Transferencia;

@WebServlet("/transferencia")
public class Transferencias extends HttpServlet {
	
	private Cajero cajero;

    public Transferencias() {
        super();
        cajero=new Cajero();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("transferencia.jsp").forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nroCuentaOrigen=request.getParameter("cuentaOrigen");
		String nroCuentaDestino=request.getParameter("cuentaDestino");
		String importe=request.getParameter("importe");
		
		HttpSession sesion=request.getSession();
		
		Acceso a=(Acceso)sesion.getAttribute("acceso");
		
		if(a!=null){
		
			Double _importe=Double.parseDouble(importe);		
			Transferencia t=cajero.transferencia(a, nroCuentaOrigen, nroCuentaDestino, _importe);
			request.setAttribute("transferencia", t);
			
		}else{
			
			sesion.invalidate();			
			response.sendRedirect("inicio");
			
		}
		
	}	

	

}
