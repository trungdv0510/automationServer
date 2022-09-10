package autoServer.repository.impl;

import autoServer.DTO.RegresstionDto;
import autoServer.Entity.RegresstionEntity;
import autoServer.Utils.FunctionUtils;
import autoServer.Utils.contains.ResultSetMapping;
import autoServer.repository.RegresstionInteface;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class RegresstionImpl implements RegresstionInteface {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<RegresstionDto> getListRegresstionTest(Date startDate, Date endDate, Integer sprint) {
        StringBuilder queryStr = new StringBuilder("SELECT * FROM regresstion WHERE testcaseName IS NOT NULL ");
        if (FunctionUtils.checkIsNull(startDate)){
            queryStr.append("AND dateRun >= :startDate ");
        }
        if (FunctionUtils.checkIsNull(endDate)){
            queryStr.append("AND dateRun <= :endDate ");
        }
        if (FunctionUtils.checkIsNull(sprint)){
            queryStr.append("AND sprint = :sprint ");
        }else{
            queryStr.append("AND sprint = (SELECT DISTINCT sprint FROM regresstion ORDER BY sprint DESC LIMIT 1)");
        }
        queryStr.append(" ORDER BY dateRun DESC ");
        Query query = em.createNativeQuery(queryStr.toString(), ResultSetMapping.REGRESSTION_MAPPTING);
        if (FunctionUtils.checkIsNull(startDate)){
            query.setParameter("startDate",startDate);
        }
        if (FunctionUtils.checkIsNull(endDate)){
            query.setParameter("endDate",endDate);
        }
        if (FunctionUtils.checkIsNull(sprint)){
            query.setParameter("sprint",sprint);
        }
        return query.getResultList();
    }

    @Override
    public List<String> getSprint() {
        String queryStr = "SELECT DISTINCT sprint  FROM regresstion ORDER BY sprint ASC";
        Query query = em.createNativeQuery(queryStr);
        return parseQuery(query.getResultList());
    }


    public List<String> parseQuery(List<Object[]> listResult){
        List<String> strings = new ArrayList<>();
        for (Object[] item: listResult) {
            strings.add(String.valueOf(item[0]));
        }
        return strings;
    }
}
