package edu.pe.cibertec.DAWII_CL2_CESAR_LEIVA.Service;

import edu.pe.cibertec.DAWII_CL2_CESAR_LEIVA.Model.bd.Rol;
import edu.pe.cibertec.DAWII_CL2_CESAR_LEIVA.Model.bd.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class DetalleUsuarioService implements UserDetailsService {

    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.findUserByUserName(username);
        return autenticacionUsuario(
                usuario, obtenerListaRolesUsuario(usuario.getRoles())
        );
    }

    private List<GrantedAuthority> obtenerListaRolesUsuario(Set<Rol> listRoles){
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (Rol rol : listRoles) {
            roles.add(new SimpleGrantedAuthority(rol.getNomrol()));
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails autenticacionUsuario(
            Usuario usuario, List<GrantedAuthority> authorityList){
        return new User(usuario.getNomusuario(),
                usuario.getPassword(),
                usuario.getActivo(),
                true,
                true,
                true, authorityList);
    }

}
