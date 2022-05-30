package es.taw.ebaytaw.repository;

import es.taw.ebaytaw.DTO.UserDTO;
import es.taw.ebaytaw.entity.Listausuarios;
import es.taw.ebaytaw.entity.Users;
import es.taw.ebaytaw.entity.Usuarioslista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioslistaRepository extends JpaRepository<Usuarioslista, Integer> {



    //@Query("select distinct p.userID from Usuarioslista p WHERE p.listID.listID = :idList")
    List<Users> getUsuariosEnUnaLista();


    //Esta llama a la de arriba
    List<UserDTO> getUsuariosDTOEnUnaLista(int idList);



    

}