package cn.hxzy.herbtool02.Service;

import java.util.List;

import cn.hxzy.herbtool02.Herb.Herb;
import cn.hxzy.herbtool02.Repository.HerbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HerbService {
    private final HerbRepository herbRepository;

    @Autowired
    public HerbService(HerbRepository herbRepository) {
        this.herbRepository = herbRepository;
    }

    public Herb addHerb(String name, String category,String effect) {
        if (herbRepository.existsByName(name)) {
            throw new RuntimeException("药材名称已存在");
        }
        Herb herb = new Herb();
        herb.setName(name);
        herb.setCategory(category);
        herb.setEffect(effect);
        return herbRepository.save(herb);
    }

    private boolean isHerbNameExists(String name) {
        return herbRepository.findByName(name) != null;
    }

    public String classify(String herbName) {
        Herb herb = herbRepository.findByName(herbName);
        if (herb != null) {
            return herb.getCategory() + "\n" + herb.getEffect();
        }
        return "未找到该药材";
    }


    public List<Herb> fuzzySearch(String keyword) {
        return herbRepository.findByNameContainingIgnoreCase(keyword);
    }

    public List<Herb> getAllHerbs() {
        return herbRepository.findAll();
    }

    public List<Herb> getHerbsByCategory(String category) {
        return herbRepository.findByCategory(category);
    }

    public List<Herb> searchHerbs(String keyword) {
        return herbRepository.findByNameContainingIgnoreCase(keyword);
    }
    public List<Herb> searchByEffect(String effect) {
        return herbRepository.findByEffectContainingIgnoreCase(effect);
    }

}

