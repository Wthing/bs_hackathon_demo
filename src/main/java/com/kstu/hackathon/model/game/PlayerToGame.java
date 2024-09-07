package com.kstu.hackathon.model.game;

import com.kstu.hackathon.model.user.User;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PlayerToGames")
public class PlayerToGame {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_to_game_seq")
    @SequenceGenerator(name = "player_to_game_seq", sequenceName = "player_to_game_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(name = "score")
    private Integer score;
}
