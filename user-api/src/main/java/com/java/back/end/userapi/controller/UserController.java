package com.java.back.end.userapi.controller;

import com.java.back.end.userapi.dto.UserDTO;
import com.java.back.end.userapi.service.UserService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;
    public static List<UserDTO> usuarios = new ArrayList<UserDTO>();

    //SIMULAÇÃO DE UM BANCO NA MEMÓRIA
    @PostConstruct
    public void initiateList() {
        UserDTO userDTO = new UserDTO();
        userDTO.setNome("Eduardo");
        userDTO.setCpf("123");
        userDTO.setEndereco("Rua	a");
        userDTO.setEmail("eduardo@email.com");
        userDTO.setTelefone("1234-3454");
        userDTO.setDataCadastro(LocalDateTime.now());

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setNome("Luiz");
        userDTO2.setCpf("456");
        userDTO2.setEndereco("Rua	b");
        userDTO2.setEmail("luiz@email.com");
        userDTO2.setTelefone("1234-3454");
        userDTO2.setDataCadastro(LocalDateTime.now());

        UserDTO userDTO3 = new UserDTO();
        userDTO3.setNome("Bruna");
        userDTO3.setCpf("678");
        userDTO3.setEndereco("Rua	c");
        userDTO3.setEmail("bruna@email.com");
        userDTO3.setTelefone("1234-3454");
        userDTO3.setDataCadastro(LocalDateTime.now());

        UserDTO userDTO4 = new UserDTO();
        userDTO4.setNome("Tester");
        userDTO4.setCpf("6782");
        userDTO4.setEndereco("Rua b");
        userDTO4.setTelefone("1324-3454");
        userDTO4.setDataCadastro(LocalDateTime.now());

        usuarios.add(userDTO);
        usuarios.add(userDTO2);
        usuarios.add(userDTO3);
        usuarios.add(userDTO4);
    }

    //SALVAR OS USERDTO CRIADOS
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO newUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.save(userDTO);
    }

    //APAGAR USERDTO ESPECÍFICO
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    //CHAMAR A LISTAGEM COMPLETA DE TODOS OS USERDTO
    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAll();
    }

    //PEGAR UM DTO ESPECÍFICO POR ID
    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    //PROCURAR UM DTO ESPECÍFICO POR NOME :http://localhost:8080/user/search?nome=mar%
    @GetMapping("/search")
    public List<UserDTO> queryByName(
            @RequestParam(name = "nome", required = true) String nome) {
        return userService.queryByName(nome);
    }

    //PROCURAR UM DTO ESPECÍFICO POR CPF
    @GetMapping("/{cpf}/cpf")
    UserDTO findByCpf(@RequestParam(name = "key", required = true) String key, @PathVariable String cpf){
        return userService.findByCpf(cpf, key);
    }

    //FAZER UMA PAGINAÇÃO
    @GetMapping("/pageable")
    public Page<UserDTO> getUserPage (Pageable pageable) {
        return userService.getAllPage(pageable);
    }

    //COMO EDITAR UM USUÁRIO COM O ID
    @PatchMapping("/{id}")
    public UserDTO editUser(@PathVariable Long id,
                            @RequestBody UserDTO userDTO) {
        return userService.editUser(id, userDTO);
    }
}
