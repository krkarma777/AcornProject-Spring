package com.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.ContentDTO;
import com.dto.board.PostPageDTO;
import com.service.PostService;
import com.service.ReviewService;

@Controller
public class MainCotroller {

    @Autowired
    PostService service;
    @Autowired
    ReviewService rService;

    @GetMapping("/")
    public String mainView(Model model, @RequestParam(value = "cg", required = false) String category) {
        String nextPage = "main";

        List<PostPageDTO> movieList = service.selectAll(new HashMap<String, String>() {
            {
                put("board", "movie");
                put("postCount", "5");
            }
        });

        List<PostPageDTO> movieMeetList = service.selectAll(new HashMap<String, String>() {
            {
                put("board", "movieMeet");
                put("postCount", "5");
            }
        });

        List<PostPageDTO> movieInfoList = service.selectAll(new HashMap<String, String>() {
            {
                put("board", "movieInfo");
                put("postCount", "5");
            }
        });

        model.addAttribute("movieList", movieList);
        model.addAttribute("movieMeetList", movieMeetList);
        model.addAttribute("movieInfoList", movieInfoList);

        if (category != null) {
            switch (category) {
            case "movie":
            	//영화 가져오기(인기 순은 아직)
	    		List<ContentDTO> movieTopList = rService.selectTop();
	    		model.addAttribute("movieTopList", movieTopList);
                nextPage = "movieHome";
                break;
            case "book":
                nextPage = "BookHome";
                break;
            case "tv":
                nextPage = "TvHome";
                break;
            default:
                nextPage = "main";
                break;
            }
        }
        return nextPage;
    }

}