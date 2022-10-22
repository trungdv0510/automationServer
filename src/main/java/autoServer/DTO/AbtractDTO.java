package autoServer.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public abstract class AbtractDTO<T> {
	@NotEmpty(message = "UUID is empty")
	private String uuid;
	private String modifyBy;
	private LocalDateTime  createDate =  LocalDateTime.now();
	private LocalDateTime  modifyDate =  LocalDateTime.now();
	private String result;
	List<T> list = new ArrayList<T>();
	
}
