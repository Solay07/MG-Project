package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.UsuarioDao;

import daoImp.UsuarioDaoImp;
import model.Usuario;
import org.w3c.dom.ls.LSInput;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet(name = "Usuario", urlPatterns = {"/Usuario"})  //URlL servlet
public class UsuarioController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UsuarioDao usuarioDao = UsuarioDaoImp.getInstance();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        //opciones
        switch (action) {
            case "index": //Listado de evaluacion
                try {
                    //Seteo de la lista
                    request.setAttribute("listUsuarios", this.listar());
                } catch (Exception e) {
                    System.out.println("Error");
                }

                //redirect y paso de la lista a la vista
                request.getRequestDispatcher("views/usuario/ListarUsuario.jsp").forward(request, response);
                break;
            case "add"://Listado de Customer
                request.getRequestDispatcher("views/usuario/AddUsuario.jsp").forward(request, response);
                break;
            case "ajax"://Listado de Customer
                Gson gson = new Gson();
                JsonObject jsonResponse = new JsonObject();

                String value = (request.getParameter("search") == null) ? "" : request.getParameter("search");

                jsonResponse.add("data", gson.toJsonTree(this.find("all", value)));

                response.setContentType("application/json");
                response.setHeader("Cache-Control", "no-store");
                response.getWriter().print(jsonResponse);
                break;
            default:
                response.getWriter().append("error dato no valido");
                break;
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String option = request.getParameter("option");

        //captura de datos
        String redirect = request.getParameter("redirect");
        String usuarinombres = request.getParameter("nombres");
        String usuariapellidos = request.getParameter("apellidos");
        String usuariemail = request.getParameter("email");
        String usuariusername = request.getParameter("username");
        String usuaripassword = request.getParameter("password");
        byte usuariid_tipo_usuario = (request.getParameter("id_tipo_usuario") != null) ? Byte.parseByte(request.getParameter("id_tipo_usuario")) : 0;
        int id = 0;

        if (request.getParameter("id") != null) {

            id = Integer.parseInt(request.getParameter("id"));

        }
        
        String contextPath = request.getContextPath();
        System.out.println("-------------------------" + option + "--------------------------------------");

        switch (option) {
            case "add"://Listado de Evaluacion
            	try {
                    this.guardarUsuario(usuarinombres, usuariapellidos, usuariemail, usuariusername, usuaripassword, usuariid_tipo_usuario);
                    request.setAttribute("mensaje", "dato agregado");
                } catch (Exception ex) {
                    request.setAttribute("mensaje", "Error al guardar el dato");
                    System.out.println(ex.getMessage());
                }
                
                response.sendRedirect(response.encodeRedirectURL(contextPath + "/Usuario?action=index"));
                break;

            case "login":
                try {
                    if (usuarioDao.testPass(request.getParameter("user"), request.getParameter("password"))) {
                        response.sendRedirect(response.encodeRedirectURL(contextPath + "/Proyectos?action=index"));
                    } else {
                       request.setAttribute("wrongPass", "Usuario o contraseña, incorrectos");
                       request.getRequestDispatcher("views/login/sign-in.jsp").forward(request, response);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                break;

            case "update":
                System.out.print("------------------------" + redirect + "-----------------------------");
                if ("true".equals(redirect)) {
                    try {
                        request.setAttribute("datos", this.crearLista(id, usuarinombres, usuariapellidos, usuariemail, usuariusername, usuaripassword, usuariid_tipo_usuario));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    request.getRequestDispatcher("views/usuario/UpdateUsuario.jsp").forward(request, response);
                } else {

                    request.setAttribute("mensaje", "test");
                    try {

                        this.updateUsuario(id, usuarinombres, usuariapellidos, usuariemail, usuariusername, usuaripassword, usuariid_tipo_usuario);

                    } catch (Exception ex) {
                        System.out.println(ex + ".............---");
                    }
                    
                    response.sendRedirect(response.encodeRedirectURL(contextPath + "/Usuario?action=index"));
                }

                break;



            case "delete":
                System.out.print("------------------------" + redirect + "-----------------------------");
                if ("true".equals(redirect)) {
                    request.setAttribute("datos", this.crearLista(id, usuarinombres, usuariapellidos, usuariemail, usuariusername, usuaripassword, usuariid_tipo_usuario));
                    request.getRequestDispatcher("views/usuario/DeleteUsuario.jsp").forward(request, response);
                } else {

                    try {

                        this.deleteUsuario(id);
                    } catch (Exception ex) {
                        System.out.println(ex + ".............---");
                        // Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    response.sendRedirect(response.encodeRedirectURL(contextPath + "/Usuario?action=index"));
                }

                break;

            default:
                response.getWriter().append("error dato no valido");
                break;
        }

    }

    //Listar
    private List<Map<String, String>> listar() {
        //Lista de Customer
        List<Map<String, String>> evaluacion = null;
        try {
            evaluacion = usuarioDao.findAllUsuario();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Retornar Lista
        return evaluacion;

    }
    
    private List<Usuario> find(String field, String value) {
        List<Usuario> us = null;
        try {
            us = usuarioDao.find(field, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return us;
    }

    //Crear listado para eliminar y modificar
    private List<Usuario> crearLista(int id, String nombres, String apellidos, String email, String username, String password, byte id_tipo_usuario) {
        //crear la lista
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        //Objeto
        Usuario usuari = new Usuario();
        //Set  datos
        usuari.setId((short) id);

        usuari.setNombres(nombres);
        usuari.setApellidos(apellidos);
        usuari.setEmail(email);
        usuari.setUsername(username);
        usuari.setPassword(password);
        usuari.setId_tipo_usuario(id_tipo_usuario);

        listaUsuario.add(usuari);

        return listaUsuario;

    }


    //Guardar
    private String guardarUsuario(String nombres, String apellidos, String email, String username, String password, byte id_tipo_usuario) throws Exception {
        //construccion del objeto
        Usuario usuari = new Usuario();

        usuari.setNombres(nombres);
        usuari.setApellidos(apellidos);
        usuari.setEmail(email);
        usuari.setUsername(username);
        usuari.setPassword(password);
        usuari.setId_tipo_usuario(id_tipo_usuario);

        usuarioDao.saveUsuario(usuari);
        // customerDao.saveCustomer(customer);
        System.out.println("OK usuario controller");

        return "ok";
    }

    private String deleteUsuario(int id) throws Exception {
        //System.out.println("entre---------------------delete");
        Usuario usuari = new Usuario();
        usuari.setId((short) id);
        usuarioDao.deleteUsuario(usuari);
        return "ok";
    }

    //Update CUSTUMER
    private String updateUsuario(int id, String nombres, String apellidos, String email, String username, String password, byte id_tipo_usuario) throws Exception {
        //construccion del objeto
        Usuario usuari = new Usuario();
        usuari.setId((short) id);
        usuari.setNombres(nombres);
        usuari.setApellidos(apellidos);
        usuari.setEmail(email);
        usuari.setUsername(username);
        usuari.setPassword(password);
        usuari.setId_tipo_usuario(id_tipo_usuario);

        usuarioDao.updateUsuario(usuari);
        // customerDao.saveCustomer(customer);
        //System.out.println("OK custumer controller");
        return "ok";
    }


}
