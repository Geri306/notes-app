package com.codecool.persistence.entity;

import com.codecool.data.Label;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;


// consider removing @Data and @EqualsAndHashCode annotations ...
// ... in JPA entity classes due to performance issues
@Entity
@Getter
@Setter
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Note {

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "note_id_sequence" // default: "note_seq"
    )
    @SequenceGenerator(
            name = "note_id_sequence",
            sequenceName = "note_id_sequence",
            allocationSize = 1
    )
    @Id
    private Long id;
    private boolean done = false;
    /*@Column(name = "content")*/
    private String content = "";
    // without @Enumerated(...) the label would have a numerical value in the database
    @Enumerated(value = EnumType.STRING)
    private Label label = Label.GREEN;
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime modified_L;
    // To ignore a field, annotate it with @Transient so it will not be mapped by hibernate.
    @Transient
    private String modified;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return done == note.done && Objects.equals(id, note.id) && Objects.equals(content, note.content) && label == note.label && Objects.equals(modified_L, note.modified_L) && Objects.equals(modified, note.modified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, done, content, label, modified_L, modified);
    }
}
