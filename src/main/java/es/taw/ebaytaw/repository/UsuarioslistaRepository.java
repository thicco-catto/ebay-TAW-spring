package es.taw.ebaytaw.repository;

import es.taw.ebaytaw.entity.Listausuarios;
import es.taw.ebaytaw.entity.Users;
import es.taw.ebaytaw.entity.Usuarioslista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioslistaRepository extends JpaRepository<Usuarioslista, Integer> {



    //@Query("select distinct p.userID from Usuarioslista p WHERE p.listID.listID = :idList")
   // List<Users> getUsuariosEnUnaLista();


    //Esta llama a la de arriba
   // List<UserDTO> getUsuariosDTOEnUnaLista(int idList);

    //@Query("select distinct p.userID from Usuarioslista p WHERE p.listID.listID = :idList")
    //List<Integer> getUsuariosEnUnaLista(int idList);

    @Query("select distinct p.userID from Usuarioslista p WHERE p.listID.listID = :idList")
    List<Users> getUsuariosEnUnaLista(int idList);


   // @Modifying
   // @Query("delete from Usuarioslista p WHERE p.listID.listID = :listID AND p.userID.userID = :userID")
   // void deleteByUserIDAndListID(@Param("userID")int userID, @Param("listID")int listID);
   //@Modifying
    //void deleteByUserIDAndListID(Users userID, Listausuarios listID);

    Usuarioslista findByUserIDAndAndListID(Users userID, Listausuarios listID);

    void deleteAllByListID(Listausuarios listID);

    List<Usuarioslista> findAllByListID(Listausuarios listID);

}