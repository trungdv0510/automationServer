package autoServer.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="user")
public class UserEntity{
	@Id
	@Column(length = 64,nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String permission= "";
	@Column(nullable = false)
	private String role= "";
	private boolean active = true;
}
