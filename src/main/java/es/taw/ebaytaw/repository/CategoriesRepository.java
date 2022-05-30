package es.taw.ebaytaw.repository;

import es.taw.ebaytaw.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

    @Query("SELECT c FROM Categories c WHERE c.name LIKE :name")
    public List<Categories> findByName(String name);


}
