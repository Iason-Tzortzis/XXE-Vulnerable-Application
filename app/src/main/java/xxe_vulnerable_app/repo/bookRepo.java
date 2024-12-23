package xxe_vulnerable_app.repo;

import xxe_vulnerable_app.domain.bookDomain;
import org.springframework.data.repository.CrudRepository;

public interface bookRepo extends CrudRepository<bookDomain, Long> {

}