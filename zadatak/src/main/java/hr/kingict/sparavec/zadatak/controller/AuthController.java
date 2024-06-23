package hr.kingict.sparavec.zadatak.controller;

import hr.kingict.sparavec.zadatak.domain.RefreshToken;
import hr.kingict.sparavec.zadatak.dto.AuthRequestDTO;
import hr.kingict.sparavec.zadatak.dto.JwtResponseDTO;
//import hr.kingict.sparavec.zadatak.service.RefreshTokenService;
import hr.kingict.sparavec.zadatak.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate;

    //private static final String authApiUrl = "http://dummyjson.com";

    /*@PostMapping("/login")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){

        List<UserDTO> userDTOList = userService.getAllDTO();

        Optional<UserDTO> optionalUserDTO = userDTOList.stream()
                .filter(user -> user.getUsername().equals(authRequestDTO.getUsername())
                        && user.getPassword().equals(authRequestDTO.getPassword()))
                .findFirst();

        if(optionalUserDTO.isPresent()){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("username", authRequestDTO.getUsername());
            requestBody.put("password", authRequestDTO.getPassword());

            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<Map> responseEntity = restTemplate.postForEntity(authApiUrl + "/auth/login", requestEntity, Map.class);

            if(responseEntity.getStatusCode() == HttpStatus.OK){
                Map<String, Object> responseBody = responseEntity.getBody();

                JwtResponseDTO jwtResponseDTO = new JwtResponseDTO();
                jwtResponseDTO.setId((Integer) responseBody.get("id"));
                jwtResponseDTO.setUsername((String) responseBody.get("username"));
                jwtResponseDTO.setEmail((String) responseBody.get("email"));
                jwtResponseDTO.setFirstName((String) responseBody.get("firstName"));
                jwtResponseDTO.setLastName((String) responseBody.get("lastName"));
                jwtResponseDTO.setGender((String) responseBody.get("gender"));
                jwtResponseDTO.setImage((String) responseBody.get("image"));
                jwtResponseDTO.setToken((String) responseBody.get("token"));
                jwtResponseDTO.setRefreshToken((String) responseBody.get("refreshToken"));

                return ResponseEntity.ok(jwtResponseDTO);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or passwrod.");
        }


        *//*try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));

            if(authentication.isAuthenticated()){
                RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
                return JwtResponseDTO.builder()
                        .accessToken(jwtService.generateToken(authRequestDTO.getUsername()))
                        .token(refreshToken.getToken())
                        .build();
            }
        }*//*
    }*/

    /*@GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String token) {
        String url = "https://dummyjson.com/auth/me";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        return ResponseEntity.ok(response.getBody());
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestParam String refreshToken) {
        String url = "https://dummyjson.com/auth/refresh";
        Map<String, Object> request = new HashMap<>();
        request.put("refreshToken", refreshToken);
        request.put("expiresInMins", 30);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
        return ResponseEntity.ok(response.getBody());
    }*/
}
