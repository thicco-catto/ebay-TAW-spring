package es.taw.ebaytaw.repository;

import es.taw.ebaytaw.entity.Listausuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListausuariosRepository extends JpaRepository<Listausuarios, Integer> {


    //@Query("DELETE FROM Listausuarios p WHERE p.listID = :idList")
    //public void borrarLista(Integer listID);



    @Query("select p.listID from Listausuarios p WHERE p.username like :fname")
    List<Integer> getId(String fname);

    Listausuarios findByListID(int listID);



    


}