package autoServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import autoServer.Entity.UserEntity;

public interface userRepository  extends JpaRepository<UserEntity,Long>{

}
