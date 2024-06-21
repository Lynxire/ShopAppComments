package terabu.shopappcomments.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import terabu.shopappcomments.dto.UserDTO;


@Service
@RequiredArgsConstructor
public class UserService {
    private final RestTemplate restTemplate;


    @Value("${user.url}")
    private String url;
    private String urlFind;

    @PostConstruct
    public void init() {
        urlFind = url + "/findById?id=";

    }

    public UserDTO findUserById(@RequestParam Long id){
        return restTemplate.getForEntity(urlFind + id, UserDTO.class).getBody();
    }

}
