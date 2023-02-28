package edu.greenriver.sdiv.myspringproject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author nemat
 * @version 1.2
 * this is the model class to represent simple Resume Data
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ResumeData
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String activity;
    private String years;
    private boolean graduated;
    private String subject;
    private String level;
}
