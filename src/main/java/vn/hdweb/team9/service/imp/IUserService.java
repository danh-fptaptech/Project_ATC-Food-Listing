package vn.hdweb.team9.service.imp;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;
import vn.hdweb.team9.domain.dto.request.SignUpDto;

import vn.hdweb.team9.domain.dto.respon.UserDto;
import vn.hdweb.team9.domain.entity.User;

public interface IUserService extends UserDetailsService {
    UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;

    User save(SignUpDto user);

    UserDto findByEmail(String userEmail);

    void uploadAvatar(MultipartFile avatar, String email);

    void userUpdate(UserDto userDto, String userEmail);
}
