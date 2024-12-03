package myaong.popolog.noticeservice.feign.constant;

import lombok.Getter;

@Getter
public enum NotificationType {
    NOTICE("NOTICE");

    private final String label;

    NotificationType(String label) {
        this.label = label;
    }
    public static NotificationType fromLabel(String type) {
        for (NotificationType t : NotificationType.values()) {
            if (t.label.equals(type)) {
                return t;
            }
        }
        return null;
    }

}
