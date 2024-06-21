package terabu.shopappcomments.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import terabu.shopappcomments.dto.UserDTO;
import terabu.shopappcomments.dto.UserDataDTO;

@RequiredArgsConstructor
@Service
public class UserDataService {
    private final RestTemplate restTemplate;

    @Value("${user_data.url}")
    private String url;
    private String urlFind;

    @PostConstruct
    public void init() {
        urlFind = url + "/findByUserId?userId=";

    }

    public UserDataDTO findDataByUserId(@RequestParam Long userId){
        UserDataDTO body = restTemplate.getForEntity(urlFind + userId, UserDataDTO.class).getBody();
        return body;
    }

}
