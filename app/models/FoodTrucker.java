package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Food Trucker Model.
 */
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames={"email"})
)
public class FoodTrucker extends Model {

    @Id
    @GeneratedValue
    private Long id;

    @Constraints.Required
    @NotNull
    @Constraints.Email
    private String email;

    @Constraints.Required
    @NotNull
    private String fullName;

    @Constraints.Required
    @NotNull
    private String businessName;

    @Constraints.Required
    @NotNull
    private String phone;

    private Boolean processed = false;

    /**
     * Finder.
     */
    public static Finder<Long, FoodTrucker> FIND = new Finder<Long,FoodTrucker>(FoodTrucker.class);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }
}
