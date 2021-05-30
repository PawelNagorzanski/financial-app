package com.example.pawel.expense.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;
    
    @Enumerated(EnumType.STRING)
//    @NaturalId
    @Column(length = 60)
    private RoleName name;

    private String role;
    
    public Role() {

    }

    public Role(RoleName name) {
        this.name = name;
    }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }
    
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
