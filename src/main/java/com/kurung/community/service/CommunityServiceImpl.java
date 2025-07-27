package com.kurung.community.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.enumeration.HealthType;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.common.exception.CustomRunTimeException;
import com.kurung.common.security.service.SessionService;
import com.kurung.community.dto.CommentDTO;
import com.kurung.community.dto.CommunityDTO;
import com.kurung.community.entity.CommentEntity;
import com.kurung.community.entity.CommunityEntity;
import com.kurung.community.repository.CommentRepository;
import com.kurung.community.repository.CommunityRepository;
import com.kurung.favorites.dto.FavoritesDTO;
import com.kurung.favorites.enumeration.FavoritesType;
import com.kurung.favorites.service.FavoritesService;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

  private final SessionService sessionService;
  private final FavoritesService favoritesService;
  private final CommentRepository commentRepository;
  private final CommunityRepository communityRepository;

  @Override
  public CommunityDTO getCommunityById(int id) {
    CommunityEntity communityEntity = communityRepository.getCommunityById(id);

    if (communityEntity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.COMMUNITY_NOT_FOUND) ;
    }

    List<FavoritesDTO> allFavoritesList = favoritesService.getALLFavoritesList(
        communityEntity.getCommunityId(), FavoritesType.COMMUNITY);

    return CommunityDTO.toCommunityBuilder().communityEntity(communityEntity).favorites(allFavoritesList).build();
  }

  @Override
  public Page<CommunityDTO> getCommunityByPage(Pageable pageable, HealthType healthType, String keyword) {

    Page<CommunityEntity> communityByPage = communityRepository.getCommunityByPage(pageable,
        keyword, healthType);
    return communityByPage.map(communityEntity ->  CommunityDTO.toCommunityBuilder()
        .communityEntity(communityEntity)
        .favorites(favoritesService.getALLFavoritesList(communityEntity.getCommunityId(),
            FavoritesType.COMMUNITY))
        .build());
  }

  @Override
  public void createCommunity(CommunityDTO communityDTO) {
    UserDTO userDTO = sessionService.getUserFromToken();

    try {
      communityRepository.save(CommunityEntity.toCommunityBuilder().communityDTO(communityDTO).user(userDTO).build());
    } catch (RuntimeException e) {
      throw new CustomRunTimeException(CustomHttpStatus.COMMUNITY_SAVE_ERROR);
    }

  }

  @Override
  @Transactional
  public void updateCommunity(CommunityDTO communityDTO) {
    UserDTO userDTO = sessionService.getUserFromToken();

    CommunityEntity communityEntity = communityRepository.getCommunityById(
        communityDTO.getCommunityId());

    if (communityEntity == null || !Objects.equals(communityEntity.getUser().getUserUuid(),
        userDTO.getUserUuid())) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.COMMUNITY_NOT_FOUND);
    }

    try {
      communityEntity.updateCommunity(communityDTO);
    } catch (RuntimeException e) {
      throw new CustomRunTimeException(CustomHttpStatus.COMMUNITY_UPDATE_ERROR);
    }
  }

  @Override
  public void deleteCommunity(int id) {
    CommunityEntity communityEntity = communityRepository.getCommunityById(id);
    if (communityEntity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.COMMUNITY_NOT_FOUND);
    }

    try {
      // 추후 본인 확인 추가
      communityRepository.delete(communityEntity);
    } catch (RuntimeException e) {
      throw new CustomRunTimeException(CustomHttpStatus.COMMUNITY_DELETE_ERROR);
    }

  }

  @Override
  public CommentDTO createComment(int id, CommentDTO commentDTO) {

    UserDTO userDTO = sessionService.getUserFromToken();

    CommunityEntity communityEntity = communityRepository.getCommunityById(id);

    commentRepository.save(CommentEntity.builder()
            .content(commentDTO.getContent())
            .user(UserEntity.createUserBuilder()
                .userDTO(userDTO).build())
            .community(communityEntity)
        .build());

    return null;
  }

  @Override
  public void deleteComment(int id) {
    CommentEntity comment = commentRepository.getComment(id);

    if (comment == null || !(comment.getUser().getUserUuid().equals(sessionService.getUserFromToken().getUserUuid()))) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.COMMUNITY_NOT_FOUND);
    }

    commentRepository.delete(comment);
  }
}
