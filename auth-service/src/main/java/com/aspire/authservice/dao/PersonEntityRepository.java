package com.aspire.authservice.dao;

import com.aspire.authservice.dao.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersonEntityRepository<T extends PersonEntity>  extends JpaRepository<T,Long> {

}
