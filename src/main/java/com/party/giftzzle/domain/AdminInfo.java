package com.party.giftzzle.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.party.giftzzle.domain.enumeration.Gender;
import com.party.giftzzle.service.dto.AdminInfoDTO;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A AdminInfo.
 */
@Entity
@Table(name = "admin_info")
public class AdminInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonIgnore
  @NotNull
  @Column(name = "user_id")
  private Long userId;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "gender", nullable = false)
  private Gender gender;

  @Temporal(TemporalType.DATE)
  @Column(name = "birthdate")
  private Date birthdate;

  @Column(name = "postal_code")
  private String postalCode;

  @Column(name = "city", nullable = true)
  private String city;

  @Column(name = "state", nullable = true)
  private String state;

  @Column(name = "country", nullable = true)
  private String country;

  @Column(name = "address", nullable = true)
  private String address;

  @OneToOne()
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private User user;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Gender getGender() {
    return gender;
  }

  public AdminInfo gender(Gender gender) {
    this.gender = gender;
    return this;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public AdminInfo postalCode(String postalCode) {
    this.postalCode = postalCode;
    return this;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCity() {
    return city;
  }

  public AdminInfo city(String city) {
    this.city = city;
    return this;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public AdminInfo state(String state) {
    this.state = state;
    return this;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public AdminInfo country(String country) {
    this.country = country;
    return this;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getAddress() {
    return address;
  }

  public AdminInfo address(String address) {
    this.address = address;
    return this;
  }

  public void setAddress(String address) {
    this.address = address;
  }
  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public AdminInfo() {
  }

  public AdminInfo(AdminInfoDTO adminInfoDTO) {
    this.id = adminInfoDTO.getId();
    this.gender = adminInfoDTO.getGender();
    this.birthdate = adminInfoDTO.getBirthdate();
    this.postalCode = adminInfoDTO.getPostalCode();
    this.city = adminInfoDTO.getCity();
    this.state = adminInfoDTO.getState();
    this.country = adminInfoDTO.getCountry();
    this.address = adminInfoDTO.getAddress();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdminInfo adminInfo = (AdminInfo) o;
    if (adminInfo.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), adminInfo.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "AdminInfo{" +
        "id=" + getId() +
        ", userId=" + getUserId() +
        ", gender='" + getGender() + "'" +
        ", birthdate='" + getBirthdate() + "'" +
        ", postalCode='" + getPostalCode() + "'" +
        ", city='" + getCity() + "'" +
        ", state='" + getState() + "'" +
        ", country='" + getCountry() + "'" +
        ", address='" + getAddress() + "'" +
        "}";
  }
}
