package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Idea;
import com.example.entity.IdeaStatus;

import jakarta.transaction.Transactional;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long> {
	 List<Idea> findByUserId(int userId);
	 List<Idea> findByStatus(IdeaStatus status);
	 @Transactional
	    @Modifying
	    @Query("UPDATE Idea i SET i.status = :status WHERE i.ideaId = :ideaId")
	    void updateIdeaStatus(int ideaId, IdeaStatus status);
	 
	 List<Idea> findAllByOrderByTotalMarksDesc();
	 
}
