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
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Cuenta;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Transferencia;

@WebServlet("/transferencia")
public class Transferencias extends HttpServlet {
	
	private Cajero cajero;

    public Transferencias() {
        super();
        cajero=new Cajero();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion=request.getSession();
		
		Acceso a=(Acceso)sesion.getAttribute("acceso");
		
		if(a!=null){
			
			List<Cuenta> cuentas=cajero.obtCuentas(a.getCliente());
			request.setAttribute("cuentas", cuentas);
			request.getRequestDispatcher("transferencia.jsp").forward(request, response);
			
		}else{
			response.sendRedirect("logout");
		}
		
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
			
			response.sendRedirect("operacion?nroOperacion=" + t.getNroOperacion());
			
		}else{
			
			sesion.invalidate();			
			response.sendRedirect("inicio");
			
		}
		
	}	

	

}
