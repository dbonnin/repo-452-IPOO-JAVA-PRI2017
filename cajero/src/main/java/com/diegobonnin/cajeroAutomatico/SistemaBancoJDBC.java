package com.diegobonnin.cajeroAutomatico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.diegobonnin.cajeroAutomatico.datos.Acceso;
import com.diegobonnin.cajeroAutomatico.datos.Cliente;
import com.diegobonnin.cajeroAutomatico.datos.Cuenta;
import com.diegobonnin.cajeroAutomatico.datos.CuentaCorriente;
import com.diegobonnin.cajeroAutomatico.datos.CuentaDeAhorro;
import com.diegobonnin.cajeroAutomatico.datos.Moneda;
import com.diegobonnin.cajeroAutomatico.datos.ResultadoOperacion;
import com.diegobonnin.cajeroAutomatico.datos.Transferencia;

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
				System.out.print(rs.getString(1) + " ");
				System.out.print(rs.getString(2));
				System.out.println();
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
	public Acceso crearAcceso(Cliente cliente, String ip) throws SistemaBancoException{
		
		LocalDateTime fechaHoraInicio=LocalDateTime.now();
		Acceso a=new Acceso(fechaHoraInicio, ip, cliente);
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			
			conexion.conectar();
			
			ps=conexion.getCon().prepareStatement("insert into accesos(fecha_hora_inicio, id_cliente, ip) values (?,?,?)");
			ps.setTimestamp(1, Timestamp.valueOf(a.getFechaHoraFin()));
			ps.setLong(2, a.getCliente().getId());
			ps.setString(3, ip);
			
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
			
			ps=conexion.getCon().prepareStatement("select * from cuentas where id_cliente=? ");
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
			
			psOperacion=conexion.getCon().prepareStatement("insert into operaciones (fecha_hora, imagen, id_cajero, id_acceso) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			psTransaccion=conexion.getCon().prepareStatement("insert into transacciones (nro_operacion, importe, id_moneda) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
			psTransferencia=conexion.getCon().prepareStatement("insert into transferencias (nro_operacion, nro_cuenta_origen, nro_cuenta_destino) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			psOperacion.setTimestamp(1, Timestamp.valueOf(transferencia.getFechaHora()));
			psOperacion.setBytes(2, transferencia.getImagen());
			psOperacion.setLong(3, transferencia.getCajero().getId());
			psOperacion.setLong(4, transferencia.getAcceso().getId());
			
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


}
