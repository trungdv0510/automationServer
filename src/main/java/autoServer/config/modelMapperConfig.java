package autoServer.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.NoArgsConstructor;
/*
	Chiến lược map chuẩn: MatchingStrategies.STANDARD( không quan tâm thứ tự -  mọi từ trong tên thuộc tính nguồn phải tồn tại trong tên thuộc tính đích.)
	Chiến lược map lỏng lẻo: MatchingStrategies.LOOSE(không quan tâm thứ tự - từ cuối cùng phải có trong tên thuọc tính đích)
	Chiến lược map chặt chẽ: MatchingStrategies.STRICT(thứ tự phải đúng - mọi từ trong thuộc tính nguồn phải khớp với toàn bộ từ)
*/
@NoArgsConstructor
@Configuration
public class modelMapperConfig {
	@Bean
	  public ModelMapper modelMapper() {
	    // Tạo object và cấu hình
	    ModelMapper modelMapper = new ModelMapper();
	    modelMapper.getConfiguration()
	            .setMatchingStrategy(MatchingStrategies.STRICT).setSkipNullEnabled(true);
	    return modelMapper;
	}
}
