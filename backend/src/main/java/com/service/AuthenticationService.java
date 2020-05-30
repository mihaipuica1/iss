package com.service;

import com.entities.ApplicationUser;
import com.mapper.ProfileMapper;
import com.input.Authentication;
import com.model.Role;
import com.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class AuthenticationService {

    private ApplicationUserRepository applicationUserRepository;
    private PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AuthenticationService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Transactional
    public void register(Authentication input) {

        Optional<ApplicationUser> profile = applicationUserRepository.findById(input.getUserName());

        if (!profile.isPresent()) {
            ApplicationUser profileEntity = ProfileMapper.profileToEntity(input);

            //profileEntity.setRoles(new Role[1]);
            //profileEntity.getRoles()[0] = Role.valueOf(input.getRoles());
            profileEntity.setPassword(bCryptPasswordEncoder.encode(input.getPassword()));
            applicationUserRepository.save(profileEntity);

        } else {
            if (profile.get().getRoles() == null) {
                profile.get().setRoles(new Role[1]);
                profile.get().getRoles()[0] = Role.valueOf(input.getRoles());
            } else {
                Role[] roles = new Role[profile.get().getRoles().length + 1];

                System.arraycopy(profile.get().getRoles(), 0, roles, 0, profile.get().getRoles().length);
                roles[profile.get().getRoles().length] = Role.valueOf(input.getRoles());
                profile.get().setRoles(roles);
            }

            applicationUserRepository.save(profile.get());
        }
    }

    public Optional<ApplicationUser> login(Authentication profileInput) {

        return applicationUserRepository.findById(profileInput.getUserName());
    }
}
