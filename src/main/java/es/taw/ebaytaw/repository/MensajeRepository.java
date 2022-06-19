package es.taw.ebaytaw.repository;

import es.taw.ebaytaw.entity.Mensaje;
import es.taw.ebaytaw.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {



    //public List<Mensaje> misMensajes(UserDTO user);

    Mensaje findByUserIDAndId(Users userID, int id);

    List<Mensaje> findAllByUserID(Users usersID);




}