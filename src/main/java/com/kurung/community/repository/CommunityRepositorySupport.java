package com.kurung.community.repository;

import com.kurung.common.enumeration.HealthType;
import com.kurung.community.entity.CommunityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommunityRepositorySupport {

  CommunityEntity getCommunityById(int id);

  Page<CommunityEntity> getCommunityByPage(Pageable pageable, String keyword, HealthType healthType);
}
