package autoServer.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public abstract class AbtractDTO<T> {
	private Long id;
	@NotEmpty(message = "UUID is empty")
	private String UUID;
	@NotEmpty(message = "createBy is empty")
	private String createBy;
	private String modifyBy;
	private LocalDateTime  createDate =  LocalDateTime.now();
	private LocalDateTime  modifyDate =  LocalDateTime.now();
	private String result;
	List<T> list = new ArrayList<T>();
	
}
