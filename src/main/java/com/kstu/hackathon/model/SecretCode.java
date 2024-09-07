package com.kstu.hackathon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SecretCodes")
public class SecretCode {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sc_seq")
    @SequenceGenerator(name = "sc_seq", sequenceName = "sc_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // todo!!! check this shit
    private User user;

    @Column(length = 4, nullable = false)
    private String code;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "expire_date")
    private LocalDateTime expireDate;
}
