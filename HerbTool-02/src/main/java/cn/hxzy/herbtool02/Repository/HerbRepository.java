package cn.hxzy.herbtool02.Repository;

import java.util.List;

import cn.hxzy.herbtool02.Herb.Herb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HerbRepository extends JpaRepository<Herb, Integer> {
    Herb findByName(String name);

    List<Herb> findByNameContainingIgnoreCase(String keyword);

    boolean existsByName(String name);

    List<Herb> findByCategory(String category);

    List<Herb> findByEffectContainingIgnoreCase(String effect);
}

