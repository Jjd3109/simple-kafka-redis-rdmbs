package com.example.simplekafkaredisrdmbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.simplekafkaredisrdmbs.entity.ViewCount;

@Repository
public interface ViewCountRepository extends JpaRepository<ViewCount, Long> {

	@Modifying
	@Query(
		nativeQuery = true,
		value = "UPDATE view_count SET view_count = :viewCount WHERE id = :contentId"
	)
	void updateViewCount(@Param("contentId") Long contentId, @Param("viewCount") Long viewCount);

}
