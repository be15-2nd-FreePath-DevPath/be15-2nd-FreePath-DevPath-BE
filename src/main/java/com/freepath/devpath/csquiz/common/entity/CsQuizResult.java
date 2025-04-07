package com.freepath.devpath.csquiz.common.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="csquiz_result")
@Getter
@Setter
@NoArgsConstructor
public class CsQuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int csquizResultId;
    @Setter
    private int csquizId;
    @Setter
    private int userId;
    @Setter
    private int userAnswer;
    @Setter
    private String isCsquizCorrect;


}
