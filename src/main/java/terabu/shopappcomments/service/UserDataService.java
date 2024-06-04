package terabu.shopappcomments.service;

import lombok.RequiredArgsConstructor;
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

    public UserDataDTO findDataByUserId(@RequestParam Long userId){
        UserDataDTO body = restTemplate.getForEntity("http://localhost:8081/data/findByUserId?userId=" + userId, UserDataDTO.class).getBody();
        return body;
    }

}
