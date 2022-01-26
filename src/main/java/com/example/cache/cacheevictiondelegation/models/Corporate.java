package com.example.cache.cacheevictiondelegation.models;

import com.example.cache.cacheevictiondelegation.validations.WalletTypeValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Corporate implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;

    @Column(nullable = false)
    private String corporateIdentifier;

    @Column(nullable = false)
    private String corporateName;

    @Email
    private String emailId;

    @WalletTypeValidation
    private String walletType;

    public Corporate(String corporateName, String emailId, String walletType){
        this.corporateName = corporateName;
        this.emailId = emailId;
        this.walletType = walletType;
    }
}
