package com.freepath.devpath.email.command.application.controller;

import com.freepath.devpath.common.dto.ApiResponse;
import com.freepath.devpath.email.command.application.Dto.NewsRequestDto;
import com.freepath.devpath.email.command.application.service.NewsService;
import com.freepath.devpath.email.command.domain.domain.News;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class NewsController {

    private final NewsService newsService;

    // 뉴스 저장
    @PostMapping("/news")
    public ResponseEntity<ApiResponse<News>> saveNews(@RequestBody NewsRequestDto dto) {
        News news = newsService.saveNews(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(news));
    }

    // 뉴스 직접 발송
    @PostMapping("/send/{newsId}")
    public ResponseEntity<ApiResponse<Void>> sendNews(@PathVariable int newsId) {
        newsService.sendNewsToSubscribers(newsId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // 메일 스케쥴링 테스트 코드
    @GetMapping("/test/sendNews")
    public String testSendNews() {
        newsService.sendNewsForToday();  // 스케줄링 메서드 수동 호출
        return "메일 전송 테스트 완료";
    }

    @PutMapping("/news/{newsId}")
    public ResponseEntity<ApiResponse<Void>> updateNews(@PathVariable int newsId, @RequestBody NewsRequestDto dto) {
        newsService.updateNews(newsId, dto);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // 뉴스 삭제
    @DeleteMapping("/news/{newsId}")
    public ResponseEntity<ApiResponse<Void>> deleteNews(@PathVariable int newsId) {
        newsService.deleteNews(newsId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
