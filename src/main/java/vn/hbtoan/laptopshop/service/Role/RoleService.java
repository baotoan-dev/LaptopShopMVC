package vn.hbtoan.laptopshop.service.Role;

import vn.hbtoan.laptopshop.domain.Role;
import vn.hbtoan.laptopshop.repository.Role.RoleRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
