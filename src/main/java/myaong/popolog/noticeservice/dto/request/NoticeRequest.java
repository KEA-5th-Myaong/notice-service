package myaong.popolog.noticeservice.dto.request;

import lombok.Getter;

@Getter
public class NoticeRequest {
    private String title;
    private String content;
    private Boolean isImportant;
}