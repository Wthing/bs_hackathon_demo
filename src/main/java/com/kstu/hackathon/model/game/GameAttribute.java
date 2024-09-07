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
@Table(name = "GameAttributes")
public class GameAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_attribute_seq")
    @SequenceGenerator(name = "game_attribute_seq", sequenceName = "game_attribute_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "attribute_name")
    private String attributeName;

    @OneToMany(mappedBy = "gameAttribute")
    List<GameAttributeValue> gameAttributeValueList;
}
