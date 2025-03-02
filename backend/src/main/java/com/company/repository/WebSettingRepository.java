package com.company.repository;

import com.company.entity.WebSettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WebSettingRepository extends JpaRepository<WebSettingEntity, Long> {
    Optional<WebSettingEntity> findByKey(String key);
}
