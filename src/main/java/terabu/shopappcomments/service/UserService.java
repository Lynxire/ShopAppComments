package terabu.shopappcomments.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import terabu.shopappcomments.dto.UserDTO;


@Service
@RequiredArgsConstructor
public class UserService {
    private final RestTemplate restTemplate;

    public UserDTO findUserById(@RequestParam Long id){
        return restTemplate.getForEntity("http://localhost:8081/user/findById?id=" + id, UserDTO.class).getBody();
    }

}
