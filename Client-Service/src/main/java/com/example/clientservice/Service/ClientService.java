package com.example.clientservice.Service;

import com.example.clientservice.DTOs.ClientRequestDTO;
import com.example.clientservice.DTOs.ClientResponseDTO;
import com.example.clientservice.Entities.Client;
import com.example.clientservice.Mapper.ClientMapperInterface;
import com.example.clientservice.Repositories.ClientRepository;
import com.example.clientservice.model.Product;
import com.example.clientservice.products.ProductFeignController;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ClientService implements ClientServiceInterface {

    @Autowired
    ClientMapperInterface clientMapperInterface;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ProductFeignController productFeigncontroller;

    @Override
    public void addClient(ClientRequestDTO clientRequestDTO) {

        Client client = clientMapperInterface.FromClientRequestDTO_2_Client(clientRequestDTO);
        clientRepository.save(client);


    }

    /*@Override
    public List<ClientResponseDTO> listClient() {
        List<Client> clientList = clientRepository.findAll();

        List<ClientResponseDTO> listR = new ArrayList<>();

        for (Client  c : clientList)
        {
            ClientResponseDTO responseDTO = clientMapperInterface.FromClient_2_ClientResponseDTO(c);

            listR.add(responseDTO);
        }
        return listR;
    }*/

    @Override
    public ClientResponseDTO ClientById(Integer id) {
            Product product;
            Client client = clientRepository.findById(id).get();
            Integer idProduct = client.getIdProduct();
            if (idProduct!=null) {
                product = productFeigncontroller.findProductById(idProduct);
            }else {product=null;}
        client.setProduct(product);

        ClientResponseDTO clientResponseDTO = clientMapperInterface.FromClient_2_ClientResponseDTO(client);
        return clientResponseDTO;
    }

    @Override
    public void Update(Integer id, ClientRequestDTO clientRequestDTO) {

        Client client1 = clientRepository.findById(id).get();
        Client client2 = clientMapperInterface.FromClientRequestDTO_2_Client(clientRequestDTO);

        client1.setName(client2.getName() != null ? client2.getName() : client1.getName());
        client1.setCity(client2.getCity() != null ? client2.getCity() : client1.getCity());

        clientRepository.save(client1);

    }

    @Override
    public void DeleteById(Integer id) {
        clientRepository.deleteById(id);

    }

    @Override
    public List<ClientResponseDTO> listClient() {
        List<Client> clientList = clientRepository.findAll();


        List<Product> products = productFeigncontroller.findAllProduct();


        List<ClientResponseDTO> listR = new ArrayList<>();


        for (Client c : clientList) {
            for (Product p : products) {
                if (p.getId() == c.getIdProduct()) {
                    c.setProduct(p);


                }
            }

            ClientResponseDTO responseDTO = clientMapperInterface.FromClient_2_ClientResponseDTO(c);
            listR.add(responseDTO);
        }

        return listR;
    }
}