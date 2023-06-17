package org.project.gongsamo.controller;

import lombok.RequiredArgsConstructor;
import org.project.gongsamo.service.ViewCountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viewcount")
@RequiredArgsConstructor
public class ViewCountController {
    private final ViewCountService viewCountService;

    @PostMapping("/hit")
    public Long hitViewCount(@RequestParam("articleId") Long articleId) {
        return viewCountService.hitViewCount(articleId);
    }

    @GetMapping("/get")
    public Long getViewCount(@RequestParam("articleId") Long articleId) {
        return viewCountService.getViewCount(articleId);
    }
}


