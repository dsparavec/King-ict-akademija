package hr.kingict.sparavec.zadatak.repository;

import hr.kingict.sparavec.zadatak.domain.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();
}
