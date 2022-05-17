package br.rosaluz.banking.system.controller;

import br.rosaluz.banking.system.dto.*;
import br.rosaluz.banking.system.model.User;
import br.rosaluz.banking.system.service.UserService;
import io.swagger.annotations.Api;
import org.elasticsearch.common.collect.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static br.rosaluz.banking.system.dto.UserResponseDto.convert;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/banking/system/singup", produces="application/json")
@Api(value="API REST Banking System")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid UserDTO userDTO, UriComponentsBuilder uriBuider)
    {
         User user = userDTO.convertToUser();
         userService.saveOrUpdate(user);

        URI uri = uriBuider.path("/user/{id}").buildAndExpand(user.getUserid()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid UserDTO userDTO, UriComponentsBuilder uriBuider)
    {
        User user = userDTO.convertToUser();
        userService.saveOrUpdate(user);

        URI uri = uriBuider.path("/user/{id}").buildAndExpand(user.getUserid()).toUri();
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> delete(@PathVariable final String userId){

        userService.deleteUser(Set.of(userId));
        return ResponseEntity.accepted().build();
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> get(@PathVariable final String userId) {

        return ResponseEntity.ok(convert(userService.searchByIds(Set.of(userId))));
    }
}
