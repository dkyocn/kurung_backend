package com.kurung.community.service;

import com.kurung.common.enumeration.HealthType;
import com.kurung.community.dto.CommunityDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommunityService {

  CommunityDTO getCommunityById(int id);

  Page<CommunityDTO> getCommunityByPage(Pageable pageable, HealthType healthType, String keyword);

  void createCommunity(CommunityDTO communityDTO);

  void updateCommunity(CommunityDTO communityDTO);

}
