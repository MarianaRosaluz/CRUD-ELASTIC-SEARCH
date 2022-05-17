package br.rosaluz.banking.system.service;

import br.rosaluz.banking.system.model.User;
import br.rosaluz.banking.system.model.filter.UserSearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface UserService {

    User saveOrUpdate(User user);
    void  deleteUser(final Set<String> ids);
    Optional<User> findByLogin(String login);
    Optional<User> findById(Long id);
    User searchByIds(final Set<String> ids);
    Page<User> userSearch(UserSearchFilter userSearchFilter, Pageable pageable);

}
