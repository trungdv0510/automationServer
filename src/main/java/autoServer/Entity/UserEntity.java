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
	@Column(length = 64)
	private String userName;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String permission;
}
