package com.kurung.community.repository;

import com.kurung.community.entity.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<CommunityEntity, Integer>, CommunityRepositorySupport {

}
