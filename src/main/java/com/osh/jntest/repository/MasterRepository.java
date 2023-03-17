package com.osh.jntest.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MasterRepository {

    int countUsersByName(@Param("name") String name);


}
