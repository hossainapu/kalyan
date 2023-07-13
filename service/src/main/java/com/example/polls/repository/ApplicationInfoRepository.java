package com.example.polls.repository;

import com.example.polls.model.ApplicationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationInfoRepository extends JpaRepository<ApplicationInfo, Integer> {

    Optional<ApplicationInfo> findFirstByReferenceNumberOrderByIdDesc(String referenceNumber);
}
