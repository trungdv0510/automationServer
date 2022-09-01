package autoServer.Converter;

import autoServer.DTO.RegresstionDto;
import autoServer.Entity.RegresstionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegresstionMapping {
    @Autowired
    private ModelMapper modelmaper;
    public RegresstionDto toDto(RegresstionEntity entity){
        RegresstionDto Regresstion = null;
        try {
            Regresstion = modelmaper.map(entity, RegresstionDto.class);
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
        }
        return Regresstion;
    }

    public RegresstionEntity toEntity(RegresstionDto dto){
        RegresstionEntity Regresstion = null;
        try {
            Regresstion = modelmaper.map(dto, RegresstionEntity.class);
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
        }
        return Regresstion;
    }
}
