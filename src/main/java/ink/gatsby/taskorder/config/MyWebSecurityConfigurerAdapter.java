package ink.gatsby.taskorder.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import ink.gatsby.taskorder.common.GlobalConstant;
import ink.gatsby.taskorder.dao.mapper.UserMapper;
import ink.gatsby.taskorder.pojo.po.UserDO;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    protected final UserMapper userMapper;

    public MyWebSecurityConfigurerAdapter(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userName -> {
            UserDO userDO = userMapper.selectOne(new LambdaQueryWrapper<UserDO>().eq(UserDO::getUserName, userName));
            if (userDO == null) {
                throw new UsernameNotFoundException("User does not exist");
            }
            String roleName = GlobalConstant.ADMIN_NAME.equals(userDO.getUserName()) ?
                    GlobalConstant.ROLE_ADMIN : GlobalConstant.ROLE_USER;
            return new User(userDO.getUserName(), userDO.getPassword(),
                    AuthorityUtils.createAuthorityList(roleName));
        }).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().permitAll()
                .and().authorizeRequests().anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/login")
                .and().rememberMe();
    }
}
