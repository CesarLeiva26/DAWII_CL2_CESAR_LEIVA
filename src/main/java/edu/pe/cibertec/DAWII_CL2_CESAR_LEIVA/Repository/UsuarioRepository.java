package edu.pe.cibertec.DAWII_CL2_CESAR_LEIVA.Repository;

import edu.pe.cibertec.DAWII_CL2_CESAR_LEIVA.Model.bd.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends
        JpaRepository<Usuario, Integer> {
    Usuario findByEmail(String email);
    Usuario findByNomusuario(String nomusuario);
}
