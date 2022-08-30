package autoServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import autoServer.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long>{
	@Query(value = "select count(*) from user where username = :username and password = :password",nativeQuery = true)
	public int login(@Param("username") String user, @Param("password") String pass);
	@Query(value = "select * from user where username = :username",nativeQuery = true)
	public UserEntity findByUsername(@Param("username") String user);
}
