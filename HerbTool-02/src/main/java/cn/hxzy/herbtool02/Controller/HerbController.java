package cn.hxzy.herbtool02.Controller;

import cn.hxzy.herbtool02.Herb.Herb;
import cn.hxzy.herbtool02.Service.HerbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/herbs")

public class HerbController {
    private final HerbService herbService;

    @Autowired
    public HerbController(HerbService herbService) {
        this.herbService = herbService;
    }

    @PostMapping("/add")
    public Herb addHerb(@RequestParam String name, @RequestParam String category, @RequestParam String effect) {
        try {
            return herbService.addHerb(name, category, effect);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "药材名称已存在");
        }
    }

    @GetMapping("/classify")
    public String classify(@RequestParam String name) {
        return herbService.classify(name);
    }

    @GetMapping("/searchByCategory")
    public List<Herb> getHerbsByCategory(@RequestParam String category) {
        return herbService.getHerbsByCategory(category);
    }

    @GetMapping("/search")
    public List<Herb> fuzzySearch(@RequestParam String keyword) {
        return herbService.fuzzySearch(keyword);
    }

    @GetMapping("/all")
    public List<Herb> getAllHerbs() {
        return herbService.getAllHerbs();
    }

    @GetMapping("/searchByEffect")
    public List<Herb> searchByEffect(@RequestParam String effect) {
        return herbService.searchByEffect(effect);
    }
}

