package vn.hdweb.team9.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hdweb.team9.domain.entity.Food;

import java.util.Optional;

@Repository
public interface IFoodRepository extends JpaRepository<Food, Long> {
    Food findById(long id);

    Optional<Food> findBySlug(String slug);
}
