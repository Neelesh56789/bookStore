package com.neelesh.bookservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="book_details")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long Id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String author;
    @Column
    private String price;
    @Column
    private Long stock;
    @Column
    private String category;
}
