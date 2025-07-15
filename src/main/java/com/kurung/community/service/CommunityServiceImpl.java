package com.kurung.community.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.enumeration.HealthType;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.common.exception.CustomRunTimeException;
import com.kurung.community.dto.CommunityDTO;
import com.kurung.community.entity.CommunityEntity;
import com.kurung.community.repository.CommunityRepository;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

  private final UserService userService;
  private final CommunityRepository communityRepository;

  @Override
  public CommunityDTO getCommunityById(int id) {
    CommunityEntity communityEntity = communityRepository.getCommunityById(id);

    if (communityEntity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.COMMUNITY_NOT_FOUND) ;
    }

    return CommunityDTO.toCommunityBuilder().communityEntity(communityEntity).build();
  }

  @Override
  public Page<CommunityDTO> getCommunityByPage(Pageable pageable, HealthType healthType, String keyword) {

    Page<CommunityEntity> communityByPage = communityRepository.getCommunityByPage(pageable,
        keyword, healthType);
    return communityByPage.map(communityEntity ->  CommunityDTO.toCommunityBuilder().communityEntity(communityEntity).build());
  }

  @Override
  public void createCommunity(CommunityDTO communityDTO) {
    UserDTO userByUuid = userService.getUserByUuid(communityDTO.getUser().getUserUuid());

    try {
      communityRepository.save(CommunityEntity.toCommunityBuilder().communityDTO(communityDTO).user(userByUuid).build());
    } catch (RuntimeException e) {
      throw new CustomRunTimeException(CustomHttpStatus.COMMUNITY_SAVE_ERROR);
    }

  }
}
