package com.example.umc.domain.term.entity;

import com.example.umc.domain.term.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table
public class Term {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

//    @OneToMany(mappedBy = "term", cascade = CascadeType.ALL)
//    private List<UserTerm> userTerms = new ArrayList<>();
}
