package br.rosaluz.banking.system.repository;

import br.rosaluz.banking.system.model.User;
import br.rosaluz.banking.system.model.filter.UserSearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository {

    User saveOrUpdate(final User user);
    User searchByIds(final Set<String> ids);
    void  deleteUser(final Set<String> ids);
    Optional<User> findByLogin(String Login);
    Page<User> userSearch(UserSearchFilter userSearchFilter, Pageable pageable);
}
