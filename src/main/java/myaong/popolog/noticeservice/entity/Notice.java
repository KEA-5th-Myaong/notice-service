package myaong.popolog.noticeservice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`notice`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notice_id")
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false)
	private String content;

	// 중요 여부
	@Column(name = "is_important", nullable = false)
	private Boolean isImportant;

	@Builder
	public Notice(String title, String content, Boolean isImportant) {
		this.title = title;
		this.content = content;
		this.isImportant = isImportant;
	}
}
