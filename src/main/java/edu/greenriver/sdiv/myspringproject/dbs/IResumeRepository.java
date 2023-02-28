package edu.greenriver.sdiv.myspringproject.dbs;

import edu.greenriver.sdiv.myspringproject.models.ResumeData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author nemat
 * @version 1.2
 * Resume Data Repository "Interface" for JPA implementations
 * and self modified methods
 */
public interface IResumeRepository extends JpaRepository<ResumeData,Integer> {
}
