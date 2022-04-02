package autoServer.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class abtractEntity {
	@Id
	@Column(nullable = false,length = 100)
	private String uuid;
	@Column 
	@LastModifiedBy
	private String modifyBy;
	@Column
	@CreatedDate
	private LocalDateTime createDate;
	@Column
	@LastModifiedDate
	private LocalDateTime modifyDate;
}
