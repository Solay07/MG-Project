package test;

import java.util.List;
import java.util.Iterator;
import java.util.Map;

import daoImp.UsuarioDaoImp;
import model.Usuario;

public class TestUsuario {
	private UsuarioDaoImp usua = new UsuarioDaoImp();
	private Usuario usuario = new Usuario();
	
	public void save() {
		 usuario.setNombres("Alberto Oscar");
		 
		 usuario.setApellidos("Chamuco Tobar");
		 
		 usuario.setEmail("asdfas@sadas.com");
		 
		 usuario.setUsername("JUan");
		 
		 usuario.setPassword("48520520");
		 
		 usuario.setId_tipo_usuario((byte) 1);
		 
		 try {

			 usua.saveUsuario(usuario);
			 
			 System.out.println("Dato guardado");
			 
		 } catch (Exception e) {
			 
			 System.out.println("Error al actualizar registros:" +e.getMessage());
		 }
	}
	
	public void update(short id) {
		 usuario.setId(id);
		 usuario.setNombres("juan camaney");
		 usuario.setApellidos("Galicia Merito");
		 usuario.setEmail("aaaaaa@YYY.com");
		 usuario.setUsername("username");
		 usuario.setPassword("11111111");
		 usuario.setId_tipo_usuario((byte) 1);
		 
		 
		 try {
			 
			 usua.updateUsuario(usuario);
			 
			 System.out.println("Dato Modificado");
			 
		 } catch (Exception e) {
			 
			 System.out.println("Error al actualizar registros:" +e.getMessage());
		 }
	}
	
	public void delete() {
		
		 
	     usuario.setId((short) 22);
	     
		 try {
			 
		 usua.deleteUsuario(usuario);
		 
		 } catch (Exception e) {

			 System.out.println("Error al actualizar registros:" + e.getMessage());
		 }
		
	}
	
	public void findAll() {
		
		List<Map<String, String>> usuarios;

		try {
			usuarios = usua.findAllUsuario();

			if ( usuarios != null) {
				for (Map<String, String> usuario:
					usuarios) {
					Iterator it = usuario.keySet().iterator();

					while (it.hasNext()) {
						String key = (String) it.next();
						System.out.println("Clave: " + key + " -> Valor: " + usuario.get(key));
					}
					System.out.println("----------------------------------------------------------\n");
				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void find(String filed, String value) {
		try {
			List<Usuario> listUsuario = usua.find(filed, value);

			for (Usuario usuario:
				 listUsuario) {

				System.out.println("id -> " + usuario.getId());
				System.out.println("nombres -> " + usuario.getNombres());
				System.out.println("apellidos -> " + usuario.getApellidos());
				System.out.println("email -> " + usuario.getEmail());
				System.out.println("username -> " + usuario.getUsername());
				System.out.println("password -> " + usuario.getPassword());
				System.out.println("id_tipo_usuario -> " + usuario.getId_tipo_usuario());

				System.out.println("----------------------------------------------------------\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean pass(String user, String pass) {
		try {
			return usua.testPass(user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		TestUsuario test = new TestUsuario();

		//test.find("all", "r");

		//test.save();

		System.out.println(test.pass("solay2516", "abcd1234"));
	}
}
