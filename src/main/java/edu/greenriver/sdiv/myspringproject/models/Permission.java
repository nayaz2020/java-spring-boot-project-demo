package edu.greenriver.sdiv.myspringproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;

/**
 * @author nemat
 * @version 1.2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Permission implements GrantedAuthority
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int permission;
    private String role;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @Override
    public String getAuthority()
    {
        return role;
    }
}
