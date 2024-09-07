package com.kstu.hackathon.model.game;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_seq")
    @SequenceGenerator(name = "game_seq", sequenceName = "game_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "game_name")
    private String gameName;

    @OneToMany(mappedBy = "game")
    List<GameAttributeValue> gameAttributeValueList;

    @OneToMany(mappedBy = "game")
    List<PlayerToGame> playerToGameList;
}
