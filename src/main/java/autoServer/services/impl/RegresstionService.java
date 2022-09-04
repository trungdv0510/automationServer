package autoServer.services.impl;

import autoServer.Converter.RegresstionMapping;
import autoServer.DTO.RegresstionDto;
import autoServer.Entity.RegresstionEntity;
import autoServer.Utils.contains;
import autoServer.repository.RegresstionInteface;
import autoServer.repository.RegresstionRepository;
import autoServer.services.IConfigService;
import autoServer.services.IRegresstionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RegresstionService implements IRegresstionServices {
    @Autowired
    private RegresstionInteface regresstionInteface;
    @Autowired
    private RegresstionRepository repository;
    @Autowired
    private RegresstionMapping regresstionMapping;
    @Autowired
    private IConfigService configService;
    @Override
    public List<RegresstionDto> getListRegresstionDto(Date startDate, Date endDate, Integer sprint){
        return regresstionInteface.getListRegresstionTest(startDate,endDate,sprint);
    }
    @Override
    public void save(RegresstionDto regresstionDto){
        String configRegress = configService.getValueConfig(contains.IS_REGRESS);
        if (configRegress.equalsIgnoreCase(contains.IS_REGRESS)){
            RegresstionEntity regresstionEntity = regresstionMapping.toEntity(regresstionDto);
            repository.save(regresstionEntity);
        }
    }
}
