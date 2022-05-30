package es.taw.ebaytaw.repository;

import es.taw.ebaytaw.entity.Usuarioslista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioslistaRepository extends JpaRepository<Usuarioslista, Integer> {



    //@Query("select distinct p.userID from Usuarioslista p WHERE p.listID.listID = :idList")
   // List<Users> getUsuariosEnUnaLista();


    //Esta llama a la de arriba
   // List<UserDTO> getUsuariosDTOEnUnaLista(int idList);



    

}