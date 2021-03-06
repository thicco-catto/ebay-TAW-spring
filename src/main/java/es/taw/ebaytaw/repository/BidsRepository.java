package es.taw.ebaytaw.repository;

import es.taw.ebaytaw.entity.Bids;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidsRepository extends JpaRepository<Bids, Integer> {
    @Query("SELECT b FROM Bids b WHERE b.userID.userID = :usuarioId")
    public List<Bids> findByUserId(Integer usuarioId);

    @Query("SELECT b FROM Bids b WHERE b.userID.userID = :usuarioId AND b.productID.productID = :productoId")
    public Bids findByUserIDAndProductID(@Param("usuarioId") Integer usuarioId, @Param("productoId") Integer productoId);
}