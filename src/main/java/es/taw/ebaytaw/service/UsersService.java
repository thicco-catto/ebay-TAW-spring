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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * @author mjura
 */

//@Stateless
@Service
public class UsersService {

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
        Users usuario = this.uf.findByEmailAndPassword(email, pass);
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
        if ((rol == null || rol.isEmpty()) && (username == null || username.isEmpty()) && (name == null || name.isEmpty()) && (email == null || email.isEmpty())
                && (surname == null || surname.isEmpty()) && (gender == null || gender.isEmpty()) && (street == null || street.isEmpty())
                && number == null && (city == null || city.isEmpty()) && (region == null || region.isEmpty()) && postalCode == null) {
            List<Users> users = this.uf.findAll();
            return this.listaEntityADTO(users);
        } else {
            List<Users> users = this.uf.getUsers(rol, username, email, name, surname, gender, street, number, city, region, postalCode);
            return this.listaEntityADTO(users);
        }
    }

    //Cristobal
    public void borrarUsuario(Integer userId) {
        Users user = this.uf.findById(userId).orElse(null); //poner siempre el orElse
        this.uf.delete(user);
    }

    //Cristobal
    public UsersDTO getUsuario(Integer userId) {
        Users user = this.uf.findById(userId).orElse(null);
        return user.toDTO();
    }

    //Cristobal
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

        System.out.println("sancho");
        System.out.println(rol + ", " + username + ", " + email + ", " + name + ", " + surname + ", " + gender + ", " + street + ", " + number + ", " + city + ", " + region + ", " + postalCode);

        this.uf.save(user);
    }

    // Antonio
    public List<UsersDTO> usuariosDTODeUnaLista(int idList) {
        //TODO:HACER CUANDO HAGA FALTA, COCHINO
       // return this.ulf.getUsuariosDTOEnUnaLista(idList);
        return null;

    }

    // Antonio
    public List<UsersDTO> listarUsuariosFiltrado(String nombreUsuario, String orderBy) {

        //TODO:HACER CUANDO HAGA FALTA, COCHINO

        //return this.uf.listarUsuariosFiltrado(nombreUsuario,orderBy);
        return null;
    }


    public UsersDTO buscarUsuario(Integer usuarioId) {
        return this.uf.findById(usuarioId).orElse(null).toDTO();
    }

    public Users findUser(Integer usuarioId) {
        return this.uf.findById(usuarioId).orElse(null);
    }




}
