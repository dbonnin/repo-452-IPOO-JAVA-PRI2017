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
import com.diegobonnin.ipoo.cajeroAutomatico.datos.MovimientoCuenta;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Operacion;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.PagoTarjeta;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.ResultadoOperacion;
import com.diegobonnin.ipoo.cajeroAutomatico.datos.Tarjeta;
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
	public Cliente obtCliente(Long id) throws SistemaBancoException {
		
		Cliente c=null;
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			
			conexion.conectar();
			
			ps=conexion.getCon().prepareStatement("select * from clientes where id_cliente=?");
			ps.setLong(1, id);
			
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
			
			ps=conexion.getCon().prepareStatement("insert into accesos(fecha_hora_inicio, id_cliente, id_cajero) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setTimestamp(1, Timestamp.valueOf(a.getFechaHoraInicio()));
			ps.setLong(2, a.getCliente().getId());
			ps.setLong(3, a.getCajero().getId());
			
			int filasInsertadas=ps.executeUpdate();
			
			if(filasInsertadas==0){
				a=null;
				throw new SistemaBancoException("No se pudo insertar el acceso");
			}else{
				rs=ps.getGeneratedKeys();
				if(rs.next()) a.setId(ps.getGeneratedKeys().getLong(1));
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
				Timestamp t=rs.getTimestamp("fecha_hora_fin");
				if(!rs.wasNull()) a.setFechaHoraFin(t.toLocalDateTime());
				Cliente c=new Cliente();
				c.setId(rs.getLong("id_cliente"));
				
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
	public Acceso obtAcceso(Long id)throws SistemaBancoException {
		
		Acceso a=null;
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			
			conexion.conectar();
			
			ps=conexion.getCon().prepareStatement("select * from accesos where id_acceso=? ");
			ps.setLong(1, id);
			
			rs=ps.executeQuery();
			
			
			if(rs.next()){
				
				a=new Acceso();
				a.setFechaHoraInicio(rs.getTimestamp("fecha_hora_inicio").toLocalDateTime());
				Timestamp t=rs.getTimestamp("fecha_hora_fin");
				if(!rs.wasNull()) a.setFechaHoraFin(t.toLocalDateTime());
				Cliente c=new Cliente();
				c.setId(rs.getLong("id_cliente"));
				a.setCliente(c);
				
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
		if(a!=null) a.setCliente(this.obtCliente(a.getCliente().getId()));
		
		return a;
	}
	

	@Override
	public Cuenta obtCuenta(String nroDeCuenta) throws SistemaBancoException {
		
		Cuenta c=null;
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			
			conexion.conectar();
			
			StringBuilder sb=new StringBuilder();
			sb.append("select c.*, m.nombre as nombre_moneda from cuentas c ");
			sb.append("inner join monedas m on c.id_moneda=m.id_moneda ");
			sb.append("where c.nro_cuenta=?");
			
			ps=conexion.getCon().prepareStatement(sb.toString());
			ps.setString(1, nroDeCuenta);
			
			rs=ps.executeQuery();
			
			
			if(rs.next()){
				
				String tipo=rs.getString("tipo");
				if("AH".equals(tipo)){
					c=new CuentaDeAhorro();
				}else{
					c=new CuentaCorriente();
				}
				c.setNroDeCuenta(rs.getString("nro_cuenta"));
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
				c.setNroDeCuenta(rs.getString("nro_cuenta"));
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
	
	@Override
	public List<Tarjeta> obtTarjetas(Cliente cliente) throws SistemaBancoException {
		
		List<Tarjeta> tarjetas=new ArrayList<>();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			
			conexion.conectar();
			
			StringBuilder sb=new StringBuilder();
			sb.append("select t.*, m.nombre as nombre_moneda from tarjetas t ");
			sb.append("inner join monedas m on t.id_moneda=m.id_moneda ");
			sb.append("where t.id_cliente=?");
			
			ps=conexion.getCon().prepareStatement(sb.toString());
			ps.setLong(1, cliente.getId());
			
			rs=ps.executeQuery();
			
			
			while(rs.next()){
				
				Tarjeta t=new Tarjeta();
				
				t.setNroTarjeta(rs.getString("nro_tarjeta"));

				t.setLineaDeCredito(rs.getDouble("linea_de_credito"));
				t.setSaldoActual(rs.getDouble("saldo_actual"));
				t.setSaldoDisponible(rs.getDouble("saldo_disponible"));
				
				t.setFechaVencimiento(new Date(
						rs.getDate("fecha_vencimiento").getTime())
						.toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate());
				
				t.setPagoMinimo(rs.getDouble("pago_minimo"));

				Moneda m=new Moneda();
				m.setId(rs.getInt("id_moneda"));
				m.setNombre(rs.getString("nombre_moneda"));
				t.setMoneda(m);
				
				tarjetas.add(t);
				

				
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
		
		return tarjetas;
		
	}	
	
	
	public ResultadoOperacion registrarOperacion(Transferencia transferencia) throws SistemaBancoException  {
		
		ResultadoOperacion ro=new ResultadoOperacion();
		String estado=null;
		String mensaje=null;
		
		PreparedStatement psOperacion=null;
		PreparedStatement psTransaccion=null;
		PreparedStatement psTransferencia=null;
		PreparedStatement psResultado=null;
		
		ResultSet rsOperacion=null;
		
		boolean registrada=false;
		
		try{
			
			conexion.conectar();
			
			psOperacion=conexion.getCon().prepareStatement("insert into operaciones (fecha_hora, id_acceso, tipo) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
			psTransaccion=conexion.getCon().prepareStatement("insert into transacciones (nro_operacion, importe, id_moneda) values (?,?,?)");
			psTransferencia=conexion.getCon().prepareStatement("insert into transferencias (nro_operacion, nro_cuenta_origen, nro_cuenta_destino) values (?,?,?)");
			psResultado=conexion.getCon().prepareStatement("update operaciones set estado=?, mensaje=? where nro_operacion=?");

			
			psOperacion.setTimestamp(1, Timestamp.valueOf(transferencia.getFechaHora()));
			psOperacion.setLong(2, transferencia.getAcceso().getId());
			psOperacion.setString(3, transferencia.getTipo());
			
			int cantFilasIntertadas=0;
			
			cantFilasIntertadas=psOperacion.executeUpdate();
			
			if(cantFilasIntertadas > 0){
				
				rsOperacion=psOperacion.getGeneratedKeys();
				
				if(rsOperacion.next()){
					
					transferencia.setNroOperacion(rsOperacion.getLong(1));
					
					psTransaccion.setLong(1, transferencia.getNroOperacion());
					psTransaccion.setDouble(2, transferencia.getImporte());
					psTransaccion.setInt(3, transferencia.getMoneda().getId());
					
					cantFilasIntertadas=psTransaccion.executeUpdate();
					
					if(cantFilasIntertadas > 0){
						
						psTransferencia.setLong(1, transferencia.getNroOperacion());
						psTransferencia.setString(2, transferencia.getCuenta().getNroDeCuenta());
						psTransferencia.setString(3, transferencia.getCuentaDestino().getNroDeCuenta());
						
						cantFilasIntertadas=psTransferencia.executeUpdate();
						
						if(cantFilasIntertadas > 0){
							registrada=true;
							estado="OK";
							mensaje="Operación registrada";
							psResultado.setString(1, estado);
							psResultado.setString(2, mensaje);
							psResultado.setLong(3, transferencia.getNroOperacion());
							psResultado.executeUpdate();
						}
						
					}else{
						estado="ERROR";
						mensaje="Transacción registrada";
					}

					
				}else{
					estado="ERROR";
					mensaje="Operación registrada";
				}
				
			}
						
			
		} catch (SQLException e) {
			e.printStackTrace();
			estado="ERROR";
			mensaje=e.getMessage();
			throw new SistemaBancoException(e);
		} catch (DBException e) {
			estado="ERROR";
			mensaje=e.getMessage();
			e.printStackTrace();
			throw new SistemaBancoException(e);
		}finally{
			
				try {
					if(rsOperacion!=null) rsOperacion.close();
					if(psOperacion!=null) psOperacion.close();
					if(psTransaccion!=null) psTransaccion.close();
					if(psTransferencia!=null) psTransferencia.close();
					if(psResultado!=null) psResultado.close();
					conexion.desconectar();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				} catch (DBException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				}
	
		}
		
		ro.setEstado(estado);
		ro.setMensaje(mensaje);
		
		return ro;
		
	}
	
	public ResultadoOperacion registrarOperacion(PagoTarjeta pagoTarjeta) throws SistemaBancoException  {
		
		ResultadoOperacion ro=new ResultadoOperacion();
		String estado=null;
		String mensaje=null;
		
		PreparedStatement psOperacion=null;
		PreparedStatement psTransaccion=null;
		PreparedStatement psPagoTarjeta=null;
		PreparedStatement psResultado=null;
		
		ResultSet rsOperacion=null;
		
		boolean registrada=false;
		
		try{
			
			conexion.conectar();
			
			psOperacion=conexion.getCon().prepareStatement("insert into operaciones (fecha_hora, id_acceso, tipo) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
			psTransaccion=conexion.getCon().prepareStatement("insert into transacciones (nro_operacion, importe, id_moneda) values (?,?,?)");
			psPagoTarjeta=conexion.getCon().prepareStatement("insert into pagos_tarjetas (nro_operacion, nro_tarjeta) values (?,?)");
			psResultado=conexion.getCon().prepareStatement("update operaciones set estado=?, mensaje=? where nro_operacion=?");
			
			psOperacion.setTimestamp(1, Timestamp.valueOf(pagoTarjeta.getFechaHora()));
			psOperacion.setLong(2, pagoTarjeta.getAcceso().getId());
			psOperacion.setString(3, pagoTarjeta.getTipo());
			
			int cantFilasIntertadas=0;
			
			cantFilasIntertadas=psOperacion.executeUpdate();
			
			if(cantFilasIntertadas > 0){
				
				rsOperacion=psOperacion.getGeneratedKeys();
				
				if(rsOperacion.next()){
					
					pagoTarjeta.setNroOperacion(rsOperacion.getLong(1));
					
					psTransaccion.setLong(1, pagoTarjeta.getNroOperacion());
					psTransaccion.setDouble(2, pagoTarjeta.getImporte());
					psTransaccion.setInt(3, pagoTarjeta.getMoneda().getId());
					
					cantFilasIntertadas=psTransaccion.executeUpdate();
					
					if(cantFilasIntertadas > 0){
						
						psPagoTarjeta.setLong(1, pagoTarjeta.getNroOperacion());
						psPagoTarjeta.setString(2, pagoTarjeta.getTarjeta().getNroTarjeta());
						
						cantFilasIntertadas=psPagoTarjeta.executeUpdate();
						
						if(cantFilasIntertadas > 0){
							registrada=true;
							estado="OK";
							mensaje="Operación registrada";
							psResultado.setString(1, estado);
							psResultado.setString(2, mensaje);
							psResultado.setLong(3, pagoTarjeta.getNroOperacion());
							psResultado.executeUpdate();
						}
						
					}else{
						estado="ERROR";
						mensaje="Transacción registrada";
					}

					
				}else{
					estado="ERROR";
					mensaje="Operación registrada";
				}
				
			}
						
			
		} catch (SQLException e) {
			e.printStackTrace();
			estado="ERROR";
			mensaje=e.getMessage();
			throw new SistemaBancoException(e);
		} catch (DBException e) {
			estado="ERROR";
			mensaje=e.getMessage();
			e.printStackTrace();
			throw new SistemaBancoException(e);
		}finally{
			
				try {
					if(rsOperacion!=null) rsOperacion.close();
					if(psOperacion!=null) psOperacion.close();
					if(psTransaccion!=null) psTransaccion.close();
					if(psPagoTarjeta!=null) psPagoTarjeta.close();
					if(psResultado!=null) psResultado.close();
					conexion.desconectar();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				} catch (DBException e) {
					e.printStackTrace();
					throw new SistemaBancoException(e);
				}
	
		}
		
		ro.setEstado(estado);
		ro.setMensaje(mensaje);
		
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

	public Operacion obtOperacion(Long nroOperacion) throws SistemaBancoException {
		
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

	private Transferencia obtTransferencia(Long nroOperacion) throws SistemaBancoException {
		
		Transferencia t=null;
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		
		String nroCuentaOrigen=null;
		String nroCuentaDestino=null;
		
		try{
			
			conexion.conectar();
			
			StringBuilder sb=new StringBuilder();
			sb.append("select o.*, t.*, tra.*, m.nombre as nombre_moneda from "); 
			sb.append("operaciones o  ");
			sb.append("inner join transacciones t on o.nro_operacion=t.nro_operacion "); 
			sb.append("inner join transferencias tra on t.nro_operacion=tra.nro_operacion "); 
			sb.append("inner join monedas m on t.id_moneda=m.id_moneda "); 
			sb.append("where o.nro_operacion=?");

			
			ps=conexion.getCon().prepareStatement(sb.toString());
			ps.setLong(1, nroOperacion);

			
			rs=ps.executeQuery();
			
			if(rs.next()){
				
				t=new Transferencia();
				t.setNroOperacion(rs.getLong("nro_operacion"));
				t.setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime());
				t.setImporte(rs.getDouble("importe"));
				ResultadoOperacion ro=new ResultadoOperacion();
				ro.setEstado(rs.getString("estado"));
				ro.setMensaje(rs.getString("mensaje"));
				t.setResultado(ro);
				Moneda m=new Moneda();
				m.setNombre(rs.getString("nombre_moneda"));
				m.setId(rs.getInt("id_moneda"));
				t.setMoneda(m);
				
				Acceso a=new Acceso();
				a.setId(rs.getLong("id_acceso"));
				t.setAcceso(a);
				
				nroCuentaOrigen=rs.getString("nro_cuenta_origen");
				nroCuentaDestino=rs.getString("nro_cuenta_destino");				
				
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
		
		if(t!=null){
			
			t.setCuenta(this.obtCuenta(nroCuentaOrigen));
			t.setCuentaDestino(this.obtCuenta(nroCuentaDestino));
			t.setAcceso(this.obtAcceso(t.getAcceso().getId()));
			
		}
		
		return t;
		
	}


	@Override
	public void actualizarSaldoCuenta(Cuenta cuenta) throws SistemaBancoException {
		
		PreparedStatement ps=null;
		
		try{
			
			conexion.conectar();
			
			ps=conexion.getCon().prepareStatement("update cuentas set saldo_disponible=? where nro_cuenta=?");
			ps.setDouble(1, cuenta.getSaldoDisponible());
			ps.setString(2, cuenta.getNroDeCuenta());
			
			int filasActualizadas=ps.executeUpdate();
			
			if(filasActualizadas==0){
				throw new SistemaBancoException("No se pudo actualizar el saldo de la cuenta: " + cuenta.getNroDeCuenta());
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		} catch (DBException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		}finally{
			
				try {
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
	
	@Override
	public void eliminarAcceso(Acceso acceso) throws SistemaBancoException {
		
		PreparedStatement ps=null;
		
		try{
			
			conexion.conectar();
			
			ps=conexion.getCon().prepareStatement("delete from accesos where id_acceso=?");
			
			ps.setLong(1, acceso.getId());
			
			int filasEliminadas=ps.executeUpdate();
			
			if(filasEliminadas==0){
				throw new SistemaBancoException("No se pudo eliminar el acceso: " + acceso.getId());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		} catch (DBException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		}finally{
			
				try {
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
	
	@Override
	public void actualizarSaldoTarjeta(Tarjeta tarjeta) throws SistemaBancoException {
		
		PreparedStatement ps=null;
		
		try{
			
			conexion.conectar();
			
			StringBuilder sb=new StringBuilder();
			sb.append("update tarjetas set saldo_actual=?, saldo_disponible=? "); 
			sb.append("where nro_tarjeta=?");
			
			ps=conexion.getCon().prepareStatement(sb.toString());
			
			ps.setDouble(1, tarjeta.getSaldoActual());
			ps.setDouble(2, tarjeta.getSaldoDisponible());
			ps.setString(3, tarjeta.getNroTarjeta());
			
			int filasActualizadas=ps.executeUpdate();
			
			if(filasActualizadas==0){
				throw new SistemaBancoException("No se pudo actualizar el saldo de la tarjeta: " + tarjeta.getNroTarjeta());
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		} catch (DBException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		}finally{
			
				try {
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
	

	@Override
	public boolean registrarMovimiento(MovimientoCuenta mc) throws SistemaBancoException {
		
		boolean insertado=false;
		
		PreparedStatement ps=null;
		
		try{
			
			// conecta a la base de datos
			conexion.conectar();
			StringBuilder sb=new StringBuilder();
			
			sb.append("insert into movimientos_cuentas(fecha_hora, ");
			sb.append("importe, nro_cuenta, descripcion, tipo, nro_comprobante, sentido) ");
			sb.append("values (?, ?, ?, ?, ?, ?, ?)");
			
			ps=conexion.getCon().prepareStatement(sb.toString());
			
			ps.setTimestamp(1, Timestamp.valueOf(mc.getFechaHora()));
			ps.setDouble(2, mc.getImporte());
			ps.setString(3,  mc.getCuenta().getNroDeCuenta());
			ps.setString(4, mc.getDescripcion());
			ps.setString(5,  mc.getTipo());
			ps.setLong(6, mc.getOperacion().getNroOperacion());
			ps.setString(7, mc.getSentido());
			
			int filasInsertadas=ps.executeUpdate();
			if(filasInsertadas > 0) insertado=true;
			
			
			
		} catch (DBException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SistemaBancoException(e);
		}finally{
			if(ps!=null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			try {
				conexion.desconectar();
			} catch (DBException e) {
				e.printStackTrace();
			}
		}
		
		return insertado;
	}
	


}
