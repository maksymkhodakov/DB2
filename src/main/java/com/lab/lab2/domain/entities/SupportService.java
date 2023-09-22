package com.lab.lab2.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "support_services")
@EqualsAndHashCode
public class SupportService extends AbstractEntity {
    @Column(name = "team_name")
    private String teamName;

    @Column(name = "domain_name")
    private String domainName;

    @OneToOne
    @JoinColumn(name = "dashboard_id")
    private Dashboard dashboard;

    @OneToMany(fetch = FetchType.LAZY)
    private List<CustomerSupporter> customerSupporters;
}
