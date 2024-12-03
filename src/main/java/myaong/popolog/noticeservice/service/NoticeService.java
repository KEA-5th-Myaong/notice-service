package myaong.popolog.noticeservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import myaong.popolog.noticeservice.common.exception.ApiCode;
import myaong.popolog.noticeservice.common.exception.ApiException;
import myaong.popolog.noticeservice.dto.request.NoticeRequest;
import myaong.popolog.noticeservice.dto.response.NoticeResponse;
import myaong.popolog.noticeservice.entity.Notice;
import myaong.popolog.noticeservice.feign.service.NotificationFeignService;
import myaong.popolog.noticeservice.feign.constant.NotificationType;
import myaong.popolog.noticeservice.repository.NoticeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final NotificationFeignService notificationFeignService;

    @Transactional
    public NoticeResponse createNotice(Long memberId, NoticeRequest request) {
        validateNoticeRequest(request);

        Notice notice = noticeRepository.save(
                Notice.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .isImportant(request.getIsImportant())
                        .build()
        );

        // 알림 전송
        sendNotificationForNewNotice(notice, memberId);

        return NoticeResponse.builder()
                .noticeId(notice.getId())
                .build();
    }

    private void sendNotificationForNewNotice(Notice notice, Long memberId) {
        String title = "새로운 공지사항이 등록되었습니다!";
        String content = notice.getTitle();
        String url = "/notices/" + notice.getId(); // 공지사항 URL

        // 알림 전송 (모든 사용자 대상으로)
        // TODO: 모든 사용자를 대상으로 하는 로직 구현
        notificationFeignService.sendNotification(
                0L, // 0L로 "모든 사용자"를 대상으로 설정
                title,
                content,
                url,
                NotificationType.NOTICE,
                memberId
        );
    }

    private void validateNoticeRequest(NoticeRequest request) {
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new ApiException(ApiCode.NOTICE_NOT_FOUND);
        }
        if (request.getContent() == null || request.getContent().trim().isEmpty()) {
            throw new ApiException(ApiCode.INVALID_DATA);
        }
    }
}