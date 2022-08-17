package com.hamitmizrak.business.service.impl;

import com.hamitmizrak.bean.PasswordEncoderBean;
import com.hamitmizrak.business.dto.UserRegisterDto;
import com.hamitmizrak.business.service.IUserServices;
import com.hamitmizrak.data.entity.RoleRegisterEntity;
import com.hamitmizrak.data.entity.UserRegisterEntity;
import com.hamitmizrak.data.repository.IUserRegisterRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//Database
@Transactional

@Service
@Log4j2
public class UserServivesImpl implements IUserServices {

    //Password Maskeleyerek saklamak
    @Autowired
    PasswordEncoderBean passwordEncoderBean;

    // 1.YOL
    // @Autowired
    // IUserRegisterRepository repository;

    // 2.YOL
    IUserRegisterRepository repository;

    //parametreli constructor
    @Autowired
    public UserServivesImpl(IUserRegisterRepository repository) {
        this.repository = repository;
    }

    // Kullanıcıyı Database kaydetmek
    @Override
    public UserRegisterDto saveUserRegister(UserRegisterDto dto) {
        //RolesRegisterEntity
        List<RoleRegisterEntity> roleRegisterEntityList=new ArrayList<>();
        roleRegisterEntityList.add(new RoleRegisterEntity("ROLES_USER"));

        //UserRegisterEntity
        UserRegisterEntity userRegisterEntity=UserRegisterEntity.builder()
                //Dikkat: Encode decoder bir şifreleme değildir amaça giden yoldur.
                .name(dto.getName()).surname(dto.getSurname()).email(dto.getEmail())
                .password(passwordEncoderBean.passwordEncoderMethod().encode(dto.getPassword()))
                .relationRoleRegisterEntities(roleRegisterEntityList)
                .build();
        repository.save(userRegisterEntity);
        return dto;
    }


    //UserDetailsService: Sistemdeki kullanıcı ile etkileşimde bulunmak için kullanıyoruz.
    //public interface IUserServices  extends UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // UserRegisterEntity: Önce database email bulmamız gerekiyor.
        UserRegisterEntity userRegisterEntity=repository.findByEmail(email);

        //sistemde bir kullanıcı yoksa
        if(userRegisterEntity==null){
            throw new UsernameNotFoundException("Geçersiz Kullanıcı emaili  veya Şifreyi yanlış girdiniz");
        }

        //roleAuthories
        //USER ==> sistemde eklecek veriye kullanıcı emaili ve şifresi buraya ekle
        //Dikkat: Roles ==>  private Collection<RoleRegisterEntity> relationRoleRegisterEntities;
        return new org.springframework.security.core.userdetails.User(userRegisterEntity.getEmail(),userRegisterEntity.getPassword(),roleAuthories(userRegisterEntity.getRelationRoleRegisterEntities()));
    }

    //Kullanıcı Rol metotumuz
    private Collection<? extends GrantedAuthority> roleAuthories(Collection<RoleRegisterEntity> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

    //sistemde email ve şifreyi almak
    public UserRegisterEntity findByEmailAndPassword(String email, String password){
   return repository.findByEmailAndPassword(email, password);
    }
}