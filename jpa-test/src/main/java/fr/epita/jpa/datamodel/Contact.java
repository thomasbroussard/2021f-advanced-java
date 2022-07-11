package fr.epita.jpa.datamodel;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="CONTACTS")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name="ADDRESS")
    private String address;

    @ManyToOne
    private Job job;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
