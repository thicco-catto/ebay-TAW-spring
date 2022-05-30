package es.taw.ebaytaw.repository;

import es.taw.ebaytaw.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {


    // Miguel y Cristobal
    @Query("select u from Users u where u.email = :email and u.password = :pass")
    public Users comprobarUsuario (String email, String pass);



    //Antonio
    //Query("SELECT u FROM Users u WHERE u.name like '%  :nombreUsuario %' ")
    //public List<UserDTO> listarUsuariosFiltrado(String nombreUsuario, String orderBy);


}
