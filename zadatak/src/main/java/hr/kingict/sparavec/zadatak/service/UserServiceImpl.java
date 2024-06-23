package hr.kingict.sparavec.zadatak.service;

import hr.kingict.sparavec.zadatak.dto.UserDTO;
import hr.kingict.sparavec.zadatak.facade.Mapper;
import hr.kingict.sparavec.zadatak.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    Mapper mapper;
    @Override
    public List<UserDTO> getAllDTO() {
        return userRepository.findAll().stream()
                .map(mapper::mapUserToUserDTO)
                .toList();
    }

    /*@Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResponseEntity<UserDTO> response = restTemplate.exchange(
                "http://dummyjson.com/users/{username}", HttpMethod.GET, null, UserDTO.class, username);

        if(response.getStatusCode() == HttpStatus.OK){
            UserDTO userDTO = response.getBody();
            return new User(userDTO.getUsername(), userDTO.getPassword(), Collections.emptyList());
        } else {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
    }*/
}
