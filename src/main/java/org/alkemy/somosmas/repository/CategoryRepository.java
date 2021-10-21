package org.alkemy.somosmas.repository;



import org.alkemy.somosmas.model.Category;
import org.alkemy.somosmas.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Long >{

    Category findByName(String categoryName);
    
    List<Category> findByDeletedFalse();
}
