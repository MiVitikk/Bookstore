package backend24.bookstore.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Category {

    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryid;
    

    

    private String name;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="category")
    @JsonIgnore
    private List<Book> books;

    
    public Category() {
        super();
    }

    

    public Category(String name) {
        super();
        this.name = name;
    }

    public Category(Long categoryid, String name, List<Book> books) {
        this.categoryid = categoryid;
        this.name = name;
        this.books = books;
    }

    

    public Category(Long categoryid) {
        this.categoryid = categoryid;
    }

    public Long getCategoryid() {
        return categoryid;
    }



    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    

        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Category [categoryId=" + categoryid + ", name=" + name + "]";
    }
}
