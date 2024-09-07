package com.kstu.hackathon.model.work;

import com.kstu.hackathon.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "WorkComments")
public class WorkComment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "work_comment_seq")
    @SequenceGenerator(name = "work_comment_seq", sequenceName = "work_comment_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "work_id")
    private Work work;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "content")
    private String content;

    @Column(name = "send_date")
    private LocalDateTime sendDate;
}
