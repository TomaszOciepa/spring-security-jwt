package pl.tom.springsecurityjwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class JwtFilter extends BasicAuthenticationFilter {

    public JwtFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {



        String header = request.getHeader("Authorization");

        if (header != null){
            UsernamePasswordAuthenticationToken authResult = getAuthenticationByToken(header);

            SecurityContextHolder.getContext().setAuthentication(authResult);

        }

        chain.doFilter(request, response);



    }

    private UsernamePasswordAuthenticationToken getAuthenticationByToken(String header) {

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey("Je?,mFt|K5@?zf9".getBytes())
                .parseClaimsJws(header.replace("Bearer ", ""));

        String username = claimsJws.getBody().get("name").toString();
        String role = claimsJws.getBody().get("role").toString();
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = Collections.singleton(new SimpleGrantedAuthority(role));


        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);

        return usernamePasswordAuthenticationToken;
    }
}
