package com.diegobonnin.ipoo.cajeroAutomatico;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.diegobonnin.ipoo.cajeroAutomatico.datos.Acceso;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Cliente;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Cuenta;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.CuentaCorriente;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.CuentaDeAhorro;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Moneda;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Operacion;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.ResultadoOperacion;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Transferencia;

public class SistemaBancoJDBC implements SistemaBanco {

	private Conexion conexion;
	
	public SistemaBancoJDBC(){
		
		conexion=new Conexion(
				"jdbc:postgresql://localhost:5432/cajero", // url 
				"org.postgresql.Driver", // clase del driver
				"postgres", // usuario
				"postgres" // contraseña
		);
		
	}
	
	@Override
	public Cliente obtCliente(String tipoDoc, String nroDoc, String password) throws SistemaBancoException {
		
		Cliente c=null;
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			
			conexion.conectar();
			
			ps=conexion.getCon().prepareStatement("select * from clientes where tipo_doc=? and nro_doc=? and password=? ");
			ps.setString(1, tipoDoc);
			ps.setString(2, nroDoc);
			ps.setString(3, password);
			
			rs=ps.executeQuery();
			
			
			if(rs.next()){
				c=new Cliente();
				c.setId(rs.getLong("id_cliente"));
				c.setNroDoc(rs.getString("nro_doc"));
				c.setTipoDoc(rs.getString("tipo_doc"));
				c.setNombre(rs.getString("nombre"));
				c.setDireccion(rs.getString("direccion"));
				c.setTelefono(rs.getString("telefono"));
				c.setFechaNacimiento(new Date(rs.getDate("fecha_nacimiento").getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		} catch (DBException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		}finally{
			
				try {
					if(rs!=null)rs.close();
					if(ps!=null) ps.close();
					conexion.desconectar();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				} catch (DBException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				}
	
		}
		
		return c;
		
	}

	@Override
	public Acceso crearAcceso(Cliente cliente, Cajero cajero) throws SistemaBancoException{
		
		LocalDateTime fechaHoraInicio=LocalDateTime.now();
		Acceso a=new Acceso(fechaHoraInicio, cliente, cajero);
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			
			conexion.conectar();
			
			ps=conexion.getCon().prepareStatement("insert into accesos(fecha_hora_inicio, id_cliente, id_cajero) values (?,?,?)");
			ps.setTimestamp(1, Timestamp.valueOf(a.getFechaHoraInicio()));
			ps.setLong(2, a.getCliente().getId());
			ps.setLong(3, a.getCajero().getId());
			
			int filasInsertadas=ps.executeUpdate();
			
			if(filasInsertadas==0){
				a=null;
				throw new SistemaBancoException("No se pudo insertar el acceso");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		} catch (DBException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		}finally{
			
				try {
					if(rs!=null)rs.close();
					if(ps!=null) ps.close();
					conexion.desconectar();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				} catch (DBException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				}
	
		}
		
		return a;		
		
	}

	@Override
	public Acceso obtAcceso(Cliente cliente)throws SistemaBancoException {
		
		Acceso a=null;
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			
			conexion.conectar();
			
			ps=conexion.getCon().prepareStatement("select * from accesos where id_cliente=? order by fecha_hora_inicio desc");
			ps.setLong(1, cliente.getId());
			
			rs=ps.executeQuery();
			
			
			if(rs.next()){
				
				a=new Acceso();
				a.setFechaHoraInicio(rs.getTimestamp("fecha_hora_inicio").toLocalDateTime());
				a.setIp(rs.getString("ip"));
				a.setFechaHoraFin(rs.getTimestamp("fecha_hora_fin").toLocalDateTime());
				a.setCliente(cliente);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		} catch (DBException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		}finally{
			
				try {
					if(rs!=null)rs.close();
					if(ps!=null) ps.close();
					conexion.desconectar();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				} catch (DBException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				}
	
		}
		
		return a;
	}

	@Override
	public Cuenta obtCuenta(String nroDeCuenta) throws SistemaBancoException {
		
		Cuenta c=null;
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			
			conexion.conectar();
			
			ps=conexion.getCon().prepareStatement("select * from cuentas where nro_cuenta=? ");
			ps.setString(1, nroDeCuenta);
			
			rs=ps.executeQuery();
			
			
			if(rs.next()){
				
				String tipo=rs.getString("tipo");
				if("AH".equals(tipo)){
					c=new CuentaDeAhorro();
				}else{
					c=new CuentaCorriente();
				}
				c.setDenominacion(rs.getString("denominacion"));
				Moneda m=new Moneda();
				m.setId(rs.getInt("id_moneda"));
				m.setNombre(rs.getString("nombre_moneda"));
				c.setMoneda(m);
				c.setSaldoAConfirmar(rs.getDouble("saldo_a_confirmar"));
				c.setSaldoDisponible(rs.getDouble("saldo_disponible"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		} catch (DBException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		}finally{
			
				try {
					if(rs!=null)rs.close();
					if(ps!=null) ps.close();
					conexion.desconectar();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				} catch (DBException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				}
	
		}
		
		return c;
	}
	
	@Override
	public List<Cuenta> obtCuentas(Cliente cliente) throws SistemaBancoException {
		
		List<Cuenta> cuentas=new ArrayList<>();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			
			conexion.conectar();
			
			StringBuilder sb=new StringBuilder();
			sb.append("select c.*, m.nombre as nombre_moneda from cuentas c ");
			sb.append("inner join cuentas_clientes cc on c.nro_cuenta=cc.nro_cuenta "); 
			sb.append("inner join monedas m on c.id_moneda=m.id_moneda ");
			sb.append("where cc.id_cliente=?");
			
			ps=conexion.getCon().prepareStatement(sb.toString());
			ps.setLong(1, cliente.getId());
			
			rs=ps.executeQuery();
			
			
			while(rs.next()){
				
				Cuenta c=null;
				
				String tipo=rs.getString("tipo");
				if("AH".equals(tipo)){
					c=new CuentaDeAhorro();
				}else{
					c=new CuentaCorriente();
				}
				c.setDenominacion(rs.getString("denominacion"));
				Moneda m=new Moneda();
				m.setId(rs.getInt("id_moneda"));
				m.setNombre(rs.getString("nombre_moneda"));
				c.setMoneda(m);
				c.setSaldoAConfirmar(rs.getDouble("saldo_a_confirmar"));
				c.setSaldoDisponible(rs.getDouble("saldo_disponible"));
				
				cuentas.add(c);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		} catch (DBException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		}finally{
			
				try {
					if(rs!=null)rs.close();
					if(ps!=null) ps.close();
					conexion.desconectar();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				} catch (DBException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				}
	
		}
		
		return cuentas;
		
	}	
	
	public ResultadoOperacion registrarOperacion(Transferencia transferencia) throws SistemaBancoException  {
		
		ResultadoOperacion ro=new ResultadoOperacion();
		String estado=null;
		String mensaje=null;
		
		PreparedStatement psOperacion=null;
		PreparedStatement psTransaccion=null;
		PreparedStatement psTransferencia=null;
		
		boolean registrada=false;
		
		try{
			
			conexion.conectar();
			
			psOperacion=conexion.getCon().prepareStatement("insert into operaciones (fecha_hora, imagen, id_acceso) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
			psTransaccion=conexion.getCon().prepareStatement("insert into transacciones (nro_operacion, importe, id_moneda) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
			psTransferencia=conexion.getCon().prepareStatement("insert into transferencias (nro_operacion, nro_cuenta_origen, nro_cuenta_destino) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			psOperacion.setTimestamp(1, Timestamp.valueOf(transferencia.getFechaHora()));
			psOperacion.setBytes(2, transferencia.getImagen());
			psOperacion.setLong(3, transferencia.getAcceso().getId());
			
			int cantFilasIntertadas=0;
			
			cantFilasIntertadas=psOperacion.executeUpdate();
			
			if(cantFilasIntertadas > 0){
				
				transferencia.setNroOperacion(psOperacion.getGeneratedKeys().getLong(1));
				
				psTransaccion.setLong(1, transferencia.getNroOperacion());
				psTransaccion.setDouble(1, transferencia.getImporte());
				psTransaccion.setInt(1, transferencia.getMoneda().getId());
				
				cantFilasIntertadas=psTransaccion.executeUpdate();
				
				if(cantFilasIntertadas > 0){
					
					psTransferencia.setLong(1, transferencia.getNroOperacion());
					psTransferencia.setString(2, transferencia.getCuenta().getNroDeCuenta());
					psTransferencia.setString(3, transferencia.getCuentaDestino().getNroDeCuenta());
					
					cantFilasIntertadas=psTransferencia.executeUpdate();
					
					if(cantFilasIntertadas > 0){
						registrada=true;
					}
					
				}
				
			}
						
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		} catch (DBException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		}finally{
			
				try {
					if(psOperacion!=null) psOperacion.close();
					if(psTransaccion!=null) psTransaccion.close();
					if(psTransferencia!=null) psTransferencia.close();
					conexion.desconectar();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				} catch (DBException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				}
	
		}
		
		if(registrada){
			ro.setEstado("OK");
			ro.setMensaje("Operación registrada");
		}else{
			ro.setEstado("ERROR");
			ro.setMensaje("Operación no registrada");
		}
		
		return ro;
		
	}	
	
	public void finalizarSesion(Acceso acceso) throws SistemaBancoException{
		
		LocalDateTime fechaHoraFin=LocalDateTime.now();
		acceso.setFechaHoraFin(fechaHoraFin);
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			
			conexion.conectar();
			
			ps=conexion.getCon().prepareStatement("update accesos set fecha_hora_fin=? where id_acceso=?");
			ps.setTimestamp(1, Timestamp.valueOf(acceso.getFechaHoraFin()));
			ps.setLong(2, acceso.getId());
			
			int filasActualizadas=ps.executeUpdate();
			
			if(filasActualizadas==0){
				throw new SistemaBancoException("No se pudo registrar el fin de la sesión");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		} catch (DBException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		}finally{
			
				try {
					if(rs!=null)rs.close();
					if(ps!=null) ps.close();
					conexion.desconectar();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				} catch (DBException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				}
	
		}
		
		
	}

	public Operacion obtOperacion(Long nroOperacion) {
		
		String tipo=null;
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			
			conexion.conectar();
			
			ps=conexion.getCon().prepareStatement("select tipo from operaciones where nro_operacion=? ");
			ps.setLong(1, nroOperacion);
			
			rs=ps.executeQuery();
			
			if(rs.next()){
				
				tipo=rs.getString("tipo");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		} catch (DBException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		}finally{
			
				try {
					if(rs!=null)rs.close();
					if(ps!=null) ps.close();
					conexion.desconectar();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				} catch (DBException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				}
	
		}
		
		if("TRANSFERENCIA".equals(tipo)){
			return this.obtTransferencia(nroOperacion);
		}
		
		return null;

	}

	private Transferencia obtTransferencia(Long nroOperacion) {
		Cuenta c=null;
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			
			conexion.conectar();
			
			StringBuilder sb=new StringBuilder();
			sb.append("select o.*, t.*, tra.*, m.* from "); 
			sb.append("operaciones o  ");
			sb.append("inner join transacciones t on o.nro_operacion=t.nro_operacion "); 
			sb.append("inner join transferencias tra on t.nro_operacion=tra.nro_operacion "); 
			sb.append("inner join monedas m on t.id_moneda=m.id_moneda "); 
			sb.append("where o.nro_operacion=?");

			
			ps=conexion.getCon().prepareStatement(sb.toString());
			ps.setLong(1, nroOperacion);
			
			rs=ps.executeQuery();
			
			if(rs.next()){
				
				Transferencia t=new Transferencia();
				
				Cuenta origen=null;
				
				String tipo=rs.getString("tipo");
				if("AH".equals(tipo)){
					origen=new CuentaDeAhorro();
				}else{
					origen=new CuentaCorriente();
				}
				origen.setDenominacion(rs.getString("denominacion"));
				Moneda m=new Moneda();
				m.setId(rs.getInt("id_moneda"));
				m.setNombre(rs.getString("nombre_moneda"));
				origen.setMoneda(m);
				origen.setSaldoAConfirmar(rs.getDouble("saldo_a_confirmar"));
				origen.setSaldoDisponible(rs.getDouble("saldo_disponible"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		} catch (DBException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		}finally{
			
				try {
					if(rs!=null)rs.close();
					if(ps!=null) ps.close();
					conexion.desconectar();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				} catch (DBException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				}
	
		}
		
		return c;
	}


}
