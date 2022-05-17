package br.rosaluz.banking.system.service;

import br.rosaluz.banking.system.model.User;
import br.rosaluz.banking.system.model.filter.UserSearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface UserService {

    public User saveUser(User user);
    public Optional<User> findByLogin(String login);
    public Optional<User> findById(Long id);
    Page<User> userSearch(UserSearchFilter userSearchFilter, Pageable pageable);

}
