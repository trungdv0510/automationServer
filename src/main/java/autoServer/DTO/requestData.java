package autoServer.DTO;


import javax.validation.constraints.NotEmpty;

import lombok.Data;
@Data
public class requestData {
	@NotEmpty
	public String dateStart;
	@NotEmpty
	public String dateEnd;
	
}
