/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.ebaytaw.service;
import es.taw.ebaytaw.entity.Users;
import es.taw.ebaytaw.DTO.UsersDTO;
import es.taw.ebaytaw.repository.UsersRepository;
import es.taw.ebaytaw.repository.UsuarioslistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * @author mjura
 */

//@Stateless
@Service
public class UsersService {
    private UsersRepository usersRepository;

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // public void setUsersRepository(){}





    //Cristobal
    public List<UsersDTO> listaEntityADTO(List<Users> lista) {
        return lista.stream().map(u -> u.toDTO()).collect(Collectors.toList());
    }

    public UsersRepository getUf() {
        return uf;
    }

    public void setUf(UsersRepository uf) {
        this.uf = uf;
    }

    UsersRepository uf;
    UsuarioslistaRepository ulf;

    // Miguel y Cristobal
    public UsersDTO comprobarCredenciales(String email, String pass) {
        Users usuario = this.uf.comprobarUsuario(email, pass);
        if (usuario == null){
            return null;
        }
        return usuario.toDTO();
    }

    // Miguel
    public void crearVendedor(String username, String email, String password,
                              String nombre, String apellidos, String genero, String calle,
                              String numero, String ciudad, String cpostal, String region) {
        Users vendedor = new Users();
        // Relleno los datos
        vendedor.setRol("Vendedor");
        vendedor.setUsername(username);
        vendedor.setEmail(email);
        vendedor.setPassword(password);

        if (nombre.isEmpty()){
            nombre = null;
        }
        vendedor.setName(nombre);

        if (apellidos.isEmpty()){
            apellidos = null;
        }
        vendedor.setSurname(apellidos);

        vendedor.setGender(genero);

        if (calle.isEmpty()){
            calle = null;
        }
        vendedor.setStreet(calle);

        if (!numero.isEmpty()){
            vendedor.setNumber(Integer.parseInt(numero));
        } else {
            vendedor.setNumber(null);
        }

        if (ciudad.isEmpty()){
            ciudad = null;
        }
        vendedor.setCity(ciudad);

        if (!cpostal.isEmpty()){
            vendedor.setPostalCode(Integer.parseInt(cpostal));
        } else {
            vendedor.setPostalCode(null);
        }


        if (region.isEmpty()){
            region = null;
        }
        vendedor.setRegion(region);


        this.uf.save(vendedor);  //save == create
    }

    public List<UsersDTO> listarUsuarios() {
        List<Users> users = this.uf.findAll();

        return this.listaEntityADTO(users);
    }

    //Cristobal
    public List<UsersDTO> listarUsuarios(String rol, String username, String email, String name,
                                        String surname, String gender, String street, Integer number,
                                        String city, String region, Integer postalCode) {
        List<Users> users;
        if ((rol == null || rol.isEmpty()) && (username == null || username.isEmpty()) && (name == null || name.isEmpty()) && (email == null || email.isEmpty())
                && (surname == null || surname.isEmpty()) && (gender == null || gender.isEmpty()) && (street == null || street.isEmpty())
                && number == null && (city == null || city.isEmpty()) && (region == null || region.isEmpty()) && postalCode == null) {
            users = this.usersRepository.findAll();
        } else {
            System.out.println(name.isEmpty());
            users = this.usersRepository.getUsers(rol, username, email, name, surname, gender, street, number, city, region, postalCode);
        }
        return this.listaEntityADTO(users);
    }

    public List<UsersDTO> listarUsuarios(UsersDTO userFilter) {
        return listarUsuarios(
                userFilter.getRol().isEmpty() ? null : userFilter.getRol(), userFilter.getUsername(), userFilter.getEmail(), userFilter.getName(),
                userFilter.getSurname(), userFilter.getGender().isEmpty() ? null : userFilter.getGender(), userFilter.getStreet(), userFilter.getNumber(),
                userFilter.getCity(), userFilter.getRegion(), userFilter.getPostalCode());
    }

    //Cristobal
    public void borrarUsuario(Integer userId) {
        Users user = this.uf.findById(userId).orElse(null); //poner siempre el orElse
        System.out.println(user);
        this.uf.delete(user);
    }

    //Cristobal
    public UsersDTO getUsuario(Integer userId) {
        Users user = this.uf.findById(userId).orElse(null);
        return user.toDTO();
    }

