package com.kurung.community.repository;

import com.kurung.community.dto.CommunityDTO;
import com.kurung.community.entity.CommunityEntity;

public interface CommunityRepositorySupport {

  CommunityEntity getCommunityById(int id);
}
