package com.example.demo.repository;

import com.example.demo.model.JobDetailModel;
import com.example.demo.model.JobModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JobRepository {

    static Logger logger = LoggerFactory.getLogger(JobModel.class);
    @PersistenceContext
    EntityManager entityManager;

    public List<JobModel> getJobList() {
        try {
            Query query = entityManager.createNativeQuery("select * from job");
            List<Object[]> result = query.getResultList();
            List<JobModel> listDto = new ArrayList<>();
            result.stream().forEach((ans) -> {
                JobModel dto = new JobModel();
                dto.setJobid((Integer) ans[0]);
                dto.setJobname((String) ans[1]);
                listDto.add(dto);
            });
            return listDto;
        } catch (Exception e) {
            logger.error("error",e);
        }
        return null;
    }

    public List<JobDetailModel> getJobDetailList(String name) {
        try {
            Query query = entityManager.createNativeQuery("select * from jobDetail  where Name = '"+name+"'");
            List<Object[]> result = query.getResultList();
            List<JobDetailModel> listDto = new ArrayList<>();
            result.stream().forEach((ans) -> {
                JobDetailModel dto = new JobDetailModel();
                dto.setJobdetailid((Integer) ans[0]);
                dto.setName((String) ans[1]);
                dto.setDetail((String) ans[2]);
                listDto.add(dto);
            });
            return listDto;
        } catch (Exception e) {
            logger.error("error",e);
        }
        return null;
    }
}
