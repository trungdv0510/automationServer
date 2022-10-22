package autoServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import autoServer.Entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>{
	@Query(value = "select count(*) from user where username = :username and password = :password",nativeQuery = true)
    int login(@Param("username") String user, @Param("password") String pass);
	@Query(value = "select * from user where username = :username",nativeQuery = true)
    UserEntity findByUsername(@Param("username") String user);
}
