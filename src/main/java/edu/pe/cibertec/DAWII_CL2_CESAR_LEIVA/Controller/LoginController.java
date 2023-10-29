package edu.pe.cibertec.DAWII_CL2_CESAR_LEIVA.Controller;

import edu.pe.cibertec.DAWII_CL2_CESAR_LEIVA.Model.bd.Usuario;
import edu.pe.cibertec.DAWII_CL2_CESAR_LEIVA.Service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class LoginController {

    private UsuarioService usuarioService;
    @GetMapping("/login")
    public String login(){
        return "auth/frmLogin";
    }

    @GetMapping("/registrar")
    public String registrar(){
        return "auth/frmRegistroUsuario";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario){
        usuarioService.saveUser(usuario);
        return "auth/frmLogin";
    }
    @PostMapping("/login-usuario")
    public String loginUsuario(HttpServletRequest request){
        UserDetails usuario = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        HttpSession session = request.getSession();
        session.setAttribute("usuario", usuario.getUsername());
        return "auth/index";
    }
    @PostMapping("/cambiarpassword")
    public String cambiarPassword(@RequestParam String newPassword) {
        Usuario usuario = obtenerUsuarioActual();
        if (usuario != null) {
            usuarioService.updatePassword(usuario, newPassword);
        }
        return "redirect:/auth/login";
    }

    private Usuario obtenerUsuarioActual() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return usuarioService.findUserByUserName(userDetails.getUsername());
    }

}
