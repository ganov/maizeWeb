package models;

import be.objectify.deadbolt.java.models.Permission;
import be.objectify.deadbolt.java.models.Role;
import be.objectify.deadbolt.java.models.Subject;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;
import play.data.format.Formats;
import play.data.validation.Constraints;
import enums.ApplicationProfile;
import utils.CustomDateSerializer;
import utils.PasswordUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * User Model.
 */
@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames={"email"})
)
public class User extends Model implements Subject {

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
    @Column(length = 64)
    @JsonIgnore
    private byte[] shaPassword;

    @Constraints.Required
    @NotNull
    private Boolean validated = true;

    @Formats.DateTime(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(updatable=false)
    @JsonSerialize(using = CustomDateSerializer.class)
    private DateTime creationDate;

    private String createOwner;

    @Formats.DateTime(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = CustomDateSerializer.class)
    private DateTime updateDate;

    private String updateOwner;

    /**
     * Profile.
     */
    @Enumerated(EnumType.STRING)
    @Constraints.Required
    @NotNull
    private ApplicationProfile profile;

    /**
     * Setter for private Password
     * @param password
     */
    public void setPassword(String password) {
        this.shaPassword = PasswordUtils.getSha512(password);
    }

    /**
     * Getter for private Password.
     */
    public byte[] getPassword() {
        return shaPassword;
    }

    /**
     * DeadBolt's Roles.
     */
    private List<ApplicationProfile> profiles = new ArrayList<>();

    /**
     * Finder.
     */
    public static Finder<Long, User> FIND = new Finder<Long,User>(User.class);

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public byte[] getShaPassword() {
        return shaPassword;
    }

    public void setShaPassword(byte[] shaPassword) {
        this.shaPassword = shaPassword;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public ApplicationProfile getProfile() {
        return profile;
    }

    public void setProfile(ApplicationProfile profile) {
        this.profile = profile;
    }

    public List<ApplicationProfile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<ApplicationProfile> profiles) {
        this.profiles = profiles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreateOwner() {
        return createOwner;
    }

    public void setCreateOwner(String createOwner) {
        this.createOwner = createOwner;
    }

    public DateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(DateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateOwner() {
        return updateOwner;
    }

    public void setUpdateOwner(String updateOwner) {
        this.updateOwner = updateOwner;
    }

    /**
     * Get all {@link Role}s held by this subject.  Ordering is not important.
     *
     * @return a non-null list of roles
     */
    @Override
    public List<? extends Role> getRoles() {
        if (profiles == null) {
            profiles = new ArrayList<>();
        }
        if (profiles.isEmpty()) {
            profiles.add(this.profile);
        }
        return profiles;
    }

    @Override
    public List<? extends Permission> getPermissions() {
        return new ArrayList<>();
    }

    @Override
    public String getIdentifier() {
        return email;
    }
}