package com.gergokovacs.notes.persistence.entity;

import com.gergokovacs.notes.data.Label;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Note {
    @Id
    @GeneratedValue
    private Long id;
    private boolean done = false;
    private String content = "";
    @Enumerated(EnumType.STRING)
    private Label label = Label.GREEN;
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime modifiedLong;
    @Transient
    private String modified;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return done == note.done && Objects.equals(id, note.id) && Objects.equals(content, note.content) && label == note.label && Objects.equals(modifiedLong, note.modifiedLong) && Objects.equals(modified, note.modified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, done, content, label, modifiedLong, modified);
    }
}
