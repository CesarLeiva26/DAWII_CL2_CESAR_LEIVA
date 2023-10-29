package edu.pe.cibertec.DAWII_CL2_CESAR_LEIVA.Repository;

import edu.pe.cibertec.DAWII_CL2_CESAR_LEIVA.Model.bd.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    Rol findByNomrol(String nombrerol);
}
