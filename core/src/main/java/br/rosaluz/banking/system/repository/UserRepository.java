package br.rosaluz.banking.system.repository;

import br.rosaluz.banking.system.model.User;
import br.rosaluz.banking.system.model.filter.UserSearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository {

    User save(final User user);
    Optional<User> findByLogin(String Login);
    Page<User> userSearch(UserSearchFilter userSearchFilter, Pageable pageable);
}
