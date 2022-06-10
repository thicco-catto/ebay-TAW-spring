package es.taw.ebaytaw.repository;

import es.taw.ebaytaw.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

    public Categories getByName(String name);

    @Query("select c from Categories c where c.name like %:name%")
    public List<Categories> getAllByName(String name);
}
