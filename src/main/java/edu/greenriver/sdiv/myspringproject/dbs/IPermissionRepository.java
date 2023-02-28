package edu.greenriver.sdiv.myspringproject.dbs;

import edu.greenriver.sdiv.myspringproject.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author nemat
 * @version 1.2
 */
public interface IPermissionRepository extends JpaRepository<Permission, Integer> {
}