    //Cristobal
    public void editarUser(UsersDTO user){
        editarUser(user.getUserID(), user.getRol(), user.getUsername(), user.getEmail(), user.getName(),
                user.getSurname(), user.getGender(), user.getStreet(), user.getNumber(),
                user.getCity(), user.getRegion(), user.getPostalCode());
    }

    public void editarUser(Integer userId, String rol, String username, String email, String name,
                           String surname, String gender, String street, Integer number,
                           String city, String region, Integer postalCode) {
        Users user = this.uf.findById(userId).orElse(null);

        user.setRol(rol);
        user.setUsername(username);
        user.setEmail(email);
        if (name.isEmpty()) {
            name = null;
        }
        user.setName(name);
        if (surname.isEmpty()) {
            surname = null;
        }
        user.setSurname(surname);
        if (gender.isEmpty()) {
            gender = null;
        }
        user.setGender(gender);
        if (street.isEmpty()) {
            street = null;
        }
        user.setStreet(street);
        user.setNumber(number);
        if (city.isEmpty()) {
            city = null;
        }
        user.setCity(city);
        if (region.isEmpty()) {
            region = null;
        }
        user.setRegion(region);
        user.setPostalCode(postalCode);

        this.uf.save(user);
    }

    //Cristobal
    public void crearUser(UsersDTO user){
        crearUser(user.getRol(), user.getUsername(), user.getPassword(), user.getEmail(), user.getName(),
                user.getSurname(), user.getGender(), user.getStreet(), user.getNumber(),
                user.getCity(), user.getRegion(), user.getPostalCode());
    }

    //Cristobal
    public void crearUser(String rol, String username, String password, String email, String name,
                          String surname, String gender, String street, Integer number,
                          String city, String region, Integer postalCode) {
        Users user = new Users();

        user.setRol(rol);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        if (name.isEmpty()) {
            name = null;
        }
        user.setName(name);
        if (surname.isEmpty()) {
            surname = null;
        }
        user.setSurname(surname);
        if (gender.isEmpty()) {
            gender = null;
        }
        user.setGender(gender);
        if (street.isEmpty()) {
            street = null;
        }
        user.setStreet(street);
        user.setNumber(number);
        if (city.isEmpty()) {
            city = null;
        }
        user.setCity(city);
        if (region.isEmpty()) {
            region = null;
        }
        user.setRegion(region);
        user.setPostalCode(postalCode);

        System.out.println ("sancho");
        System.out.println(rol + ", " + username + ", " + email + ", " + name + ", " + surname + ", " + gender + ", " + street + ", " + number + ", " + city + ", " + region + ", " + postalCode);

        this.uf.save(user);
    }

/*
    //Antonio
    public List<UsersDTO> listarUsuariosFiltrado(String nombreUsuario, String orderBy) {


        String queryStr = "SELECT u FROM Users u ";
        String where = "WHERE u.name like '%" + nombreUsuario +"%' ";
        String order = "ORDER BY u." + orderBy;

        if(nombreUsuario != null && !nombreUsuario.isEmpty())
            queryStr += where;

        if(orderBy != null && !orderBy.isEmpty())
            queryStr += order;

        Query q = this.getEntityManager().createQuery(queryStr);

        List<Users> lista = q.getResultList();

        List<UserDTO> resultado = new ArrayList<>();
        for(Users u : lista)
            resultado.add(u.toDTO());


        return resultado;


    }
*/








    public UsersDTO buscarUsuario(Integer usuarioId) {
        return this.uf.findById(usuarioId).orElse(null).toDTO();
    }

    public Users findUser(Integer usuarioId) {
        return this.uf.findById(usuarioId).orElse(null);
    }

    // Denis
    public void guardarComprador(UsersDTO usuarioDTO) {
        Users comprador = new Users();

        comprador.setUserID(usuarioDTO.getUserID());
        comprador.setRol("Comprador");
        comprador.setUsername(usuarioDTO.getUsername());
        comprador.setPassword(usuarioDTO.getPassword());
        comprador.setEmail(usuarioDTO.getEmail());
        comprador.setName(usuarioDTO.getName());
        comprador.setSurname(usuarioDTO.getSurname());
        comprador.setGender(usuarioDTO.getGender());
        comprador.setStreet(usuarioDTO.getStreet());
        comprador.setNumber(usuarioDTO.getNumber());
        comprador.setCity(usuarioDTO.getCity());
        comprador.setRegion(usuarioDTO.getRegion());
        comprador.setPostalCode(usuarioDTO.getPostalCode());

        this.usersRepository.save(comprador);
    }
}
