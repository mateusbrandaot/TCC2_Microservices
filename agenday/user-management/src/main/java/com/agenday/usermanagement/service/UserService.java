package com.agenday.usermanagement.service;

import com.agenday.usermanagement.config.SecurityConfiguration;
import com.agenday.usermanagement.dto.CreateUserDto;
import com.agenday.usermanagement.dto.LoginUserDto;
import com.agenday.usermanagement.dto.RecoveryUserDto;
import com.agenday.usermanagement.dto.UserResponseDTO;
import com.agenday.usermanagement.exception.UserException;
import com.agenday.usermanagement.model.Institution;
import com.agenday.usermanagement.model.Role;
import com.agenday.usermanagement.model.User;
import com.agenday.usermanagement.model.impl.UserDetailsImpl;
import com.agenday.usermanagement.repository.InstitutionRepository;
import com.agenday.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    @Autowired
    private InstitutionRepository institutionRepository;

    // Método responsável por autenticar um usuário e retornar um token JWT
    public RecoveryUserDto authenticateUser(LoginUserDto loginUserDto) {
        try {
            // Cria um objeto de autenticação com o email e a senha do usuário
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

            // Autentica o usuário com as credenciais fornecidas
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            // Obtém o objeto UserDetails do usuário autenticado
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            List<Institution> institution = institutionRepository.findByUserId(userDetails.getUser().getId());
            Long instituitionId = null;
            if(institution != null && !institution.isEmpty()) {
                instituitionId = institution.stream().findFirst().get().getId();
            }
            return new RecoveryUserDto(userDetails.getUser().getId(), userDetails.getUsername(), jwtTokenService.generateToken(userDetails),instituitionId);
        } catch (Exception e) {
            throw new UserException("Senha incorreta");
        }
    }

    // Método responsável por criar um usuário
    public UserResponseDTO createUser(CreateUserDto createUserDto) throws Exception{
            // Verifica se o email já está cadastrado
            if (userRepository.existsByEmail(createUserDto.email())) {
                throw new UserException("Email já cadastrado");
            }
            try {
                // Cria um novo usuário com os dados fornecidos
                User newUser = User.builder()
                        .email(createUserDto.email())
                        // Codifica a senha do usuário com o algoritmo bcrypt
                        .password(securityConfiguration.passwordEncoder().encode(createUserDto.password()))
                        // Atribui ao usuário uma permissão específica
                        .roles(List.of(Role.builder().name(createUserDto.role()).build()))
                        .name(createUserDto.name())
                        .build();

                // Salva o novo usuário no banco de dados
                User user = userRepository.save(newUser);
                UserResponseDTO userResponseDTO = new UserResponseDTO();
                userResponseDTO.setUserId(newUser.getId());
                return userResponseDTO;
        } catch(Exception e) {
            throw new Exception("Erro ao Criar usuario no banco de dados ");
        }
    }
}
