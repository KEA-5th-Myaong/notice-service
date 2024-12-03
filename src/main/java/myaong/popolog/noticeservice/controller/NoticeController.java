package myaong.popolog.noticeservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import myaong.popolog.noticeservice.dto.request.NoticeRequest;
import myaong.popolog.noticeservice.dto.response.NoticeResponse;
import myaong.popolog.noticeservice.service.NoticeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @Operation(summary = "API 명세서 v0.4 line 124", description = "공지 작성")
    @PostMapping
    public ResponseEntity<NoticeResponse> createNotice(@RequestHeader("memberId") Long memberId, @RequestBody NoticeRequest request) {
        NoticeResponse response = noticeService.createNotice(memberId, request);
        return ResponseEntity.ok(response);
    }
}
