package com.diegobonnin.ipoo.cajeroAutomatico.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.diegobonnin.ipoo.cajeroAutomatico.Cajero;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Acceso;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.PagoPrestamo;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Prestamo;

@WebServlet("/pagoPrestamo")
public class PagosPrestamos extends HttpServlet {
	
	private Cajero cajero;

    public PagosPrestamos() {
        super();
        cajero=new Cajero();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion=request.getSession();
		
		Acceso a=(Acceso)sesion.getAttribute("acceso");
		
		if(a!=null){
			
			List<Prestamo> prestamos=cajero.obtPrestamos(a.getCliente());
			request.setAttribute("prestamos", prestamos);
			request.getRequestDispatcher("pagoPrestamo.jsp").forward(request, response);
			
		}else{
			response.sendRedirect("logout");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nroPrestamo=request.getParameter("nroPrestamo");
		String importe=request.getParameter("importe");
		
		HttpSession sesion=request.getSession();
		
		Acceso a=(Acceso)sesion.getAttribute("acceso");
		
		if(a!=null){
		
			Double _importe=Double.parseDouble(importe);		
			PagoPrestamo t=cajero.pagoPrestamo(a, nroPrestamo, _importe);
						
			response.sendRedirect("operacion?nroOperacion=" + t.getNroOperacion());
			
		}else{
			
			sesion.invalidate();			
			response.sendRedirect("inicio");
			
		}
		
	}	

	

}
