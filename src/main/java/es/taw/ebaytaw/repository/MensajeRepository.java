package es.taw.ebaytaw.repository;

import es.taw.ebaytaw.DTO.UserDTO;
import es.taw.ebaytaw.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {


    
    public List<Mensaje> misMensajes(UserDTO user);




}