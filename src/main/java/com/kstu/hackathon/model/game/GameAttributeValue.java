package com.kstu.hackathon.model.game;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GameAttributeValues")
public class GameAttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_attribute_value_seq")
    @SequenceGenerator(name = "game_attribute_value_seq", sequenceName = "game_attribute_value_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "game_attribute_id")
    private GameAttribute gameAttribute;

    @Column(name = "value")
    private String value;
}
