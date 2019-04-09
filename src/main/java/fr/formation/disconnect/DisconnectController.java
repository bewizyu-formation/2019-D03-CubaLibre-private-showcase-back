package fr.formation.disconnect;

import fr.formation.controllers.AbstractController;
import fr.formation.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/disconnect")
public class DisconnectController extends AbstractController {

    @GetMapping("/")
    /*public void disconnect(HttpServletRequest request) {
        System.out.println("Date changed");
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        if (token != null) {
            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody();

            claims.setExpiration(new Date(System.currentTimeMillis()));
            System.out.println("Date changed");
        }
    }*/

    public void disconnect(){
        System.out.println("Date changed");
    }
}
