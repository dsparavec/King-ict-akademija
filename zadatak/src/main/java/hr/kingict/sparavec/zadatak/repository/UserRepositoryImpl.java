package hr.kingict.sparavec.zadatak.repository;

import hr.kingict.sparavec.zadatak.domain.User;
import hr.kingict.sparavec.zadatak.domain.UsersList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private WebClient.Builder webClientBuilder;
    private static final String EXTERNAL_API_USERS_URL = "https://dummyjson.com/users";
    private static final int LIMIT = 30;

    @Override
    public List<User> findAll() {
        return fetchUsers(EXTERNAL_API_USERS_URL, 0, LIMIT);
    }

    private List<User> fetchUsers(String url, int skip, int limit) {

        List<User> allUsers = new ArrayList<>();

        while (true) {
            String paginatedUrl = url + "?limit=" + limit + "&skip=" + skip;
            List<User> users = webClientBuilder.build()
                    .get()
                    .uri(paginatedUrl)
                    .retrieve()
                    .bodyToMono(UsersList.class)
                    .map(UsersList::getUserList)
                    .block();

            if (users == null || users.isEmpty())
                break;

            allUsers.addAll(users);
            skip += limit;

            if (users.size() < limit) {
                break;
            }
        }
        return allUsers;
    }
}
