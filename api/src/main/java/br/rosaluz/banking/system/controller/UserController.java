package br.rosaluz.banking.system.controller;

import br.rosaluz.banking.system.dto.*;
import br.rosaluz.banking.system.model.User;
import br.rosaluz.banking.system.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/banking/system/singup", produces="application/json")
@Api(value="API REST Banking System")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private  PasswordEncoder encoder;



    public UserController(UserService userService, PasswordEncoder encoder) {

        this.encoder = encoder;
        this.userService = userService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid UserDTO userDTO, UriComponentsBuilder uriBuider)
    {
         userDTO.setPassword(encoder.encode(userDTO.getPassword()));
         User user = userDTO.convertToUser();
         userService.saveUser(user);

        URI uri = uriBuider.path("/user/{id}").buildAndExpand(user.getUserid()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @GetMapping
    public ResponseEntity<Page<?>> orderList(final UserSearchFilterDTO searchFilterDTO,
                                                          @PageableDefault(sort = {"createdAt"}, direction = Sort.Direction.DESC) final Pageable pageable) {

        userService.userSearch(searchFilterDTO.convertToUserFilter(), pageable);
        return ResponseEntity.ok().build();
    }
}
