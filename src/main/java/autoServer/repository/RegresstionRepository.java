package autoServer.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import autoServer.Entity.RegresstionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegresstionRepository extends JpaRepository<RegresstionEntity,Long>,RegresstionInteface{

}
