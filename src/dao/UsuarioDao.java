package dao;

import java.util.List;

import java.util.Map;

import model.Usuario;

public interface UsuarioDao {

    void saveUsuario(Usuario usuario) throws Exception;

    void updateUsuario(Usuario usuario) throws Exception;

    void deleteUsuario(Usuario usuario) throws Exception;
  
    List<Map<String, String>> findAllUsuario() throws Exception;

    List<Usuario> find(String field, String value) throws Exception;

    boolean testPass(String user, String pass) throws Exception;
}
