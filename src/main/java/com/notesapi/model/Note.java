package com.notesapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_At")
    private LocalDateTime createdAt;

    @Column(name = "updated_At")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

//    while creating notes
    @PrePersist
    protected void onCreate(){
        updatedAt = LocalDateTime.now();
        createdAt = LocalDateTime.now();
    }

//    while updating notes
    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }


}
