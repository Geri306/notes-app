package com.codecool.persistence.entity;

import com.codecool.data.Label;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "note")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Note {
    @Id
    @SequenceGenerator(
            name = "note_id_sequence",
            sequenceName = "note_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "note_id_sequence"
    )
    private long id;
    @Column(name = "content")
    private String content;
    @Column(name = "label")
    @Enumerated(value = EnumType.STRING)
    private Label label = Label.GREEN;
    @Column(name = "done")
    private boolean done = false;
    @Column(name = "modified")
    private LocalDateTime modified = LocalDateTime.now();
}
