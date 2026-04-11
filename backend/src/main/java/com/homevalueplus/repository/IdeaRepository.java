package com.homevalueplus.repository;

import com.homevalueplus.entity.Idea;
import com.homevalueplus.enums.Category;
import com.homevalueplus.enums.PropertyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long> {

    Page<Idea> findByCategory(Category category, Pageable pageable);

    Page<Idea> findByPropertyType(PropertyType propertyType, Pageable pageable);

    Page<Idea> findByCategoryAndPropertyType(Category category, PropertyType propertyType, Pageable pageable);

    List<Idea> findByCategory(Category category);

    long countByCategory(Category category);

    @Query("SELECT i.category, COUNT(i) FROM Idea i GROUP BY i.category")
    List<Object[]> countByCategories();
}
