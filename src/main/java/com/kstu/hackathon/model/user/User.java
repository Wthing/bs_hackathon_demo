package com.kstu.hackathon.model.user;

import com.kstu.hackathon.model.forum.Forum;
import com.kstu.hackathon.model.forum.ForumComment;
import com.kstu.hackathon.model.game.PlayerToGame;
import com.kstu.hackathon.model.reviews.Rating;
import com.kstu.hackathon.model.reviews.Review;
import com.kstu.hackathon.model.work.Work;
import com.kstu.hackathon.model.work.WorkComment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_email_confirmed")
    private Boolean isEmailConfirmed;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<SecretCode> codes;

    @OneToMany(mappedBy = "creator")
    private List<Forum> forumList;

    @OneToMany(mappedBy = "user")
    private List<ForumComment> forumCommentList;

    @OneToMany(mappedBy = "user")
    private List<PlayerToGame> playerToGameList;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratingList;

    @OneToMany(mappedBy = "user")
    private List<Review> reviewList;

    @OneToMany(mappedBy = "user")
    private List<Work> workList;

    @OneToMany(mappedBy = "user")
    private List<WorkComment> workCommentList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }
}
