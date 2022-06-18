package es.taw.ebaytaw.repository;

import es.taw.ebaytaw.entity.Categories;
import es.taw.ebaytaw.entity.Products;
import es.taw.ebaytaw.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {
    public List<Products> getAllByTitle(String title);

    public List<Products> getAllByUserID(Users userID);

    public List<Products> getAllByTitleAndUserID(String title, Users userID);

    @Query("select p from Products p where (:title is null or p.title like %:title%) and (:userId is null or p.userID.userID = :userId) and " +
            "(:categoryId is null or p.categoryID.categoryID = :categoryId) and (:initialPrice is null or p.initialPrice = :initialPrice) and " +
            "(:startDate is null or p.startDate = :startDate) and (:finishDate is null or p.finishDate = :finishDate) and (:isSold is null or p.isSold = :isSold)")
    public List<Products> findAll(String title, Integer userId, Integer categoryId, BigDecimal initialPrice, Date startDate, Date finishDate, Boolean isSold);

    // Denis
    @Query("SELECT p FROM Products p WHERE p.productID IN :listaProductosId")
    public List<Products> findByIds(@Param("listaProductosId") List<Integer> listaProductosId);

    @Query("SELECT p FROM Products p WHERE p.productID IN :listaProductosId AND (p.title LIKE %:filtroTituloDescripcion% OR p.description LIKE %:filtroTituloDescripcion%)")
    public List<Products> findByIdsAndTitleDescription(@Param("listaProductosId") List<Integer> listaProductosId, @Param("filtroTituloDescripcion") String filtroTituloDescripcion);

    @Query("SELECT p FROM Products p WHERE p.productID IN :listaProductosId AND (p.title LIKE %:filtroTituloDescripcion% OR p.description LIKE %:filtroTituloDescripcion%) AND p.categoryID.name IN :filtroCategoria")
    public List<Products> findByIdsAndTitleDescriptionAndCategory(@Param("listaProductosId") List<Integer> listaProductosId, @Param("filtroTituloDescripcion") String filtroTituloDescripcion, @Param("filtroCategoria") String[] filtroCategoria);

    @Query("SELECT p FROM Products p WHERE p.productID IN :listaProductosId AND p.categoryID.name IN :filtroCategoria")
    public List<Products> findByIdAndCategory(@Param("listaProductosId") List<Integer> listaProductosId, @Param("filtroCategoria") String[] filtroCategoria);

    @Query("SELECT p FROM Products p WHERE (p.title LIKE %:filtroTituloDescripcion% OR p.description LIKE %:filtroTituloDescripcion%) AND p.categoryID.name IN :filtroCategoria")
    public List<Products> findByTitleDescriptionAndCategory(@Param("filtroTituloDescripcion") String filtroTituloDescripcion, @Param("filtroCategoria")  String[] filtroCategoria);

    @Query("SELECT p FROM Products p WHERE (p.title LIKE %:filtroTituloDescripcion% OR p.description LIKE %:filtroTituloDescripcion%)")
    public List<Products> findByTitleDescription(@Param("filtroTituloDescripcion") String filtroTituloDescripcion);

    @Query("SELECT p FROM Products p WHERE p.categoryID.name IN :filtroCategoria")
    public List<Products> findByCategory(@Param("filtroCategoria")  String[] filtroCategoria);
}
