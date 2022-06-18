package es.taw.ebaytaw.service;

import es.taw.ebaytaw.DTO.BidsDTO;
import es.taw.ebaytaw.DTO.UsersDTO;
import es.taw.ebaytaw.entity.Bids;
import es.taw.ebaytaw.entity.Users;
import es.taw.ebaytaw.repository.BidsRepository;
import es.taw.ebaytaw.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BidsService {
    private BidsRepository bidsRepository;
    private UsersRepository usersRepository;

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Autowired
    public void setBidsRepository(BidsRepository bidsRepository) {
        this.bidsRepository = bidsRepository;
    }

    public List<BidsDTO> convertirListaPujasADTO(List<Bids> listaPujas) {
        List<BidsDTO> listaPujasDTO = null;

        if (listaPujas != null) {
            listaPujasDTO = new ArrayList<>();
            for (Bids puja : listaPujas) {
                listaPujasDTO.add(puja.toDTO());
            }
        }

        return listaPujasDTO;
    }

    public List<BidsDTO> listarPujasPorUsuario(UsersDTO usuario) {
        Users comprador = this.usersRepository.findByUserID(usuario.getUserID());
        List<BidsDTO> listaPujasDTO = null;

        if (comprador != null) {
            List<Bids> listaPujas = this.bidsRepository.findByUserId(comprador.getUserID());
            listaPujasDTO = this.convertirListaPujasADTO(listaPujas);
        }

        return listaPujasDTO;
    }

    public void subirPuja(BidsDTO pujaDTO) {
        Bids puja = this.bidsRepository.findByUserIDAndProductID(pujaDTO.getUserID().getUserID(), pujaDTO.getProductID().getProductID());

        if (puja == null) {
            puja = new Bids();
            puja.setUserID(pujaDTO.getUserID());
            puja.setProductID(pujaDTO.getProductID());
        }

        puja.setPrice(pujaDTO.getPrice());

        this.bidsRepository.save(puja);
    }

    public void retirarPuja(BidsDTO pujaDTO) {
        Bids puja = this.bidsRepository.findByUserIDAndProductID(pujaDTO.getUserID().getUserID(), pujaDTO.getProductID().getProductID());

        this.bidsRepository.delete(puja);
    }
}
