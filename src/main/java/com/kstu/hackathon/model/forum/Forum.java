package com.kstu.hackathon.model.forum;

import com.kstu.hackathon.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Forums")
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "forum_seq")
    @SequenceGenerator(name = "forum_seq", sequenceName = "forum_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creator")
    private User creator;

    @Column(name = "name")
    private String name;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "forum")
    private List<ForumComment> forumCommentList;
}
