package es.taw.ebaytaw.repository;

import es.taw.ebaytaw.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {


    // Miguel y Cristobal
    @Query("select u from Users u where u.email = :email and u.password = :pass")
    public Users comprobarUsuario (String email, String pass);


    Users findByEmailAndPassword(String email, String pass);



    //Antonio
    //Query("SELECT u FROM Users u WHERE u.name like '%  :nombreUsuario %' ")
    //public List<UserDTO> listarUsuariosFiltrado(String nombreUsuario, String orderBy);

    @Query("select u from Users u where (:rol is null or u.rol = :rol) and (:email is null or u.email like :email) and (:username is null or u.username like :username) and (:email is null or u.email like :email) and (:name is null or u.name like :name) and (:surname is null or u.surname like :surname) and (:gender is null or u.gender = :gender) and (:street is null or u.street like :street) and (:number is null or u.number = :number) and (:city is null or u.city like :city) and (:region is null or u.region like :region) and (:postalCode is null or u.postalcode = :postalCode)")
    public List<Users> getUsers(String rol, String username, String email, String name, String surname, String gender, String street, Integer number, String city, String region, Integer postalCode);

    //Antonio
    List<Users> findAll();
    //Antonio
    Users findByUserID(int userID);





}
