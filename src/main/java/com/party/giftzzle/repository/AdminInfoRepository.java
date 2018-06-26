package com.party.giftzzle.repository;

import com.party.giftzzle.domain.AdminInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data JPA repository for the AdminInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdminInfoRepository extends JpaRepository<AdminInfo, Long> {

  Optional<AdminInfo> findOneByUserId(Long userId);

  Long deleteByUserId(Long userId);

//    @Query(value = "FROM appointment.admin_info I INNER JOIN appointment.jhi_user U ON U.id = I.user_id " +
//        " INNER JOIN appointment.jhi_user_authority UA ON UA.user_id = U.id " +
//        " WHERE U.login <> :login AND UA.authority_name = 'ROLE_ADMIN' ", nativeQuery = true)
//    Page<AdminInfo> findAllAdminInfoByLoginNot(Pageable pageable, @Param("login") String login);
}
