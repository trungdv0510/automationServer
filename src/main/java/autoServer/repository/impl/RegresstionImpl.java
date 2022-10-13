package autoServer.repository.impl;

import autoServer.DTO.RegresstionDto;
import autoServer.Utils.FunctionUtils;
import autoServer.repository.RegresstionInteface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Repository
public class RegresstionImpl implements RegresstionInteface {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<RegresstionDto> getListRegresstionTest(Date startDate, Date endDate, String sprint) {
        StringBuilder queryStr = new StringBuilder("SELECT ")
                .append(" id as id, ")
                .append("testcase_name as testcaseName, ")
                .append("date_run as dateRun, ")
                .append("evidence_link as evidenceLink, ")
                .append("result as result, ")
                .append("error_description as ErrorDescription, ")
                .append("sprint as sprint, ")
                .append("testsuite_uuid as testsuiteUuid, ")
                .append("author as author ")
                .append(" FROM regresstion WHERE testcase_name IS NOT NULL ");
        if (FunctionUtils.checkIsNull(startDate)) {
            queryStr.append("AND date_run >= :startDate ");
        }
        if (FunctionUtils.checkIsNull(endDate)) {
            queryStr.append("AND date_run <= :endDate ");
        }
        if (FunctionUtils.checkIsNull(sprint)) {
            queryStr.append("AND sprint = :sprint ");
        } else {
            queryStr.append("AND sprint = (SELECT DISTINCT sprint FROM regresstion ORDER BY sprint DESC LIMIT 1)");
        }
        queryStr.append(" ORDER BY date_run DESC ");
        Query query = em.createNativeQuery(queryStr.toString());
        if (FunctionUtils.checkIsNull(startDate)) {
            query.setParameter("startDate", startDate);
        }
        if (FunctionUtils.checkIsNull(endDate)) {
            query.setParameter("endDate", endDate);
        }
        if (FunctionUtils.checkIsNull(sprint)) {
            query.setParameter("sprint", sprint);
        }
        return parseQuery(query.getResultList());
    }

    @Override
    public List<String> getSprint() {
        String queryStr = "SELECT DISTINCT sprint  FROM regresstion ORDER BY sprint DESC";
        Query query = em.createNativeQuery(queryStr);
        return parseSprint(query.getResultList());
    }

    @Override
    public Integer getTotalWithSprint(String sprintName, String result) {
        StringBuilder stringBuilder = new StringBuilder("SELECT COUNT(*) FROM regresstion WHERE sprint = '").append(sprintName).append("'");
        if (!FunctionUtils.isNullOrEmpty(result)){
            stringBuilder.append(" and result = '").append(result).append("'");
        }
        Query query = em.createNativeQuery(stringBuilder.toString());
        System.out.println(query.getResultList());
        return Integer.parseInt(query.getResultList().get(0).toString()) ;
    }

    public List<String> parseSprint(List<Object> listResult) {
        List<String> strings = new ArrayList<>();
        for (Object item : listResult) {
            strings.add(String.valueOf(item));
        }
        return strings;
    }

    public List<RegresstionDto> parseQuery(List<Object[]> listRegressionEntity){
        List<RegresstionDto> listRegress = new ArrayList<>();
        for (Object[] item : listRegressionEntity) {
            RegresstionDto dto = new RegresstionDto();
            dto.setId(FunctionUtils.isObjectNull(item[0])?null:FunctionUtils.parseToLong(item[0]));
            dto.setTestcaseName(FunctionUtils.isObjectNull(item[1])?null:FunctionUtils.parseToString(item[1]));
            dto.setDateRun(FunctionUtils.isObjectNull(item[2])?null:FunctionUtils.parseToLocalDatetime(item[2],"yyyy-MM-dd HH:mm:ss.SSSSSS"));
            dto.setEvidenceLink(FunctionUtils.isObjectNull(item[3])?null:FunctionUtils.parseToString(item[3]));
            dto.setResult(FunctionUtils.isObjectNull(item[4])?null:FunctionUtils.parseToString(item[4]));
            dto.setErrorDescription(FunctionUtils.isObjectNull(item[5])?null:FunctionUtils.parseToString(item[5]));
            dto.setSprint(FunctionUtils.isObjectNull(item[6])?null:FunctionUtils.parseToString(item[6]));
            dto.setTestsuiteUuid(FunctionUtils.isObjectNull(item[7])?null:FunctionUtils.parseToString(item[7]));
            dto.setAuthor(FunctionUtils.isObjectNull(item[8])?null:FunctionUtils.parseToString(item[8]));
            listRegress.add(dto);
        }
        return listRegress;
    }
}
