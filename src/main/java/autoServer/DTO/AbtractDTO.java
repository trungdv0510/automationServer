package autoServer.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public abstract class AbtractDTO<T> {
	private Long id;
	private String UUID;
	private String createBy;
	private String modifyBy;
	private Date createDate;
	private Date modifyDate;
	private String result;
	List<T> list = new ArrayList<T>();
	
}
