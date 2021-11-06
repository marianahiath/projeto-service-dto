package br.org.serratec.servicedto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.org.serratec.servicedto.dto.EnderecoDTO;
import br.org.serratec.servicedto.model.Endereco;
import br.org.serratec.servicedto.repository.EnderecoRepository;

@Service
public class EnderecoService {
    
    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoDTO buscar(String cep) {

       Optional<Endereco> endereco = Optional.ofNullable(enderecoRepository.findByCep(cep));

        if(endereco.isPresent()) {
            return new EnderecoDTO(endereco.get());
        } else { //se não tiver no meu banco vai buscar no viacep
            RestTemplate restTemplate = new RestTemplate();

            String uriViaCep = "https://viacep.com.br/ws/" + cep + "/json/";
            
            Optional<Endereco> enderecoViaCep = Optional.ofNullable(restTemplate.getForObject(uriViaCep, Endereco.class));
            //converte para endereço com o ofNullable

            if(enderecoViaCep.get().getCep() != null) {
                String cepSemTraco = enderecoViaCep.get().getCep().replace("-", ""); //tirando o tracinho
                enderecoViaCep.get().setCep(cepSemTraco);

                return inserir(enderecoViaCep.get());
            } else {
                return null;
            }
        }

    }

    public EnderecoDTO inserir(Endereco endereco) {

        endereco = enderecoRepository.save(endereco);

        return new EnderecoDTO(endereco);
    }

}
