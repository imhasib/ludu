package com.hsb.ludu.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@Data
public class Player {
    @NotBlank(message = "Name is required.")
    private String name;

    @NotNull(message = "Age is required.")
    @Min(1)
    @Max(150)
    private Integer age;
    @JsonIgnore
    private int score = 0;
    @JsonIgnore
    private int previousScore = 0;
    @JsonIgnore
    private boolean started;
    public void addScore(int s) {
        if(this.isStarted()) {
            this.score += s;
        }
    }

    public void reset() {
        score = 0;
        previousScore = 0;
        started = false;
    }
}
